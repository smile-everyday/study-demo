package cn.dark.mydemo.tools.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author lwj
 * @date 2020-10-22
 */
@Slf4j
public class MergeSortTask extends RecursiveTask<int[]> {

    private int[] arr;

    public MergeSortTask(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        this.arr = arr;
    }

    @Override
    protected int[] compute() {
        if (arr.length == 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        MergeSortTask subTask1 = new MergeSortTask(left);
        MergeSortTask subTask2 = new MergeSortTask(right);

        invokeAll(subTask1, subTask2);

        int[] result1 = subTask1.join();
        int[] result2 = subTask2.join();
        return MergeSort.merge(result1, result2);
    }

    private int[] merge(int[] result1, int[] result2) {
        int[] temp = new int[result1.length + result2.length];

        int i = 0, j = 0, index = 0;
        while (i < result1.length && j < result2.length) {
            temp[index++] = result1[i] < result2[j] ? result1[i++] : result2[j++];
        }

        while (i < result1.length) {
            temp[index++] = result1[i++];
        }

        while (j < result2.length) {
            temp[index++] = result2[j++];
        }
        return temp;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] arr = MakeArray.makeArray();
        // log.info(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        MergeSortTask task = new MergeSortTask(arr);
        int[] result = pool.invoke(task);
        log.info("执行耗时：{}", System.currentTimeMillis() - start);
        // log.info(Arrays.toString(result));
    }

}
