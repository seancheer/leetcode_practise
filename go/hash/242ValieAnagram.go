package main

import "fmt"

/*
*
解题思路：这道题很简单，统计每个字母出现的次数，然后在t中如果发现一个字母就-1，当发现某个字母出现的次数为0的时候，返回false即可
该题目的s和t均只包含小写字母
Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
如果是unicode字符，就使用map，而不是一个26大小的数组
*/
func main() {
	var (
		s = "anagram"
		t = "nagaram"
	)
	result := isAnagram(s, t)
	fmt.Println(result) // true

	s = "rat"
	t = "car"
	result = isAnagram(s, t)
	fmt.Println(result) // false
}

func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	if len(s) == 0 {
		return true
	}

	m := make([]int, 26)
	for _, v := range s {
		m[v-'a']++
	}
	for _, v := range t {
		idx := v - 'a'
		if m[idx] <= 0 {
			return false
		}
		m[idx]--
	}
	return true
}
