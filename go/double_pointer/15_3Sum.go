package main

import (
	"fmt"
	"slices"
)

/*
*
解题思路：
解法1： 使用三个指针遍历结果，最后在做一下去重即可，该解法给干超时了，用一个map记录每个数字出现的次数，循环的时候只循环i和j，到第三个数的时候从map中找，这样子可以稍微加速这个暴力解法，
但是还不是最优的
解法2：首先对数组进行排序，再选定一个数字，当前的位置记为i，然后j=i+1, k=len(nums)-1，如果nums[i]大于0，那么说明当前三个数的和不可能等于0，流程结束
如果当前的sum为0，那么记录结果，然后尝试对j++, k--，如果nums[j]==nums[j+1]说明当前的值存在重复，直接跳过该数字，k也是同理，以此类推，该解法可大大提升速度,
需要注意的是，当sum比0大的时候，这个时候需要移动j，如果比0小，需要移动k
*/
func main() {
	var (
		nums = []int{-1, 0, 1, 2, -1, -4}
	)
	result := threeSum(nums)
	result2 := threeSum2(nums)
	fmt.Println(result, "---------", result2) // [[-1,-1,2],[-1,0,1]]

	nums = []int{0, 1, 1}
	result = threeSum(nums)
	result2 = threeSum2(nums)
	fmt.Println(result, "---------", result2) // []

	nums = []int{0, 0, 0}
	result = threeSum(nums)
	result2 = threeSum2(nums)
	fmt.Println(result, "---------", result2) // [0,0,0]
}

func threeSum(nums []int) [][]int {
	if len(nums) < 3 {
		return nil
	}

	var (
		result    = make(map[[3]int]interface{}) // 使用map进行去除
		num2Count = make(map[int]int)
		i         = 0
		j         = 1
	)
	for _, val := range nums {
		num2Count[val]++
	}
	for ; i < len(nums)-2; i++ {
		for j = i + 1; j < len(nums)-1; j++ {
			target := 0 - (nums[i] + nums[j])
			count, ok := num2Count[target]
			if !ok {
				continue
			}
			needCount := 1
			if target == nums[i] {
				needCount++
			}
			if target == nums[j] {
				needCount++
			}
			if count >= needCount {
				key := [3]int{nums[i], nums[j], target}
				slices.Sort(key[:]) // 将数组转换为slice，可以进行排序，此时实际上slice引用的还是原来的数组
				result[key] = nil
			}
		}
	}
	res := make([][]int, 0)
	for key := range result {
		res = append(res, key[:])
	}
	return res
}

// threeSum2 更巧妙的一个解法，使用三个指针在有序的数组中快速找到答案
func threeSum2(nums []int) [][]int {
	if len(nums) < 3 {
		return nil
	}

	slices.Sort(nums)
	result := make([][]int, 0)
	// 保证最后能留下三个数
	for i := 0; i < len(nums)-2; i++ {
		if nums[i] > 0 {
			return result
		}
		// 注意，i也需要跳过重复的值，不然最后的结果中可能会出现重复值
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		j := i + 1
		k := len(nums) - 1
		for j < k && j < len(nums)-1 {
			sum := nums[i] + nums[j] + nums[k]
			if sum == 0 {
				result = append(result, []int{nums[i], nums[j], nums[k]})
				j++
				// 此时已经满足条件了，这里为了去除掉重复的数字，还要继续移动j和k
				for j < len(nums)-1 && nums[j] == nums[j-1] && j < k {
					j++
				}
				// 去除掉右边界重复的值
				k--
				for j < k && nums[k] == nums[k+1] {
					k--
				}
			} else if sum < 0 { // 结果比0小，需要增大sum
				j++
			} else { // 结果比0大，需要减小sum
				k--
			}
		}
	}
	return result
}
