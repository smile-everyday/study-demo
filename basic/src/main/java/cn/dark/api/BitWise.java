package cn.dark.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwj
 * @date 2021-03-03
 */
public class BitWise {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(t1(5).toArray()));
    }

    private static List<Integer> t1(int a) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i*=2) {
            int b = (1 << i) & a;
            if (b > 0) {
                list.add(b);
            }
        }

        if ((a & 1) > 0) {
            list.add(1);
        }
        return list;
    }

    private static List<Integer> calc(int a, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            int b = a & i;
            if (b > 0 && b >= a) {
                list.add(b);
            }
        }
        return list;
    }

}
