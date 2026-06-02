package main

import "fmt"

/*
*
解题思路：返回和为k的子数组数量
解法1(容易想出来的暴力解法)
维护一个同样长度的数组，该数组记录了0-i之间的和，记为sum，这样子任意区间和的计算公式如下：

	k = sum[j] - sum[i - 1]

该方法过慢，解法2是对其的一个加速
解法2（优化解法1）
依旧是前缀和的思路，将解法的式子进行一个移项，得到
sum[i-1] = sum[j] - k
上面这个等式意味着，任意的前缀和只要能找到其前面前缀和-k的个数，我们就相当于找到了满足和为k的子数组的数量，而这个只需要遍历一遍就能得到结果，代价就是需要一个hash表来维护前缀和等于x的个数
*/
func main() {
	var (
		nums = []int{1, 1, 1}
		k    = 2
	)
	result := subarraySum(nums, k)
	result2 := subarraySum2(nums, k)
	fmt.Println(result, "--------", result2) // 2

	nums = []int{1, 2, 3}
	k = 3
	result = subarraySum(nums, k)
	result2 = subarraySum2(nums, k)
	fmt.Println(result, "--------", result2) // 2

	nums = []int{1, 2, 3, 4, -4, 0}
	k = 3
	result = subarraySum(nums, k)
	result2 = subarraySum2(nums, k)
	fmt.Println(result, "--------", result2) // 4
}

func subarraySum(nums []int, k int) int {
	if len(nums) == 0 {
		return 0
	}
	var (
		sum    = make([]int, len(nums))
		tmp    = 0
		result = 0
	)
	for idx, num := range nums {
		tmp += num
		sum[idx] = tmp
		if tmp == k {
			result++
		}
	}

	// 有了前缀和之后，开始计算任意区间的子串和
	for i := 1; i < len(nums); i++ {
		if nums[i] == k {
			result++
		}
		// 开始计算子串的和
		for j := i + 1; j < len(nums); j++ {
			tmp = sum[j] - sum[i-1]
			if tmp == k {
				result++
			}
		}
	}
	return result
}

// subarraySum2 对解法1的优化，使用一个hash表记录前缀和的个数
func subarraySum2(nums []int, k int) int {
	if len(nums) == 0 {
		return 0
	}
	var (
		m      = make(map[int]int, 0)
		prefix = 0
		result = 0
	)
	m[0] = 1
	for _, num := range nums {
		prefix += num
		result += m[prefix-k]
		m[prefix]++
	}
	return result
}
