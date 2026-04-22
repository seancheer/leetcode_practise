package main

import "fmt"

/*
*
解题思路, 这道题很简单，遍历思路如下
1. 如果发现target比下一行对应的值大，那么就往下摞一行
2. 否的话往右走
3. 如果发现既不能往下走又不能往右走，那么说明已经无法找到目标值了。返回false即可
*/
func main() {
	result := searchMatrix([][]int{
		{1, 3, 5, 7},
		{10, 11, 16, 20},
		{23, 30, 34, 60},
	}, 3)
	fmt.Println(result) // true

	result = searchMatrix([][]int{
		{1, 3, 5, 7},
		{10, 11, 16, 20},
		{23, 30, 34, 60},
	}, 13)
	fmt.Println(result) // false

	result = searchMatrix([][]int{
		{1, 3, 5, 7},
		{10, 11, 16, 20},
		{23, 30, 34, 60},
	}, 7)
	fmt.Println(result) // true
}

func searchMatrix(matrix [][]int, target int) bool {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return false
	}

	var i, j int
	for {
		if matrix[i][j] == target {
			return true
		}
		if i+1 < len(matrix) && matrix[i+1][j] <= target {
			i++
			continue
		}
		if j+1 < len(matrix[0]) && matrix[i][j+1] <= target {
			j++
			continue
		}
		return false
	}
}
