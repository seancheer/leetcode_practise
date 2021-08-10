package org.seancheer.sort_algorithm;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * 算法练习
 *
 * @author: seancheer
 * @date: 2020/7/5
 **/
public class SortPractise extends LeetCodeParent {
    private static final int ARR_SIZE = 30;
    private static final int UP_BOUND = ARR_SIZE * 10;

    public static void main(String[] args) {
        int[] arr = genArray(ARR_SIZE, UP_BOUND);

        //1冒泡排序
        printArray(arr, "before bundleSort");
        buddleSort(arr);
        printArray(arr, "after bundleSort");

        arr = genArray(ARR_SIZE, UP_BOUND);
        //2插入排序
        printArray(arr, "before insertSort");
        insertSort(arr);
        printArray(arr, "after insertSort");

        //3归并排序, 这里实现了两种方式，一种自顶向下（递归），一种自底向上（迭代）
        /**
         * merge sort
         * 两种实现方式，可以参考这里 https://www.cnblogs.com/nullzx/p/5968170.html
         * 1 自顶向下，采用递归的实现方式
         * 2 自底向上，采用迭代的方式
         */
        arr = genArray(ARR_SIZE, UP_BOUND);
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        printArray(arr, "before mergeSort(up2Bottom)");
        //1 自顶向下，采用递归的实现方式
        mergeSortUp2Bottom(arr);
        printArray(arr, "after mergeSort(up2Bottom)");

        printArray(arrCopy, "before mergeSort(bottom2Up)");
        //2 自底向上，采用迭代的方式
        mergeSortBottom2Up(arrCopy);
        printArray(arrCopy, "after mergeSort(bottom2Up)");

        //4基数排序
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before radixSort");
        radixSort(arr);
        printArray(arr, "after radixSort");

        //5计数排序
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before countSort");
        countSort(arr);
        printArray(arr, "after countSort");

        //6希尔排序（高级的插入排序）
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before shellSort");
        shellSort(arr);
        printArray(arr, "after shellSort");


        //7堆排序
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before heapSort");
        heapSort(arr);
        printArray(arr, "after heapSort");

        //8快速排序
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before quickSort");
        quickSort(arr);
        printArray(arr, "after quickSort");

        //9桶排序
        arr = genArray(ARR_SIZE, UP_BOUND);
        printArray(arr, "before bucketSort");
        bucketSort(arr);
        printArray(arr, "after bucketSort");
    }

