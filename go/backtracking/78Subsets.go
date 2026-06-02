package main

import "fmt"

/*
*
解题思路：返回数组的所有子集，输入的数字中数字不会出现重复
1 第一种解法很简单，直接采用递归回溯的方式，不过在golang中需要注意的是，append可能会替换掉slice的底层数组，小心递归的过程中，append之后值无法共享到上一级调用中
2 第二种是非递归的解决办法, 基本思想是长度为k的子集一定是在长度为k-1的子集通过添加元素生成的，因此可以利用这点每次都往旧子集中加一个数来构成新的子集
*/
func main() {
	result := subsets([]int{1, 2, 3})
	result2 := subsets2([]int{1, 2, 3})
	fmt.Println(result, "*******", result2) //  [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

	result = subsets([]int{0})
	result2 = subsets2([]int{0})
	fmt.Println(result, "*******", result2) //  [[],[0]]

	result = subsets([]int{1, 2, 3, 4, 5, 6, 7})
	result2 = subsets2([]int{1, 2, 3, 4, 5, 6, 7})
	fmt.Println(result, "*******", result2) //
}

// subsets 最简单的递归回溯式解决办法，不过在golang中需要注意的是，append可能会替换掉slice的底层数组，小心递归的过程中，append之后值无法共享到上一级调用中
func subsets(nums []int) [][]int {
	result := make([][]int, 0)
	// 首先加入空集
	result = append(result, []int{})

	for i := 1; i <= len(nums); i++ {
		tmpResult := make([]int, 0, i*2) // 提前分配好底层数组，避免append的时候底层数组发生变化
		subsetsInternal(nums, i, 0, tmpResult, &result)
	}
	return result
}

func subsetsInternal(nums []int, count int, idx int, item []int, result *[][]int) {
	if count == 0 {
		copiedItem := make([]int, len(item))
		copy(copiedItem, item)
		*result = append(*result, copiedItem) // 避免因为共用底层数组导致结果发生变化
		return
	}

	for i := idx; i < len(nums)-count+1; i++ {
		item = append(item, nums[i])
		subsetsInternal(nums, count-1, i+1, item, result)
		item = item[:len(item)-1]
	}
}

// subsets2 非递归解决办法, 每来一个数，就往旧子集中加入这个数构成新的子集，这样子就能完成所有的子集遍历
func subsets2(nums []int) [][]int {
	result := make([][]int, 0)
	// 首先加入空集
	result = append(result, []int{})
	for _, num := range nums {
		for _, res := range result {
			newResult := copySlice(res)
			newResult = append(newResult, num)
			result = append(result, newResult)
		}
	}
	return result
}

func copySlice(nums []int) []int {
	if len(nums) == 0 {
		return []int{}
	}
	result := make([]int, len(nums))
	copy(result, nums)
	return result
}
