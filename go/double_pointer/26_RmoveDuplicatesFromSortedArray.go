package main

import "fmt"

/*
*
解题思路：这道题要求就地修改，不能另外生成一个数组，思路很简单，使用两个指针，一个指针指向有效数组的部分，一个指针往后移动，如果发送重复的，则把这个数字赋值给前面的指针，前面的指针后移，
后面的指针也继续往后移，直到出现不重复的值
实现的时候注意i和j的初始值
*/
func main() {
	var (
		nums = []int{1, 1, 2}
	)
	result := removeDuplicates(nums)
	fmt.Println(result) // 2

	nums = []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
	result = removeDuplicates(nums)
	fmt.Println(result) // 5 [0,1,2,3,4,_,_,_,_,_]
}

func removeDuplicates(nums []int) int {
	if len(nums) <= 1 {
		return len(nums)
	}

	var (
		i = 1
		j = 1
	)
	for j < len(nums) {
		if nums[j] == nums[i-1] {
			j++
			continue
		}
		nums[i] = nums[j]
		i++
		j++
	}
	return i
}
