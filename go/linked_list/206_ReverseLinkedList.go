package main

import "fmt"

/*
*
解题思路：翻转链表，该问题很简单
使用一个栈，然后弹出该栈即可
解法2：原地进行翻转
*/
func main() {
	head := constructLinedList([]int{1, 2, 3, 4, 5})
	head2 := constructLinedList([]int{1, 2, 3, 4, 5})
	result := reverseList(head)
	result2 := reverseList2(head2)
	printLinkedList(result)
	printLinkedList(result2)

	head = constructLinedList([]int{1, 2})
	head2 = constructLinedList([]int{1, 2})
	result = reverseList(head)
	result2 = reverseList(head2)
	printLinkedList(result)
	printLinkedList(result2)
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	s := make([]*ListNode, 0)
	for head != nil {
		s = append(s, head)
		head = head.Next
	}

	var (
		result *ListNode
	)
	for i := len(s) - 1; i >= 0; i-- {
		if result == nil {
			result = s[i]
			continue
		}
		result.Next = s[i]
		s[i].Next = nil
		result = s[i]
	}
	return s[len(s)-1]
}

// reverseList2 原地翻转链表
func reverseList2(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	prev := head
	cur := head.Next
	head.Next = nil
	for cur != nil {
		newCur := cur.Next
		cur.Next = prev
		prev = cur
		cur = newCur
	}
	return prev
}

// 以下是辅助函数
func constructLinedList(nums []int) *ListNode {
	if len(nums) == 0 {
		return nil
	}

	head := &ListNode{Val: nums[0]}
	current := head
	for i := 1; i < len(nums); i++ {
		current.Next = &ListNode{Val: nums[i]}
		current = current.Next
	}
	return head
}

func printLinkedList(head *ListNode) {
	for head != nil {
		fmt.Printf("%v\t", head.Val)
		head = head.Next
	}
	fmt.Println()
}

type ListNode struct {
	Val  int
	Next *ListNode
}