    /**
     * bundle sort
     *
     * @param arr
     */
    private static void buddleSort(int[] arr) {
        int len = arr.length;
        boolean isOrder = true;
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len - i - 1; ++j) {
                //后一个比前一个小
                if (arr[j] > arr[j + 1]) {
                    isOrder = false;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }

            if (isOrder) {
                System.out.println("Is in order. No need to sort!");
                break;
            }
        }

    }


    /**
     * insert sort
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        int len = arr.length;
        int j = 0;
        for (int i = 1; i < len; ++i) {
            int cur = arr[i];
            for (j = i; j > 0 && arr[j - 1] > cur; --j) {
                arr[j] = arr[j - 1];
            }
            arr[j] = cur;
        }
    }

    /**
     * 自底向上的实现方式，迭代方式实现
     *
     * @param arr
     */
    public static void mergeSortBottom2Up(int[] arr) {
        int[] sortedArr = new int[arr.length];
        int len = arr.length;
        //类似于递归模式每次分一半，对半进行排序的思想，gap就是控制这个的
        for (int gap = 1; gap < len; gap <<= 1) {
            int i = 0;
            while (i < len) {
                mergeSortBottom2UpInternal(arr, i, i + gap, i + gap, i + gap + gap, sortedArr);
                //因为每次都取一对，所以这里需要 + 2 * gap
                i = i + gap + gap;
            }
        }
    }

    /**
     * 对每一部分的数组进行排序，左闭右开
     *
     * @param arr
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @param sortedArr
     */
    private static void mergeSortBottom2UpInternal(int[] arr, int start1, int end1, int start2, int end2, int[] sortedArr) {
        int i = start1, j = start2;
        int k = start1;
        int len = arr.length;
        while (i < end1 && j < end2 && i < len && j < len) {
            sortedArr[k++] = (arr[i] <= arr[j] ? arr[i++] : arr[j++]);
        }

        while (i < end1 && i < len) {
            sortedArr[k++] = arr[i++];
        }
        while (j < end2 && j < len) {
            sortedArr[k++] = arr[j++];
        }

        for (i = start1; i < end2 && i < len; ++i) {
            arr[i] = sortedArr[i];
        }
    }

    /**
     * 自顶向下的mergeSort，递归方式实现
     *
     * @param arr
     */
    private static void mergeSortUp2Bottom(int[] arr) {
        int[] sortedArr = new int[arr.length];
        mergeSortInternal(arr, sortedArr, 0, arr.length - 1);
    }

    /**
     * merge sort internal
     * 递归进行排序
     *
     * @param arr
     * @param sorted
     * @param left
     * @param right
     */
    private static void mergeSortInternal(int[] arr, int[] sorted, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) >> 1;
        mergeSortInternal(arr, sorted, left, mid);
        mergeSortInternal(arr, sorted, mid + 1, right);
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            sorted[k++] = ((arr[i] <= arr[j]) ? arr[i++] : arr[j++]);
        }

        while (i <= mid) {
            sorted[k++] = arr[i++];
        }

        while (j <= right) {
            sorted[k++] = arr[j++];
        }

        for (i = left; i <= right; ++i) {
            arr[i] = sorted[i];
        }
    }


    /**
     * radix sort
     *
     * @param arr
     */
    private static void radixSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        final int bitSum = 10;
        int maxItem = getMaxItem(arr);
        int bitForMaxItem = howManyBit(maxItem);
        int len = arr.length;

        for (int i = 0; i < bitForMaxItem; ++i) {
            int[] countArr = new int[bitSum];
            int[] sortedArr = new int[arr.length];
            for (int j = 0; j < len; ++j) {
                int tmp = arr[j];
                tmp /= ((int) (Math.pow(10.0, i)));
                tmp %= 10;
                countArr[tmp]++;
            }

            //为每一个基数下面计算编号
            for (int j = 1; j < bitSum; ++j) {
                countArr[j] = countArr[j] + countArr[j - 1];
            }

            //根据编号对数据进行初步排序，注意这里需要反着来，这样子可以保证较大的数据在后面位置
            for (int j = len - 1; j >= 0; --j) {
                int tmp = arr[j];
                tmp /= ((int) (Math.pow(10.0, i)));
                tmp %= 10;
                sortedArr[countArr[tmp] - 1] = arr[j];
                --countArr[tmp];
            }

            for (int j = 0; j < len; ++j) {
                arr[j] = sortedArr[j];
            }
        }
    }

    /**
     * count sort
     *
     * @param arr
     */
    private static void countSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        int maxItem = getMaxItem(arr);
        int[] countArr = new int[maxItem + 1];
        for (int item : arr) {
            countArr[item]++;
        }

        int j = 0;
        for (int i = 0; i <= maxItem; ++i) {
            while (countArr[i]-- > 0) {
                arr[j++] = i;
            }
        }
    }

    /**
     * shell sort
     *
     * @param arr
     */
    private static void shellSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int len = arr.length;
        int i = 0, j = 0, gap = 0;
        for (gap = len >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < len; ++i) {
                int tmp = arr[i];
                //注意这里实现插入排序时候的小小技巧
                for (j = i - gap; j >= 0 && arr[j] > tmp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = tmp;
            }
        }
    }

    /**
     * heap sort
     *
     * @param arr
     */
    private static void heapSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            buildMaxHeap(arr, i);
        }

        //最大堆已经建立好了，接下来开始进行排序交换
        for (int i = len - 1; i > 0; --i) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            adjustHeap(arr, i);
        }
    }

    /**
     * 首先需要建立堆
     *
     * @param arr
     */
    private static void buildMaxHeap(int[] arr, int size) {
        //一直循环进行处理，职到
        for (int i = size; i > 0 && arr[parentIdx(i)] < arr[i]; i = parentIdx(i)) {
            int tmp = arr[parentIdx(i)];
            arr[parentIdx(i)] = arr[i];
            arr[i] = tmp;
        }
    }

    /**
     * 每交换一次就需要重新调整
     *
     * @param arr
     */
    private static void adjustHeap(int[] arr, int size) {
        int i = 0;
        while (true) {
            int leftIdx = leftChild(i);
            int rightIdx = rightChild(i);
            if (leftIdx >= size && rightIdx >= size) {
                break;
            }

            int swapIdx = i;
            if (leftIdx < size && arr[leftIdx] > arr[swapIdx]) {
                swapIdx = leftIdx;
            }

            if (rightIdx < size && arr[rightIdx] > arr[swapIdx]) {
                swapIdx = rightIdx;
            }

            //already is the maxinum
            if (i == swapIdx) {
                break;
            }

            int tmp = arr[i];
            arr[i] = arr[swapIdx];
            arr[swapIdx] = tmp;
            i = swapIdx;
        }
    }

    /**
     * parent index
     *
     * @param idx
     * @return
     */
    private static int parentIdx(int idx) {
        return (0 == idx) ? 0 : ((idx - 1) / 2);
    }

    /**
     * left child index
     *
     * @param idx
     * @return
     */
    private static int leftChild(int idx) {
        return 2 * idx + 1;
    }

    /**
     * right child index
     *
     * @param idx
     * @return
     */
    private static int rightChild(int idx) {
        return leftChild(idx) + 1;
    }

    /**
     * quick sort
     *
     * @param arr
     */
    private static void quickSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        quickSortInternal(arr, 0, arr.length - 1);
    }

    /**
     * quick sort internal
     * 主要控制循环的地方
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSortInternal(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIdx = quickSortPartition(arr, left, right);
        quickSortInternal(arr, left, pivotIdx - 1);
        quickSortInternal(arr, pivotIdx + 1, right);
    }

    /**
     * 寻找枢纽元的方法
     * 默认取最右边的数为枢纽元
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int quickSortPartition(int[] arr, int left, int right) {
        if (left >= right) {
            return left;
        }

        int i = left;
        int j = right;
        int pivot = arr[right];
        while (i < j) {
            while (i < j && arr[i] <= pivot) {
                ++i;
            }

            while (i < j && arr[j] >= pivot) {
                --j;
            }

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;

        //将枢纽元放回到正确的位置上
        return i;
    }

    /**
     * BucketNode for bucketsort.
     */
    static class BucketNode {
        public int val;
        public BucketNode next;

        public BucketNode(int v) {
            this.val = v;
            this.next = null;
        }
    }

    /**
     * 桶排序
     *
     * @param arr
     */
    private static void bucketSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        int maxItem = getMaxItem(arr);
        final int bitSum = 10;
        int maxItemBits = howManyBit(maxItem);
        BucketNode[] nodes = new BucketNode[bitSum];
        for (int i = 0; i < bitSum; ++i) {
            nodes[i] = new BucketNode(Integer.MAX_VALUE);
        }

        for (int item : arr) {
            //根据数组最大值的位放到对应的桶里面
            int curBit = getSpecificBit(item, maxItemBits);
            BucketNode prev = nodes[curBit];
            BucketNode cur = prev.next;

            while (null != cur && cur.val < item) {
                prev = cur;
                cur = cur.next;
            }

            BucketNode newItem = new BucketNode(item);
            prev.next = newItem;
            newItem.next = cur;
        }

        //将对应位置放在了桶里面，现在开始进行统计和排序
        int count = 0;
        for (BucketNode item : nodes) {
            BucketNode cur = item.next;
            while (null != cur) {
                arr[count++] = cur.val;
                cur = cur.next;
            }
        }
    }
}
