package main

import "fmt"

/*
*
注意，该问题的题目要求是保证一定有解且必然只有一组解，所以不用考虑无解的场景
解题思路: 因为要返回符合条件结果的index，因此排序肯定是行不通的
1. 首先构造一个map，键值对为num -> [index array]
2. 遍历原始数组，查找当前num和target - num寻找结果,  如果未找到target - num，那么将当前值放入到map中，如果找到了则直接输出结果
该解法只需要遍历一次即可
*/
func main() {
	result := twoSum([]int{2, 7, 11, 15}, 9)
	fmt.Println(result) // [0,1]

	result = twoSum([]int{3, 2, 4}, 6)
	fmt.Println(result) // [1,2]

	result = twoSum([]int{3, 3}, 6)
	fmt.Println(result) // [0,1]
}

func twoSum(nums []int, target int) []int {
	if len(nums) == 0 {
		return nil
	}
	m := make(map[int]int)
	for idx, val := range nums {
		// 未查找到目标对象
		if _, ok := m[target-val]; !ok {
			m[val] = idx
			continue
		}
		return []int{m[target-val], idx}
	}
	return nil
}
