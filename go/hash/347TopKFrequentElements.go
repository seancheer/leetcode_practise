package main

import (
	"container/heap"
	"fmt"
)

/*
*
解题思路：返回出现次数最多排名前两位的数字，题目保证了只会有一组解
解题思路：这题目很简单，维护一个map，统计各个数字出现的频率，如果发现当前数字出现的频率比result里面的大，那么替换掉result里面出现次数小的（可使用最小堆，也可以通过二分查找的方式）
经过测试，二分查找的方式速度非常慢，这是因为golang的slice append的时候底层可能会使用原来的slice array，导致append在原来的slice上修改，只能使用copy的方式修改数组，效率非常的低
更进一步
Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
题目要求必须是O(n)的时间，这个时候就需要通过桶排序的方式来实现了，出现的频率作为桶的下标，但是这个只适合算法题，现实中的情况往往不知道出现的频率是多少，如果是一个很大的数的话，那么将会
迅速耗尽系统内存
*/
func main() {
	result := topKFrequent([]int{1, 1, 1, 2, 2, 3}, 2)
	result2 := topKFrequent2([]int{1, 1, 1, 2, 2, 3}, 2)
	result3 := topKFrequentUsingBucketSort([]int{1, 1, 1, 2, 2, 3}, 2)
	fmt.Println(result, result2, result3) // 1,2

	result = topKFrequent([]int{1}, 1)
	result2 = topKFrequent2([]int{1}, 1)
	result3 = topKFrequentUsingBucketSort([]int{1}, 1)
	fmt.Println(result, result2, result3) // 1

	result = topKFrequent([]int{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)
	result2 = topKFrequent2([]int{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)
	result3 = topKFrequentUsingBucketSort([]int{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)
	fmt.Println(result, result2, result3) // 1,2

}

// //////////////////////////////heap结构/////////////////////////////////
type value struct {
	val   int
	count int
}
type intHeap []*value

func (h *intHeap) Push(x interface{}) {
	*h = append(*h, x.(*value))
}
func (h *intHeap) Pop() interface{} {
	x := (*h)[len(*h)-1]
	*h = (*h)[:len(*h)-1]
	return x
}
func (h intHeap) Len() int {
	return len(h)
}
func (h intHeap) Less(i, j int) bool {
	return h[i].count >= h[j].count
}
func (h intHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }

// 使用最小堆来统计出现次数最多的k个数
func topKFrequent(nums []int, k int) []int {
	if len(nums) == 0 {
		return []int{}
	}

	m := make(map[int]int)
	// 先统计出现的次数
	for _, num := range nums {
		m[num]++
	}
	h := &intHeap{}
	heap.Init(h)
	// 计算top k
	for num, count := range m {
		heap.Push(h, &value{num, count})
	}

	result := make([]int, 0)
	i := 0
	for i < k {
		result = append(result, heap.Pop(h).(*value).val)
		i++
	}
	return result
}

// 使用二分查找的方式来统计出现次数最多的k个数
type valueCounter struct {
	val   int
	count int
}

func topKFrequent2(nums []int, k int) []int {
	if len(nums) == 0 {
		return []int{}
	}
	m := make(map[int]int)
	// 先统计出现的次数
	for _, num := range nums {
		m[num]++
	}
	result := make([]*valueCounter, 0)
	for num, count := range m {
		result = orderedInsert(result, &valueCounter{num, count}, k)
	}

	res := make([]int, 0)
	for _, counter := range result {
		res = append(res, counter.val)
	}
	return res
}

func orderedInsert(result []*valueCounter, v *valueCounter, k int) []*valueCounter {
	if len(result) == 0 {
		result = append(result, v)
		return result
	}

	// 通过二分查找的方式寻找插入位置
	var (
		i = 0
		j = len(result) - 1
	)

	for i < j {
		mid := (i + j) >> 1
		if v.count < result[mid].count {
			j = mid - 1
		} else if v.count > result[mid].count {
			i = mid + 1
		} else {
			i = mid
			break
		}
	}

	var newResult []*valueCounter
	if v.count > result[i].count {
		newResult = make([]*valueCounter, len(result[:i+1]))
		copy(newResult, result[:i+1])
		newResult = append(newResult, v)
		newResult = append(newResult, result[i+1:]...)
	} else {
		newResult = make([]*valueCounter, len(result[:i]))
		copy(newResult, result[:i])
		newResult = append(newResult, v)
		newResult = append(newResult, result[i:]...)
	}

	if len(newResult) > k {
		newResult = newResult[1:]
	}
	return newResult
}

// topKFrequentUsingBucketSort 使用桶排序的方式返回答案，该算法复杂度为O(n)
func topKFrequentUsingBucketSort(nums []int, k int) []int {
	if len(nums) == 0 {
		return []int{}
	}

	m := make(map[int]int)
	maxReq := 0
	for _, num := range nums {
		m[num]++
		maxReq = max(maxReq, m[num])
	}

	// 接下来对出现的频率进行一个桶排序
	bucket := make([][]int, maxReq+1)
	for num, count := range m { // 出现次数可能重复，因此这里使用的二维数组
		bucket[count] = append(bucket[count], num)
	}

	// 接下来从bucket的末尾开始返回结果，注意跳过出现次数为0的位置
	i := 0
	j := len(bucket) - 1
	result := make([]int, 0)
	for j >= 0 && i < k { // bucket访问到末尾或者已经找够了k个值
		if len(bucket[j]) > 0 {
			end := min(len(bucket[j]), k-i)
			result = append(result, bucket[j][:end]...)
		}
		i = len(result)
		j--
	}
	return result
}
