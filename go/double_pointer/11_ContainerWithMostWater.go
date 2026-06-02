package main

import (
	"fmt"
	"slices"
)

/*
*
解题思路：找出最大盛水的面积，该问题是一个很经典的双指针问题，使用两个指针分别指向数组的开头和结尾，记为i和j
1 计算当前可以盛水的面积
2 如果nums[i] < nums[j]，那么i++，记录当前的盛水面积，更新大值，反之亦然
3 如果两个都相等的话，那么随便移动一个指针即可
*/
func main() {
	var (
		height = []int{1, 8, 6, 2, 5, 4, 8, 3, 7}
	)
	result := maxArea(height)
	fmt.Println(result) // 49

	height = []int{1, 1}
	result = maxArea(height)
	fmt.Println(result) // 1

	height = []int{1, 2}
	result = maxArea(height)
	fmt.Println(result) // 1
}

func maxArea(height []int) int {
	if len(height) == 0 {
		return 0
	}

	var (
		i      = 0
		j      = len(height) - 1
		result = 0
	)
	for i <= j {
		h := slices.Min([]int{height[i], height[j]})
		result = slices.Max([]int{result, h * (j - i)})
		if height[i] < height[j] {
			i++
		} else {
			j--
		}
	}
	return result
}
