package org.seancheer.greedy;

import org.seancheer.utils.LeetCodeParent;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 * <p>
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 * <p>
 * Return the maximum number of courses that you can take.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Example 2:
 * <p>
 * Input: courses = [[1,2]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 *
 * @author: seancheer
 * @date: 2021/8/17
 **/
public class _630CourseSchedule_3 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _630CourseSchedule_3();
        String courses = "[[1,2]]";
        var res = obj.scheduleCourse(convertStrTo2DemArr(courses));
        var res2 = obj.scheduleCourse2(convertStrTo2DemArr(courses));
        var res3 = obj.scheduleCourse3(convertStrTo2DemArr(courses));
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3); //1

        courses = "[[3,2],[4,3]]";
        res = obj.scheduleCourse(convertStrTo2DemArr(courses));
        res2 = obj.scheduleCourse2(convertStrTo2DemArr(courses));
        res3 = obj.scheduleCourse3(convertStrTo2DemArr(courses));
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3);//0

        courses = "[[100,200],[200,1300],[1000,1250],[2000,3200]]";
        res = obj.scheduleCourse(convertStrTo2DemArr(courses));
        res2 = obj.scheduleCourse2(convertStrTo2DemArr(courses));
        res3 = obj.scheduleCourse3(convertStrTo2DemArr(courses));
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3); //3

        courses = "[[5,5],[4,6],[2,6]]";
        res = obj.scheduleCourse(convertStrTo2DemArr(courses));
        res2 = obj.scheduleCourse2(convertStrTo2DemArr(courses));
        res3 = obj.scheduleCourse3(convertStrTo2DemArr(courses));
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3);//2
    }

    /**
     * 解题思路：贪心算法，后面两种解法都会导致TLE的问题，建议参考下思路，以后可以解决类似的问题，该题还是得贪心算法。
     * 1 首先还是对课程进行一个排序，按照截止日从小到大排序
     * 2 维护一个优先队列，该队列按照duration从大到小的顺序排序
     * 3 每次遍历到一个课程，如果发现day + course[0] > course[1]，那么我们就从优先队列中选取一个duration最久的课程，然后用当前
     *   课程替代此课程，这样子尽可能的缩小day，就能保证我们后面学习更多的课程
     * 4 最后优先队列中存储的课程就是我们要学习的所有课程，也就是最终的答案
     * 这里有一个疑问？为什么在超过截止日期的时候，我们把优先队列里面持续时间最久的课程去掉，就能够保证day在当前课程的截止日期之前？
     * 且看下面的数学关系，假设当前时间为day，当前课程为course，long表示优先队列里面持续时间最久的课程
     * 可以有以下大小关系：
     * day + course[0] > course[1]
     * day <= course[1]（因为courses是按照截止时间排序的，day之前已经保证了该有的课程都能修上，如果有某个课程持续时间比course[1]还大，
     *                   那么就意味其截止日期必然比course[1]大（持续时间小于等于截止日期），这个时候它就是非法的，不可能进入队列）
     * cur[0] <= long <= day
     * 所以day + course[0] - long必然会小于等于course[1]，所以可以放心的去掉持续时间最久的课程
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        if (null == courses || courses.length == 0 || courses[0] == null) {
            return 0;
        }
        Arrays.sort(courses, Comparator.comparingInt(item -> item[1]));
        Queue<Integer> queue = new PriorityQueue<>((left, right) -> right - left);
        int day = 0;
        for(int[] course : courses){
            day += course[0];
            //这里的加入也是为了方便后面直接remove，如果当前课程刚好duration最久，直接移除的就是当前课程
            queue.add(course[0]);
            //发现超过截止日期了，那么就把持续时间最久的课程去掉。
            if (day > course[1]){
                day -= queue.remove();
            }
        }
        return queue.size();
    }


    /**
     * 解题思路：使用递归的方式进行暴力求解，对于每一个课程，我们可以选择，也可以不选择，两条路径任选一条，只要一条是通的
     * 就可以。
     * 必须要进行排序，因为这个course的顺序至关重要，比如说[[2,5],[2,19],[1,3]] ，这个例子，如果不按照deadline从小到大排序，
     * 那么会导致三个无法都选上，因为前两个都选了，那么4就大于3了，[1,3]就不能选了，所以我们要把按照deadline从小到大的顺序来
     * 执行算法，这样子就可以尽可能的在减小day的前提下找到最佳答案。
     * 这种方法就不测试了，很有可能会TLE
     *
     * @param courses
     * @return
     */
    public int scheduleCourse2(int[][] courses) {
        if (null == courses || courses.length == 0 || courses[0] == null) {
            return 0;
        }


        Arrays.sort(courses, Comparator.comparingInt(item -> item[1]));
        return helper(courses, 0, 0);
    }

    private int helper(int[][] courses, int day, int index) {
        //到末尾了
        if (index == courses.length) {
            return 0;
        }

        int[] cur = courses[index];
        int taken = 0, notTaken = 0;
        if (day + cur[0] <= cur[1]) {
            taken = 1 + helper(courses, day + cur[0], index + 1);
        }
        notTaken = helper(courses, day, index + 1);

        return Math.max(taken, notTaken);
    }


    /**
     * 解题思路：上一道解法的记忆化搜索，避免分支的重复判断，其实本质上是一种动态规划
     * 这里也只是提供一种思路，注意，这个也会TLE，所以本道题的关键还是在于贪心算法
     * @param courses
     * @return
     */
    public int scheduleCourse3(int[][] courses) {
        if (null == courses || courses.length == 0 || courses[0] == null) {
            return 0;
        }

        //因为deadline敏感，所以这道题必须先排序，请看其他解法关于排序的解释
        Arrays.sort(courses, Comparator.comparingInt(item -> item[1]));
        int maxDay = courses[courses.length - 1][1];
        Integer[][] memo = new Integer[courses.length][maxDay + 1];
        return helper2(courses, 0, 0, memo);
    }

    /**
     * 递归的进行查找，但是带有记忆化搜索功能
     * @param courses
     * @param index
     * @param day
     * @param memo
     * @return
     */
    private Integer helper2(int[][] courses, int index, int day, Integer[][] memo){
        if (index == courses.length){
            return 0;
        }

        if(null != memo[index][day]){
            return memo[index][day];
        }

        int taken = 0, notTaken = 0;
        int[] cur = courses[index];
        if (day + cur[0] <= cur[1]){
            taken = 1 + helper2(courses, index + 1, day + cur[0], memo);
        }

        notTaken = helper2(courses, index + 1, day, memo);
        memo[index][day] = Math.max(taken, notTaken);
        return memo[index][day];
    }
}