package cn.dark.dp;

/**
 * 求序列中的最长递增子序列
 *
 * @author lwj
 * @date 2020-01-22
 */
public class LIS {

    private static int max = 0;

    public static void main(String[] args) {
        int[] seq = {5, 1, 3, 2, 9, 3, 6, 5, 1, 7};
        int[] seq1 = {3, 5, 8, 9, 1, 7};
        System.out.println(maxLength1(seq, seq.length));
    }

    /**
     * 时间：O(n^2)，空间：O(n)
     *
     * @param seq
     * @param n
     * @return void
     * @date 2020-01-22
     *
     */
    private static void maxLength(int[] seq, int n) {
        // 记录每个元素的最长递增序列
        int[] count = new int[n];
        // 初始化为1简化后面计算
        for (int i = 0; i < count.length; i++) {
            count[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // count[i] <= count[j]表示只需要将之前的最大子序列长度+1即为当前元素的最长递增子序列长度
                if (seq[i] > seq[j] && count[i] <= count[j]) {
                    count[i] = count[j] + 1;
                }
            }
            max = Math.max(count[i], max);
        }
    }

    /**
     * 动态规划+二分查找：时间（O(nlogn)），空间（O(n)）
     *
     * @param seq
     * @param n
     * @return int
     * @date 2020-01-22
     */
    private static int maxLength1(int[] seq, int n) {
        // 有序的保存每个元素对应的最长递增子序列中末尾最小的元素
        int[] seq1 = new int[n];
        seq1[0] = seq[0];

        // 有序数组的最后一个元素的索引
        int end = 0;
        for (int i = 1; i < n; i++) {
            /**
             * 当前元素比有序数组的最后一个元素还大，就直接放入该数组末尾，
             * 否则使用二分查找有序数组中第一个大于等于该元素的值的索引，
             * 并用当前元素替换掉即可（始终保持临时数组的有序性），最后end + 1
             * 即为最长递增子序列的长度，但是sqe1并不是对应的最长递增子序列
             */
            if (seq[i] > seq1[end]) {
                seq1[++end] = seq[i];
            } else {
                int indx = binarySearch(seq1, 0, end, seq[i]);
                seq1[indx] = seq[i];
            }
        }
        return ++end;
    }

    private static int binarySearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + ((high - 1) / 2);
            if (arr[mid] >= target) {
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return -1;
    }

}
