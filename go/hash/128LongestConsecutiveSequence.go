package main

import "fmt"

/*
*
解题思路: 题目要求时间复杂度为O(n)
1 初始化一个map，每个数字出现一次就记录在map中，map的value为bool，初始化为false
2 第二次遍历nums，然后在map中继续查找当前数字num+1是否在map中出现过，如果没出现过，就继续遍历下一个num，如果出现过，那么最长子序列+1，且把访问过的数字都设置为true，
这样子下一次访问到该数字时，可以直接跳过该数字（因为肯定是之前访问过的子序列）
3 返回最大的最长子序列长度即可
该问题的难点在于如何减少重复计算，比如序列[1,2,3,4]，如果以1作为起点计算最长连续序列，那么以2，3，4作为起点就不需要计算了（一定是前者是的子序列），如何解决这个问题呢？
第二次遍历nums的时候，如果发现当前值val - 1在map中出现过，那么我们直接跳过该值的计算就好了
注意!!!!!! 该题还有个坑点，就是nums可以有重复值，因此第二次遍历的时候不要用原nums，直接使用map即可!!!!!!!!!
*/
func main() {
	result := longestConsecutive([]int{100, 4, 200, 1, 3, 2})
	fmt.Println(result) // 4 [1, 2, 3, 4]

	result = longestConsecutive([]int{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})
	fmt.Println(result) // 9

	result = longestConsecutive([]int{1, 0, 1, 2})
	fmt.Println(result) // 3

	result = longestConsecutive([]int{0, 0, 1, -1})
	fmt.Println(result) // 3

}

func longestConsecutive(nums []int) int {
	if len(nums) < 2 {
		return len(nums)
	}

	m := make(map[int]bool)
	for _, num := range nums {
		m[num] = true
	}

	result := 1
	for num := range m { // 遍历使用去重后的map，不要使用原nums，这样子可以避免因为太多重复值而导致时间超限
		// 说明该值不是连续序列的起点，因此直接跳过，避免重复计算
		if _, ok := m[num-1]; ok {
			continue
		}
		tmp := 1
		num++
		for {
			// 当前数字未出现过，说明不连续了
			if _, ok := m[num]; !ok {
				break
			} else {
				tmp++
				num++
			}
		}
		result = max(result, tmp)
	}
	return result
}
