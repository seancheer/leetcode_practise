package main

import "fmt"

/*
*
注意，该问题的题目要求是保证一定有解且必然只有一组解，所以不用考虑无解的场景
解题思路: 该问题完全可以使用第一个版本的TwoSum的解法解决，但是该题是排好序的，因此原则上可以采用双指针的解法
更好的解题思路：利用双指针的解法，如果发现当前值比目标值小，那么很明显移动左指针，如果发现当前的值比目标值大，那么移动右指针，这个是一个很简单的逻辑问题
*/
func main() {
	result := twoSum2([]int{2, 7, 11, 15}, 9)
	result2 := twoSum2UsingTwoPointers([]int{2, 7, 11, 15}, 9)
	fmt.Println(result, result2) // [1, 2]

	result = twoSum2([]int{2, 3, 4}, 6)
	result2 = twoSum2UsingTwoPointers([]int{2, 3, 4}, 6)
	fmt.Println(result, result2) // [1, 3]

	result = twoSum2([]int{-1, 0}, -1)
	result2 = twoSum2UsingTwoPointers([]int{-1, 0}, -1)
	fmt.Println(result, result2) // [1, 2]
}

func twoSum2(numbers []int, target int) []int {
	if len(numbers) < 2 {
		return nil
	}

	m := make(map[int]int)
	for idx, val := range numbers {
		if _, ok := m[target-val]; !ok {
			m[val] = idx
			continue
		}

		return []int{m[target-val] + 1, idx + 1}
	}
	return nil
}

// twoSum2UsingTwoPointers 双指针解法，这个问题其实很好证明，比如数组为[a,b,c,d]，当a+d小于target的时候，完全可以放心的右移左指针，因为
// 此时左指针左边的值都比左指针当前的值小，那么他们的和也一定比左指针当前的值+右指针当前的值的和小，即左指针左边的数字不可能会是答案，右指针同理
func twoSum2UsingTwoPointers(numbers []int, target int) []int {
	if len(numbers) < 2 {
		return nil
	}

	var (
		i = 0
		j = len(numbers) - 1
	)

	for i < j {
		if numbers[i]+numbers[j] == target {
			return []int{i + 1, j + 1}
		}

		if numbers[i]+numbers[j] > target {
			j--
		} else {
			i++
		}
	}
	return nil
}
