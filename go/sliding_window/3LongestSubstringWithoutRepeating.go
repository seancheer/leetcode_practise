package main

import "fmt"

/*
*
解题思路：查找最长的无重复子串（不是子序列， 子序列需要动态规划）
该题是一个很明显的滑动窗口问题，维护两个指针start和end，以及一个map，key-value为char -> index，遍历字符串，如果发现该字符在map中之前出现过，则将start置为该字符位置的下一个位置作为新的起点
如果没出现过，则刷新下当前最大子串的长度
最后，都需要将该字符的当前位置记录到map中
需要注意一个细节！！！！！就是滑动左指针的时候，由于map不会清理旧值，因此发现当前字符在map中的时候，还必须判断index是否在[start, end)之间，小于start的字符是因为map
没清理导致的（无需清理map，因为代价太高）
*/
func main() {
	result := lengthOfLongestSubstring("abcabcbb")
	fmt.Println(result) // 3

	result = lengthOfLongestSubstring("bbbbb")
	fmt.Println(result) // 1

	result = lengthOfLongestSubstring("pwwkew")
	fmt.Println(result) // 3

	result = lengthOfLongestSubstring("tmmzuxt")
	fmt.Println(result) // 5

}

func lengthOfLongestSubstring(s string) int {
	if len(s) <= 1 {
		return len(s)
	}
	m := make(map[byte]int) // char -> index的映射

	var (
		start  = 0
		end    = 1
		result = 1
	)

	m[s[start]] = start
	for end < len(s) {
		curVal := s[end]
		if _, ok := m[curVal]; ok {
			start = max(start, m[curVal]+1) // 这个解法更加精简，无脑尝试更新startIdx，这个判断的巧妙之处在于它避免了不在[start,end)范围之类的重复字符（因为m不会清理旧值）
		}
		result = max(result, end-start+1)
		m[curVal] = end
		end++
	}
	return result
}
