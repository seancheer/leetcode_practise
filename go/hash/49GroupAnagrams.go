package main

import (
	"fmt"
	"slices"
)

/*
*
解题思路: 该题的所有字符串都是小写，需要把组成字母相同的字符串分到一组
第一种解法很简单，暴力算法，对每一个字符串进行排序，如果发现排序后的结果作为key值在map中出现过，那么将当前字符串加入到该分组，否则的话则将排序后的结果作为key值放在map中
第二种解法类似于第一种解法，不同的是这个解法的key值是每个字母出现的次数，即用一个大小为26的数组作为key值，这样子省去了排序的过程，速度也会更快
*/
func main() {
	result := groupAnagrams([]string{"eat", "tea", "tan", "ate", "nat", "bat"})
	result2 := groupAnagrams2([]string{"eat", "tea", "tan", "ate", "nat", "bat"})
	fmt.Println(result, result2) // [["bat"],["nat","tan"],["ate","eat","tea"]]

	result = groupAnagrams([]string{""})
	result2 = groupAnagrams2([]string{""})
	fmt.Println(result, result2) // [“”]

	result = groupAnagrams([]string{"a"})
	result2 = groupAnagrams2([]string{"a"})
	fmt.Println(result, result2) // [["a"]]
}

// groupAnagrams 暴力解法，先将字符串排序，然后排序后的结果作为map的key值
func groupAnagrams(strs []string) [][]string {
	if len(strs) == 0 {
		return [][]string{}
	}
	m := make(map[string][]string)
	for _, str := range strs {
		arr := []byte(str)
		slices.Sort(arr)
		sortedStr := string(arr)
		if _, ok := m[sortedStr]; !ok {
			m[sortedStr] = []string{str}
		} else {
			m[sortedStr] = append(m[sortedStr], str)
		}
	}

	result := make([][]string, 0)
	for _, values := range m {
		result = append(result, values)
	}
	return result
}

// groupAnagrams2 一个大小为26的数组作为key值，go中数组是可以比较的，因此可以直接作为key值，无序二次处理
func groupAnagrams2(strs []string) [][]string {
	if len(strs) == 0 {
		return [][]string{}
	}

	// 注意，[]int不能作为key，即map[[]int]string是非法的，但是固定大小的slice是可以作为key值的
	m := make(map[[26]int][]string, 0)
	for _, str := range strs {
		key := [26]int{}
		for _, c := range str {
			key[c-'a']++
		}
		m[key] = append(m[key], str)
	}

	result := make([][]string, 0)
	for _, values := range m {
		result = append(result, values)
	}
	return result
}
