package main

import (
	"fmt"
)

/*
*
解题思路, 典型的单调栈问题，非常简单
使用一个栈，将元素的index放入到栈中，如果发现下一个元素的值比栈顶大，那么当前这个元素就是栈顶值要等的温度，持续性的pop stack，直到发现栈为空或者栈顶的值比当前值大了；
当不满足上述条件时，就将当前值push到栈中，然后继续遍历dailyTemperatures
*/
func main() {
	result := dailyTemperatures([]int{73, 74, 75, 71, 69, 72, 76, 73})
	fmt.Println(result) // [1,1,4,2,1,1,0,0]

	result = dailyTemperatures([]int{30, 40, 50, 60})
	fmt.Println(result) // [1,1,1,0]

	result = dailyTemperatures([]int{30, 60, 90})
	fmt.Println(result) // [1,1,0]

	result = dailyTemperatures([]int{12})
	fmt.Println(result) // [0]

	result = dailyTemperatures([]int{1, 2, 3, 4, 5})
	fmt.Println(result) // [1 1 1 1 0]

	result = dailyTemperatures([]int{5, 4, 3, 2, 1})
	fmt.Println(result) // [0 0 0 0 0]

	result = dailyTemperatures([]int{5, 4, 6, 3, 7})
	fmt.Println(result) // [2 1 2 1 0]
}
func dailyTemperatures(temperatures []int) (result []int) {
	if len(temperatures) == 0 {
		return result
	}

	stack := make([]int, 0)
	result = make([]int, len(temperatures))
	for idx, val := range temperatures {
		for len(stack) > 0 && temperatures[stack[len(stack)-1]] < val {
			topIdx := stack[len(stack)-1]
			result[topIdx] = idx - topIdx
			stack = stack[:len(stack)-1]
		}
		stack = append(stack, idx)
	}
	return result
}
