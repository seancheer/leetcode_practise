package main

import "fmt"

/*
*
解题思路：从nums中查找总和>=target的最短子串
1 O(n)的解法很简单，非常经典的滑动窗口问题，当和比target小的时候，移动右指针，当和比target大的时候移动左指针，遍历完整个数组就能完成解答, 此题的难点在于处理各种边界情况
*/
func main() {
	result := minSubArrayLen(7, []int{2, 3, 1, 2, 4, 3})
	fmt.Println(result) // 2

	result = minSubArrayLen(4, []int{1, 4, 4})
	fmt.Println(result) // 1

	result = minSubArrayLen(11, []int{1, 1, 1, 1, 1, 1, 1, 1})
	fmt.Println(result) // 0

	result = minSubArrayLen(13, []int{2, 3, 1, 2, 4, 8})
	fmt.Println(result) // 3
}

func minSubArrayLen(target int, nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if nums[0] >= target {
		return 1
	}

	var (
		i      = 0
		j      = 1
		sum    = nums[0]
		result = 0
	)

	for j < len(nums) {
		if nums[j]+sum < target {
			sum += nums[j]
			j++
		} else {
			if result == 0 {
				result = j - i + 1
			} else {
				result = min(result, j-i+1)
			}
			if result == 1 { // 1已是最小的结果了，无需继续遍历
				return result
			}
			// 当前结果已经能满足需求
			sum -= nums[i]
			i++
		}
	}
	return result
}
