package org.seancheer.graph;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * 其实就是判断有没有环，注意题目中的prerequisites不一定包含所有课程，这种情况下如果不存在环也是可以的
 *
 * @author: seancheer
 * @date: 2021/8/16
 **/
public class _207CourseSchedule extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _207CourseSchedule();
        String prerequisites = "[[1,0]]";
        int numCourses = 2;
        var res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        var res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2); //true

        prerequisites = "[[1,0],[0,1]]";
        numCourses = 2;
        res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2); //false

        prerequisites = "[[1,1]]";
        numCourses = 2;
        res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2);//false

        prerequisites = "[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]";
        numCourses = 20;
        res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2);//false

        prerequisites = "[[1,0],[0,2],[2,1]]";
        numCourses = 3;
        res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2); //false

        prerequisites = "[[1,4],[2,4],[3,1],[3,2]]";
        numCourses = 5;
        res = obj.canFinish(numCourses, convertStrTo2DemArr(prerequisites));
        res2 = obj.canFinish2(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + res + "  res2:" + res2); //true
    }

    /**
     * 解题思路：题目规定了prerequisites都是二维数组
     * 根据prerequisites构造一个线段树，线段树构造完成之后，按照DFS的方式访问线段树，查找有没有环
     * 注意，prerequisites相同的情况， prerequisites不会出现重复的情况
     * DFS的方式会超时，所以这里就不使用了，看解法2，使用广度遍历的方式
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || null == prerequisites) {
            return false;
        }
        CourseNode[] allNodes = new CourseNode[numCourses];
        for (int[] pre : prerequisites) {
            if (allNodes[pre[0]] == null) {
                allNodes[pre[0]] = new CourseNode(pre[0]);
            }
            if (allNodes[pre[1]] == null) {
                allNodes[pre[1]] = new CourseNode(pre[1]);
            }

            allNodes[pre[0]].preLi.add(allNodes[pre[1]]);
        }

        //构造完成之后，开始用DFS的方式检测有没有环
        for (CourseNode node : allNodes) {
            if (null != node && !node.skipped) {
                //使用flag，成功避免了每次dfs之后清理状态的操作
                if (!dfs(node)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 使用dfs的方式
     *
     * @param node
     * @return
     */
    private boolean dfs(CourseNode node) {
        //表示这个节点访问过了，在外层的for循环里面就可以跳过，避免重复判断
        node.skipped = true;
        //出现了环
        if (node.visited) {
            return false;
        }
        node.visited = true;
        for (CourseNode child : node.preLi) {
            if (!dfs(child)) {
                //恢复状态
                node.visited = false;
                return false;
            }
        }
        //恢复状态
        node.visited = false;
        return true;
    }

    class CourseNode {
        public int val;
        public List<CourseNode> preLi = new ArrayList<>();
        //for BFS
        public boolean visited = false;
        public boolean skipped = false;

        public CourseNode(int val) {
            this.val = val;
        }
    }


    /**
     * 解题思路：题目规定了prerequisites都是二维数组
     * 根据prerequisites构造一个线段树，线段树构造完成之后，按照DFS的方式访问线段树，查找有没有环
     * 注意，prerequisites相同的情况， prerequisites不会出现重复的情况
     * DFS的方式会超时，所以这里就不使用了，看解法2，使用广度遍历的方式，思想如下：
     * 1 统计每个节点的度数，比如 [1,2],[1,3]，[2,3]那么2的度数为1，3的度数为2（1和2都依赖3）
     * 2 寻找度数为0节点作为起点（这里为1），对其依赖的项目度数全部-1，然后会有新的度数为0的节点，放在队列中
     * 3 从队列中不停的remove节点作为起点（队列中存储的都是度数为0的节点），重复上面的操作
     *
     * 最后，如果没有环的话，那么所有节点的度数都必然为0，否则的必有节点度数不为0（这就是从度数0节点为起点的原因）
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || null == prerequisites) {
            return false;
        }
        //这里直接使用List，就不需要自定义类了
        List<List<Integer>> allNodes = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            allNodes.add(new ArrayList<>());
        }

        int[] degrees = new int[numCourses];
        for (int[] pre : prerequisites) {
            allNodes.get(pre[0]).add(pre[1]);
            degrees[pre[1]]++;
        }

        //使用广度优先遍历的方式来查找有没有环，BFS用的是度的方式，且看代码
        //查找度为0的
        Queue<Integer> q = new ArrayDeque<>();
        int zeroCount = 0;
        for(int i= 0;i < numCourses;++i){
            //每次都从0度开始入手，因为如果不是这个条件的话，会导致算法无效，永远都找不到环
            if (degrees[i] == 0){
                ++zeroCount;
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.remove();
            for(int child : allNodes.get(cur)){
                --degrees[child];
                //刚好减为0了
                if (degrees[child] == 0){
                    ++zeroCount;
                    q.add(child);
                }
            }
        }
        //出来之后，没有环的话，那么所有的节点的度数必然为0，否则的话，说明有环
        return zeroCount == numCourses;
    }


}
