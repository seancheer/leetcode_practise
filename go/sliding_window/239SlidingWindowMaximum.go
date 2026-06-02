package main

import (
	"container/heap"
	"fmt"
)

/*
*
解题思路：窗口的大小为k，将滑动窗口从最左边移动到最右边，要求返回每个窗口里的最大值
解法1：
最直观的想法，维护一个最大堆，堆里面存储nums的index和其对应的value，每移动一个窗口，将最新的数字加入到堆中，接下来开始查看堆，如果堆顶的数index不再窗口范围之类，则直接跳过，直到堆顶的元素在窗口范围之类, 该算法的时间复杂度为
nlog(n)，虽然最终能够ac，但是不是最优的解法
解法2：
依旧维护一个窗口数组，不过类似于单调栈的思想，当窗口最右边的数字加入到窗口后，将窗口里面所有小于该值的全部删除，大于等于该值的不动（这样子可以保证窗口数组一定是递减的；
如果发现最左边的数字等于窗口的最左边，移除掉该数字（最左边的数被淘汰了），每次移动一格，因为窗口数组是递减的，因此窗口数组的0号位就是最大值
*/
func main() {
	var (
		nums = []int{1, 3, -1, -3, 5, 3, 6, 7}
		k    = 3
	)
	result := maxSlidingWindow(nums, k)
	result2 := maxSlidingWindow2(nums, k)
	fmt.Println(result, "+++++", result2) // 3,3,5,5,6,7

	nums = []int{1}
	k = 1
	result = maxSlidingWindow(nums, k)
	result2 = maxSlidingWindow2(nums, k)
	fmt.Println(result, "+++++", result2) // 1

	nums = []int{9, 8, 7, 6, 5, 4}
	k = 2
	result = maxSlidingWindow(nums, k)
	result2 = maxSlidingWindow2(nums, k)
	fmt.Println(result, "+++++", result2) // 9 8 7 6 5

}

type value struct {
	nums []int
	val  int
	idx  int
}
type indexHeap []*value

func (h *indexHeap) Push(x interface{}) {
	*h = append(*h, x.(*value))
}
func (h *indexHeap) Pop() interface{} {
	x := (*h)[len(*h)-1]
	*h = (*h)[:len(*h)-1]
	return x
}
func (h indexHeap) Len() int {
	return len(h)
}
func (h indexHeap) Less(i, j int) bool {
	return h[i].nums[h[i].idx] >= h[j].nums[h[j].idx]
}
func (h indexHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }

// maxSlidingWindow 最直观的想法，维护一个最大堆，堆里面存储nums的index和其对应的value，每移动一个窗口，将最新的数字加入到堆中，接下来开始查看堆，如果堆顶的数index不再窗口范围之类，则直接跳过，直到堆顶的元素在窗口范围之类
func maxSlidingWindow(nums []int, k int) []int {
	if len(nums) == 0 || k == 0 {
		return []int{}
	}
	h := &indexHeap{}
	heap.Init(h)
	i := 0
	for ; i < k; i++ {
		heap.Push(h, &value{nums, nums[i], i})
	}

	result := make([]int, 0)
	result = append(result, (*h)[0].val)

	for ; i < len(nums); i++ {
		leftIdx := i - k
		heap.Push(h, &value{nums, nums[i], i})
		// 堆顶第一个在窗口范围内的数即为目标值
		for (*h)[0].idx <= leftIdx {
			_ = heap.Pop(h) // 当前值已经不在窗口范围内，直接跳过即可
		}
		result = append(result, (*h)[0].val)
	}
	return result
}

// maxSlidingWindow2 对于窗口数组采用类似于单调栈的思想, 该算法是最优的解法s
func maxSlidingWindow2(nums []int, k int) []int {
	if len(nums) == 0 || k == 0 {
		return []int{}
	}
	window := make([]int, 0)
	result := make([]int, 0)
	for idx, val := range nums {
		window = addVal2Window(window, val)
		if idx < k-1 {
			continue
		}
		if idx-k >= 0 {
			if window[0] == nums[idx-k] { //移除掉最左边的数字
				window = window[1:]
			}
		}
		result = append(result, window[0])
	}
	return result
}

func addVal2Window(window []int, val int) []int {
	idx := len(window) - 1
	for idx >= 0 && window[idx] < val { // 因为窗口数组是递减的，因此这里只需要循环到第一个>=val的地方
		idx--
	}
	return append(window[:idx+1], val)
}
