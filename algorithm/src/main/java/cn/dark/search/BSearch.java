package cn.dark.search;

/**
 * @author dark
 * @date 2019-12-27
 */
public class BSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 9, 10, 10, 10, 11, 11, 11, 11, 13};
        int i = doSearch3(arr, 15);
        System.out.println(i);
    }

    /**
     * 二分任意一个与给定值相等的元素
     */
    private static int doSearch1(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            // 避免相加溢出
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > value) {
                r = mid - 1;
            } else if (arr[mid] < value) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找第一个与给定值相等的元素
     */
    private static int doSearch2(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > value) {
                r = mid - 1;
            } else if (arr[mid] < value) {
                l = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                }
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找最后一个与给定值相等的元素
     */
    private static int doSearch3(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > value) {
                r = mid - 1;
            } else if (arr[mid] < value) {
                l = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                }
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找第一个大于等于给定值的元素
     */
    private static int doSearch4(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) {
                    return mid;
                }
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找最后一个小于等于给定值的元素
     */
    private static int doSearch5(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > value) {
                r = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] > value) {
                    return mid;
                }
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找循环数有序组中的给定值{4, 5, 6, 1, 2, 3}
     */
    private static int doSearch6(int[] arr, int value) {

        return -1;
    }

}
