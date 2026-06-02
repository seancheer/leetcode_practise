package main

import (
	"fmt"
)

/*
*
解题思路：返回一个相同长度的数组，该数组的位置存储了除了index之外的其他数据的乘积，注意，题目要求必须O(n)的时间且不能使用除法
维护两个数组，一个记录正序数组的乘积nums1，另外一个维护逆序数组的乘积nums2，那么最终的num[i] = nums1[i-1]*nums2[i+1], 注意处理边界index==0和index==len(nums) - 1的边界情况
该思路可以优化成1个乘积数组，先只计算1个正向的乘积数组，然后计算结果的时候，将反向的乘积结果只保存在一个int里面，然后得到最终的结果
*/
func main() {
	var (
		nums = []int{1, 2, 3, 4}
	)
	result := productExceptSelf(nums)
	fmt.Println(result) // 24,12,8,6

	nums = []int{-1, 1, 0, -3, 3}
	result = productExceptSelf(nums)
	fmt.Println(result) // 0,0,9,0,0
}

func productExceptSelf(nums []int) []int {
	if len(nums) < 1 {
		return nums
	}

	product := make([]int, len(nums))
	for idx := range nums {
		if idx == 0 {
			product[0] = nums[idx]
		} else {
			product[idx] = product[idx-1] * nums[idx]
		}
	}
	// 统计最终的结果
	rightProduct := 1
	for idx := len(nums) - 1; idx >= 0; idx-- {
		if idx > 0 {
			oldVal := nums[idx]
			nums[idx] = product[idx-1] * rightProduct
			rightProduct *= oldVal
		} else {
			nums[idx] = rightProduct
		}
	}
	return nums
}
