package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * Example 4:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is 0, 1, or 2.
 * <p>
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 * 使得相同的颜色相邻，要按照0，1，2的顺序，不能乱序，不能使用标准库的排序函数
 *
 * @author: seancheer
 * @date: 2021/8/14
 **/
public class _75SortColors {

    public static void main(String[] args) {
        var obj = new _75SortColors();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        obj.sortColors(nums);
        obj.sortColors2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{2, 0, 1};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.sortColors(nums);
        obj.sortColors2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{0};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.sortColors(nums);
        obj.sortColors2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{1, 1, 1, 1, 1, 1};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.sortColors(nums);
        obj.sortColors2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{2, 0, 2};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.sortColors(nums);
        obj.sortColors2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));
    }

    /**
     * 解题思路：要求一遍过，并占用常数的空间复杂度
     * 使用双指针left和right，一个从头开始遍历，一个从尾部开始，我们把0放在left，把2放在right，同时维护一个leftOne指针，表示
     * 在left中第一个1的位置
     * 1 如果left位置是1，不处理，如果是2，那么和right交换值；如果是0，此时需要检查leftOne是否合法，不合法，直接跳过（说明left没有出现过
     * 1），合法，和leftOne位置交换，同时leftOne +=1 （此举是为了保证[0, leftOne)区间内全是0
     * 2 如果right位置是2，不处理，如果是0，那么需要检查leftOne是否合法，合法和leftOne位置交换，非法和left交换
     * 3 每一次迭代完成后，需要检查left和right位置的值，left如果是2，说明和right进行了一次无效的交换（即两者都是2），不移动left指针；
     * 如果left是1且leftOne非法，将left赋给leftOne表示left开始出现了1，同时移动left指针；如果是0，直接移动left指针；
     * right位置只有在2的情况下才移动指针，否则不移动。
     * 逻辑比较复杂，且看解法2
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }

        int left = 0, leftOne = -1;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[right] == 0) {
                //出现过1的位置
                if (leftOne != -1) {
                    nums[leftOne++] = 0;
                    nums[right] = 1;
                    //left没有出现过1
                } else {
                    int tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = tmp;
                }
            }

            if (nums[left] == 2) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                //此举是为了保证[0,leftOne)都是0
            } else if (nums[left] == 0 && leftOne != -1) {
                int tmp = nums[left];
                nums[left] = nums[leftOne];
                nums[leftOne] = tmp;
                ++leftOne;
            }

            //所有的处理都完成后，left第一次出现了1，记录下位置
            if (nums[left] == 1 && leftOne == -1) {
                leftOne = left;
            }

            //避免出现无效交换的情况
            if (nums[left] != 2) {
                ++left;
            }

            //这一波操作之后，只有当right正好变为2了才往前走，left始终往前走，但是要记录leftOne的位置（就是1的位置
            if (nums[right] == 2) {
                --right;
            }
        }
    }


    /**
     * 解题思路：要求一遍过，并占用常数的空间复杂度，其实和解法1的本质上思想是类似的，只是这个思路更加清晰
     * 使用三个指针，i = 0, j = 0, k = nums.length() - 1，i意在指向0的位置，j意在指向1的位置，k意在指向2的位置
     * 以1的位置j为游标
     * 1 nums[j] == 0，此时直接交换nums[i]和nums[j]，同时i++, j++，保证i指过的位置都是0
     * 2 nums[j] == 1, 直接移动j++
     * 3 nums[j] == 2，交换nums[j]和nums[k]，此时注意只移动k--, 因为nums[j]和nums[k]有可能相同（我们没有判断nums[k]），
     * 所以仅移动k是为了保证下一次nums[j]的2能被放在正确的位置
     * <p>
     * 其实本质上思路和1是一样的。
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }

        //p0表示i， p1表示j， p2表示k
        int p0 = 0, p1 = 0, p2 = nums.length - 1;
        //注意终止条件，最后else分支只减p2，所以有可能导致p2和p1相遇，相遇就说明处理完成了
        //还有一个点，就是这里的p1和p2必须有相等性测试，因为[2,0,1]这种情况，如果没有相等性测试，会导致0没有在正确的位置上，虽然
        //可能会导致p1==p2，刚好nums[p1] == 2导致无效交换的情况，但是这个是必要的
        while (p1 <= p2) {
            if (nums[p1] == 0) {
                int tmp = nums[p1];
                nums[p1] = nums[p0];
                nums[p0] = tmp;
                ++p1;
                ++p0;
            } else if (nums[p1] == 1) {
                ++p1;
            } else {
                int tmp = nums[p2];
                nums[p2] = nums[p1];
                nums[p1] = tmp;
                //注意这里只移动p2指针，不移动p1
                --p2;
            }
        }
    }
}
