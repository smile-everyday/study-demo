package cn.dark.mydemo.tools.forkjoin;

import java.util.Random;

/**
 * @author lwj
 * @date 2020-10-22
 */
public class MakeArray {

    private static final int size = 40000000;

    public static int[] makeArray() {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

}
