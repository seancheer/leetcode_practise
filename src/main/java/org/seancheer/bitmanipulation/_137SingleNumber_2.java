package org.seancheer.bitmanipulation;

/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 * <p>
 * <p>
 * Follow up: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * @author: seancheer
 * @date: 2021/2/7
 **/
public class _137SingleNumber_2 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2};
        _137SingleNumber_2 obj = new _137SingleNumber_2();
        int res = obj.singleNumber(nums);
        int res2 = obj.singleNumber2(nums);
        int res3 = obj.singleNumber3(nums);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3);

        nums = new int[]{0, 1, 0, 1, 0, 1, 99};
        obj = new _137SingleNumber_2();
        res = obj.singleNumber(nums);
        res2 = obj.singleNumber2(nums);
        res3 = obj.singleNumber3(nums);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3);
    }


    /**
     * 解题思路：因为时间复杂度的要求，所以不能使用排序之类的算法，所以这里使用一个int，循环计算数组中每一位数字出现的次数，
     * 循环一圈后，然后对3取余，如果这个数出现了三次，那么一定为0，否则的话，将该为置为1，移动到对应的位置，最后的结果就是
     * 最终的结果，该算法的时间复杂度为32*size，也就是O(n)
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            int count = 0;
            for (int item : nums) {
                if (((item >> i) & 1) > 0) {
                    ++count;
                }
            }

            int tmp = (count % 3);
            //其实这个tmp要么是0，要么是1
            res |= (tmp << i);
        }

        return res;
    }

    /**
     * 解题思路：one：表示截至目前1只出现一次的位置，two：表示截至目前1出现两次的位置；
     * one ^ item：表示和当前值item异或后，1出现两次的被异或为0，剩下的1就是item异或后出现1次的数，在& (~two)，则表示把所有出现
     * 3次1的值给清零掉；
     * two ^ item: 表示当前出现3次的1被清零，剩下的1就是出现两次的1，在& (~one)表示出现3次的被清零，最终的结果就是只出现2次的1
     * <p>
     * 最终的结果一定是two为0，one为最终的答案。
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int one = 0;
        int two = 0;
        int three = 0;
        //TODO 待完成
        return 0;

    }

    /**
     * 解题思路：one：表示截至目前1只出现一次的位置，two：表示截至目前1出现两次的位置；
     * one ^ item：表示和当前值item异或后，1出现两次的被异或为0，剩下的1就是item异或后出现1次的数，在& (~two)，则表示把所有出现
     * 3次1的值给清零掉；
     * two ^ item: 表示当前出现3次的1被清零，剩下的1就是出现两次的1，在& (~one)表示出现3次的被清零，最终的结果就是只出现2次的1
     * <p>
     * 最终的结果一定是two为0，one为最终的答案。
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int one = 0;
        int two = 0;
        for (int item : nums) {
            one = ((one ^ item) & (~two));
            two = ((two ^ item) & (~one));
        }
        return one;
    }
}
