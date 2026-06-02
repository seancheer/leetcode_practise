package main

import "fmt"

/*
*
解题思路：经典的接雨水问题，依旧是双指针解法, 注意，这道题的两端是没法存水的
解法1
1 初始化两个指针，分别指向数组的开始和末尾，同时需要维护leftMax和rightMax，接下来将分为下面两种情况

		1.1 如果左指针的值小于右指针的值，此时左指针的位置能蓄水的水量=左指针之前的最大高度（即leftMax）- height[current] （右指针的rightMax肯定是高于左指针的leftMax的）,
	        此时引动左指针
	    1.2 反之，则移动右指针

这道解法的巧妙之处在于，如果想用leftMax - height[current]作为当前的水量，那么意味着rightMax必须>=leftMax，负责的话水是无法从蓄住的，而这个解法移动左右指针的方式就可以保证移动方的max一定小于未移动指针的max
*/
func main() {
	var (
		nums = []int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
	)
	result := trap(nums)
	fmt.Println(result) // 6

	nums = []int{4, 2, 0, 3, 2, 5}
	result = trap(nums)
	fmt.Println(result) // 9

	nums = []int{5, 5, 6, 3, 0, 4}
	result = trap(nums)
	fmt.Println(result) // 5
}

// trap 先正向计算一遍蓄水量，在逆序计算一遍蓄水量
func trap(height []int) int {
	if len(height) <= 2 {
		return 0
	}

	var (
		i        = 0
		j        = len(height) - 1
		result   = 0
		leftMax  = 0
		rightMax = 0
	)
	for i < j {
		leftMax = max(height[i], leftMax)
		rightMax = max(height[j], rightMax)
		// 左指针的值小于右指针，本次移动左指针，这样子可以保证rightMax >= leftMax
		if height[i] < height[j] {
			result += (leftMax - height[i])
			i++
		} else {
			// 反之亦然
			result += (rightMax - height[j])
			j--
		}
	}
	return result
}
