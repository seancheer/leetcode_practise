package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * 自创题，题目描述和{@link _154FindMinInRotatedSortedArr_2}一样，只是这道题不返回最小值，而要求返回最小值的index，如果有
 * 重复值，返回该值在枢纽点的index，比如1 1 1 1 1 1 1 1 2 1 1， 枢纽点index9的位置，如果有多个答案（比如全相等的情况，返回任意结果）
 * 时间复杂度要求还是O(logN)
 *
 * @author: seancheer
 * @date: 2021/9/6
 **/
public class FindMinInRotatedSortedArr_3 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new FindMinInRotatedSortedArr_3();
        int[] nums = new int[]{1, 3, 5};
        var res = obj.findMin(nums);
        System.out.println("res:" + res); // index0 = 1

        nums = new int[]{2, 2, 2, 0, 1};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index3 = 0

        nums = new int[]{4, 5, 6, 7, 0, 1, 4};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index4 = 0

        nums = new int[]{2, 2, 2};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index0 = 2

        nums = new int[]{2, 2, 2, 1};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index3 = 1

        nums = new int[]{5, 1, 2, 5};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index1 = 1

        nums = new int[]{5, 1, 5, 5, 5};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index1 = 1

        nums = new int[]{1, 1, 1, 1, 1, 1, 1, 2, 1, 1};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // index8 = 1，很明显2后面的就是枢纽点
    }

    /**
     * 解题思路：和{@link _154FindMinInRotatedSortedArr_2}代码大致上是相同的，不过会有一个小小的改变，因为要返回index，原来的代码
     * 在nums[mid]和nums[end]相等的情况下跳过枢纽点，所以我们在--end，之前判断一下end前面的值是否比自己大，如果大的话，那么end就一定是
     * 枢纽点，赋值给start（因为我们返回的就是start），然会break出来即可
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        //只有一个元素，或者本身就是有序的情况，注意不能相等，因为有可能会重复，比如[2,3,4,5,1,2]
        if (nums.length == 1 || nums[0] < nums[nums.length - 1]) {
            return 0;
        }


        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            //由于有可能会相等，所以之前的短路判断不能在用了，比如[2,3,4,5,1,2]这种就会出问题
//            if(nums[start] < nums[end]){
//                return nums[start];
//            }

            int mid = (start + end) >> 1;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                //end发现前一个比自己还要大，说明end到枢纽点了，停下来吧
                if (nums[end - 1] > nums[end]) {
                    start = end;
                    break;
                }
                --end;
            }
        }
        //这种是全相等的情况
        return start;
    }
}
