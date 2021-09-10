package org.seancheer.graph;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 *
 * @author: seancheer
 * @date: 2021/8/16
 **/
public class _210CourseSchedule_2 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _210CourseSchedule_2();
        String prerequisites = "[[1,0]]";
        int numCourses = 2;
        var res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res)); //[0,1]

        prerequisites = "[[1,0],[0,1]]";
        numCourses = 2;
        res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res)); //[]

        prerequisites = "[[1,1]]";
        numCourses = 2;
        res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res));//[]

        prerequisites = "[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]";
        numCourses = 20;
        res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res));//[]

        prerequisites = "[[1,0],[0,2],[2,1]]";
        numCourses = 3;
        res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res)); //[]

        prerequisites = "[[1,4],[2,4],[3,1],[3,2]]";
        numCourses = 5;
        res = obj.findOrder(numCourses, convertStrTo2DemArr(prerequisites));
        System.out.println("res:" + Arrays.toString(res)); //[0, 4,1,2,3]
    }

    /**
     * 解题思路：其实和{@link _207CourseSchedule}一样，这一道题需要返回修课的顺序，所以我们稍微修改下处理逻辑，让
     * prerequisites[1]指向prerequisites[0]，把prerequisites[0]作为维度的计算标准，这样子，每次从0度开始，就把其加入
     * 到最终的答案中，最后的顺序就是天然对的
     * 依旧采用BFS的方式来统计和判断有没有环
     * 需要注意的是，这道题虽然prerequisites依旧有可能会遗漏一些课程，但是答案中这些遗漏的课程必须出现，这点很重要，和
     * {@link _207CourseSchedule}不同
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || null == prerequisites) {
            return new int[]{};
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            graph.add(new ArrayList<>());
        }
        //首先建立映射图，并计算度数
        int[] degrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; ++i) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            ++degrees[prerequisites[i][0]];
        }

        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (degrees[i] == 0) {
                queue.add(i);
                //维度为0说明没有依赖，完全可以直接学
                res.add(i);
            }
        }

        //接下来开始BFS来判断有没有环，都是从维度0开始计算
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            List<Integer> children = graph.get(cur);
            for (Integer child : children) {
                --degrees[child];
                if (degrees[child] == 0) {
                    res.add(child);
                    queue.add(child);
                }
            }
        }

        //判断最终的结果是不是出现了所有的课程
        if (res.size() < numCourses) {
            return new int[0];
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
}
