package org.seancheer;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 * @author: seancheer
 * @date: 2021/7/29
 **/
public class _169MajorityElement {
    public static void main(String[] args) {
        _169MajorityElement obj = new _169MajorityElement();
        int[] nums = new int[]{3, 2, 3};
        var res = obj.majorityElement(nums);
        System.out.println("res:" + res); //3

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        res = obj.majorityElement(nums);
        System.out.println("res:" + res); //2
    }

    /**
     * 解题思路：要求在O(n)的时间复杂度和O(1)的空间复杂度来解决这个问题
     * 简单的思路就是排个序，然后中间的值就是要求的答案，但是排序的时间复杂度不可能为O(n)，所以这个方法不可取，还有其他办法么？
     * 如果按照题目的要求的话，我们可以使用一个32位的int类型数字，然后记录每一位出现的次数，如果次数大于n/2，那么就意味着这一位
     * 属于最终答案里面的，记录下来，以此类推，得到最终的结果
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }

        int res = 0;
        int len = nums.length;

        for(int i = 0;i < Integer.SIZE;++i){
            int count = 0;
            int flag = (1 << i);
            for(int num : nums){
                if ((num & flag) != 0){
                    ++count;
                }
            }

            //这一位超过一半了，是目标答案，记录下来
            if (count > (len >> 1)){
                res |= flag;
            }
        }

        return res;
    }
}
