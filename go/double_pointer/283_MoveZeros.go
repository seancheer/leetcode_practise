package main

import "fmt"

/*
*
解题思路：将0值移动到数组的末尾，但是不能破坏非0值原来的顺序，题目要求最小话操作次数，并且必须在原数组上进行更改
维护两个指针，如果指向0，一个指向非0，然后交换两个指针的值，将i移动到0值，将j移动到非0值即可，当j或者i达到末尾的时候停下来
注意边界情况，错误的将[1,0]给交换了，i必须在j的前面
*/
func main() {
	var (
		nums = []int{0, 1, 0, 3, 12}
	)

	moveZeroes(nums)
	fmt.Println(nums) // [1,3,12,0,0]

	nums = []int{0}
	moveZeroes(nums)
	fmt.Println(nums) // [0]

	nums = []int{1, 0}
	moveZeroes(nums)
	fmt.Println(nums) // [1,0]

	nums = []int{0, 1}
	moveZeroes(nums)
	fmt.Println(nums) // [1,0]

	nums = []int{1, 0, 1}
	moveZeroes(nums)
	fmt.Println(nums) // [1,1,0]
}

func moveZeroes(nums []int) {
	if len(nums) <= 1 {
		return
	}

	var (
		i = 0
		j = 0
	)

	for i < len(nums) && j < len(nums) {
		for i < len(nums) && nums[i] != 0 {
			i++
		}

		j = i + 1 // j必须保证在i的后面
		for j < len(nums) && nums[j] == 0 {
			j++
		}
		// 交换i和j的值
		if i < len(nums) && j < len(nums) {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
}
