
package cn.dark.sort;

import java.util.Arrays;

/**
 * @author dark
 * @date 2019-12-25
 */
public class MySort {

    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 4, 2, 1, 0, 0};
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        radixSort(arr);
    }

    /*******************非比较交换*******************/

    private static void radixSort(int[] arr) {
        int max = getMax(arr);
        // 对每个位使用计数排序
        for (int exp = 1; max / exp  > 0; exp *= 10) {
            countingSortWithExp(arr, exp * 10);
        }
    }

    private static void countingSortWithExp(int[] arr, int exp) {
        int[] countArr = new int[10];
        for (int value : arr) {
            countArr[value % exp]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[--countArr[arr[i] % exp]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }

    private static void bucketsSort(int[] arr, int bucketSize) {
        int min = arr[0], max = arr[0];
        for (int value : arr) {
            if (min > value) {
                min = value;
                continue;
            }

            if (max < value) {
                max = value;
                continue;
            }
        }

        // 计算需要桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        // 记录每个桶实际存储个数
        int[] indexes = new int[bucketCount];
        for (int value : arr) {
            // 计算当前元素所属桶索引
            int bucketIndex = (value - min) / bucketCount;
            // 确保容量足够
            ensureCapacity(buckets, bucketIndex, indexes[bucketIndex]);
            // 将元素填入到桶末端，并更新桶的实际容量
            buckets[bucketIndex][indexes[bucketIndex]++] = value;
        }

        // 对每个桶使用快速排序后复制回原数组
        int i = 0;
        for (int j = 0; j < buckets.length; j++) {
            if (indexes[j] == 0) {
                continue;
            }
            quickSort(buckets[j], indexes[j]);
            for (int k = 0; k < indexes[j]; k++) {
                arr[i++] = buckets[j][k];
            }
        }
    }

    private static void ensureCapacity(int[][] buckets, int bucketIndex, int size) {
        int[] bucket = buckets[bucketIndex];
        if (bucket.length > size) {
            return;
        }

        int[] newBucket = new int[bucket.length * 2];
        for (int i = 0; i < bucket.length; i++) {
            newBucket[i] = bucket[i];
        }
        buckets[bucketIndex] = newBucket;
    }

    private static void countingSort(int[] arr, int n) {
        // 获取数组中最大值，并根据该值构建一个新的数组
        int max = getMax(arr);
        int[] countArr = new int[max + 1];
        // 将原数组的元素作为新数组的下标，每出现一次计数就+1
        for (int i = 0; i < n; i++) {
            countArr[arr[i]]++;
        }

        // 累加计数数组，这样能方便得出下标在有序数组的索引
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        // 输出有序数组
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[--countArr[arr[i]]] = arr[i];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /*******************比较交换********************/

    private static void quickSort(int[] arr, int n) {
        quickSort(arr, 0, n - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition2(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static int partition1(int[] arr, int l, int r) {
        // 选取第一个元素为分界点
        int pivot = arr[l];
        int i = l, j = r;
        while (i < j) {
            // 从后向前找，比分界点小的元素放到前面去
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }

            // 从前往后找，比分界点大的元素放到后面去
            while (i < j && arr[i] < pivot) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }

        // 最后将分界点填入，这样比其小的数都在左边，比其大的数都在右边
        arr[i] = pivot;
        return i;
    }

    private static int partition2(int[] arr, int l, int r) {
        // 取最后一个元素为分界点
        int pivot = arr[r];
        // 取i和j两个指针，i表示已处理区间的尾部
        int i = l, j = l;
        for (; j < r; j++) {
            /**
             * 每次从未处理区间取一个数和分区点比较，如果比其小则加入已处理区间尾部，
             * 否则只需要忽略继续取下一个数判断即可
             */
            if (arr[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int temp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // 最后将分区点填入已处理区间的尾部
        swap(arr, i, r);
        return i;
    }

    private static void mergeSort(int[] arr, int n) {
        mergeSort(arr, 0 , n - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // 取中点，防止l+r超出int范围，所以采用以下方法
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        mergeWithSentinel(arr, l, mid, r);
    }

    /**
     * 普通实现
     */
    private static void mergeWithCommon(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        // 比较左右两个区间将小的元素放入到temp,直到其中一个区间比较完，取“<=”是为了保证稳定性
        while (i <= mid && j <= r) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        // 将未比较完的数组元素填入temp
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= r) {
            temp[k++] = arr[j++];
        }

        // 最后将其复制到原数组相应的位置上
        for (int m = 0; m < temp.length; m++) {
            arr[l + m] = temp[m];
        }
    }

    /**
     * 哨兵实现
     */
    private static void mergeWithSentinel(int[] arr, int l, int mid, int r) {
        int[] left = new int[mid - l + 2];
        int[] right = new int[r - mid + 1];
        // 数组的最后一个元素是用来存哨兵的
        for (int i = 0; i < mid - l + 1; i++) {
            left[i] = arr[l + i];
        }
        for (int i = 0; i < r - mid; i++) {
            right[i] = arr[mid + i + 1];
        }

        // 添加哨兵
        left[mid - l + 1] = Integer.MAX_VALUE;
        right[r - mid] = Integer.MAX_VALUE;

        int i = 0, j = 0, k = l;
        while (k <= r) {
            arr[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
    }

    /**
     * 希尔排序，不稳定，可以简单的想象为阵列且宽为gap，
     * gap的选取会影响算法的性能
     */
    private static void shellSort(int[] arr, int n) {
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 从第二行第一个元素开始
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                // 在同一列上使用插入排序
                int j = i - gap;
                for (; j >= 0; j -= gap) {
                    if (arr[j] > temp) {
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }

                arr[j + gap] = temp;
            }
        }
    }

    private static void insertionSort(int[] arr, int n) {
        // 从第二个元素开始
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i - 1;
            // 寻找插入位置
            for (; j >= 0; j--) {
                /**
                 * 比当前元素大的一律向后移动，
                 * 相等元素不会向后移动，也就保证了稳定性
                 */
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }

            // 因为上面--所以元素插入位置为j+1
            arr[j + 1] = temp;
        }
    }

    private static void bubleSort(int[] arr, int n) {
        // 外层循环控制遍历次数，因为最后一次已经是有序，不用遍历，所以取n-1
        for (int i = 0; i < n - 1; i++) {
            // 提前退出标志
            boolean flag = false;
            /**
             * 内层循环比较交换，已经交换到末尾的元素不用在后面的循环中比较，
             * 所以取n-i，同时为了避免越界，所以n-i-1
             */
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);

                    // 有数据交换
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
