package main

import (
	"fmt"
	"math"
	"slices"
)

// main 下一个更多数3
/**
解题思路：
1 从右边开始遍历数字，找到第一个递减的相邻数字，即第一次出现nums[i] < nums[i+1]的地方，记下这个index i
2 从i的位置开始正向遍历，查找这其中比nums[i]大的最小值，记下位置j
3 交换nums[i]和nums[j]
4 对nums[j:]的数字进行排序，输出最后的结果
*/
func main() {
	result := nextGreaterElement3(1132)
	fmt.Println(result)

	result = nextGreaterElement3(14321)
	fmt.Println(result)

	result = nextGreaterElement3(21)
	fmt.Println(result)

	result = nextGreaterElement3(12)
	fmt.Println(result)
}

func nextGreaterElement3(n int) int {
	if n < 0 {
		return -1
	}
	var result int64
	digits := make([]int, 0)
	num := n
	for num > 0 {
		digits = append([]int{num % 10}, digits...)
		num /= 10
	}
	targetIdx := -1
	biggerValIdx := -1
	for idx := len(digits) - 1; idx > 0; idx-- {
		if digits[idx-1] < digits[idx] {
			targetIdx = idx - 1
			biggerValIdx = idx
			break
		}
	}
	if targetIdx == -1 {
		return -1
	}

	// 开始正向遍历，查找比nums[targetIdx]大的最小值
	biggerVal := digits[biggerValIdx]
	for idx := targetIdx + 1; idx < len(digits); idx++ {
		if digits[idx] < biggerVal && digits[idx] > digits[targetIdx] {
			biggerValIdx = idx
			biggerVal = digits[idx]
		}
	}
	// 交换两个目标值
	val := digits[biggerValIdx]
	digits[biggerValIdx] = digits[targetIdx]
	digits[targetIdx] = val

	// 对剩下的数字进行排序
	remainDigits := digits[targetIdx+1:]
	slices.Sort(remainDigits)
	i := len(digits) - 1
	for idx, digit := range digits {
		tmp := int64(math.Pow10(i))
		result = result + int64(digit)*tmp
		i--
		if idx == targetIdx {
			break
		}
	}
	for _, digit := range remainDigits {
		tmp := int64(math.Pow10(i))
		result = result + int64(digit)*tmp
		i--
	}

	if result > math.MaxInt32 {
		return -1
	}
	return int(result)
}
