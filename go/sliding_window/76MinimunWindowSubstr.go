package main

import "fmt"

/*
*
解题思路：从s中找出能包含所有t字符的最小子串, 注意，子串的字符出现顺序无要求，只要出现了t的所有字符即可, t可能会重复, 大小写字母均包含
题目要求时间复杂度为O(m+n)，且题目保证了只会有一组最小的结果
该问题的题解其实是符合直觉的，但是代码实现起来比较复杂，很容易漏考虑掉corener case，以下为解题步骤
1. 移动右指针，统计子串字符出现的次数，记录当前是否所有字符都已经满足要求，如果满足的话进入第2步
2. 移动左指针，尽可能的尝试缩短子串，直到子串不再满足要求，在进入到第1步，中间需要不断的刷新最小的结果值
*/
func main() {
	result := minWindow("ADOBECODEBANC", "ABC")
	fmt.Println(result) // "BANC"

	result = minWindow("a", "a")
	fmt.Println(result) // "a"

	result = minWindow("a", "aa")
	fmt.Println(result) // ""

	result = minWindow("aaaab", "ab")
	fmt.Println(result) // "ab"

	result = minWindow("ab", "b")
	fmt.Println(result) // "b"

	result = minWindow("ADOBECODEBANC", "ABC")
	fmt.Println(result) // "BANC"

	result = minWindow("bbaa", "aba")
	fmt.Println(result) // "baa"

	result = minWindow("acbbaca", "aba")
	fmt.Println(result) // "baca"

	result = minWindow("cabwefgewcwaefgcf", "cae")
	fmt.Println(result) // "cwae"
}

func minWindow(s string, t string) string {
	if len(s) < len(t) || len(t) == 0 {
		return ""
	}
	if s == t {
		return t
	}

	// 先统计t中每个字符出现的次数
	m := make(map[byte]int)
	for _, v := range t {
		m[byte(v)]++
	}
	var (
		counterMap     = make(map[byte]int)
		satisfiedCount = 0
		result         = ""
		i              = 0
		j              = 0
	)
	for j < len(s) {
		v := s[j]
		if m[v] == 0 { // 跳过无关的字符
			j++
			continue
		}
		counterMap[v]++
		if counterMap[v] == m[v] {
			satisfiedCount++
		}
		// 所有字符已经满足了, 接下来开始移动左指针，尽可能的缩短子串，直到当前的子串不再满足要求在退出
		for satisfiedCount == len(m) { // 这里判断用len(m)而不是len(t)，是因为这里不关心t中的重复字符，重复了几次有counterMap统计
			sz := j - i + 1
			if len(result) == 0 || sz < len(result) {
				result = s[i : j+1]
			}
			v = s[i]
			if m[v] != 0 { // 跳过无关字符
				counterMap[v]--
				if counterMap[v] < m[v] { // 当前这个字符已经无法满足需求，已经是目标位置
					satisfiedCount--
				}
			}
			i++
		}
		j++
	}
	return result
}
