package cn.dark.dp;

/**
 * 0-1背包：有一个背包，背包总的承载重量是 Wkg，
 * 现在我们有 n 个物品，每个物品的重量不等，并且
 * 不可分割。我们现在期望选择几件物品，装载到背包
 * 中。在不超过背包所能装载重量的前提下，如何让背
 * 包中物品的总重量最大
 *
 * @author lwj
 * @date 2020-01-17
 */
public class Bag {


    public static void main(String[] args) {
        int[] items = {2, 5, 2};
        int[] values = {1, 5, 1};
        System.out.println(cal2(5, items, values, items.length));
    }

    /**
     * 动态规划计算背包最大可放入物品重量
     *
     * @param w     背包限制重量
     * @param items 物品重量
     * @param n     物品个数
     * @date 2020-01-17
     */
    private static int cal(int w, int[] items, int n) {
        // 行号表示物品序列，列号表示重量，计算过的重量都记录状态为true
        boolean[][] states = new boolean[n][w + 1];
        // 哨兵思想
        states[0][0] = true;
        states[0][items[0]] = true;
        // 逐个遍历每个物品，注意j表示累加重量
        for (int i = 1; i < n; i++) {
            // 不放入第i个物品
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }

            // 放入第i个物品（枚举0-i个物品的各种组合的结果）
            for (int j = 0; j <= w - items[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        // 不大于限制重量的最大值
        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 针对上面算法的空间复杂度优化版，使用一个一维数组代替二维数组，
     * 相当于每次只考虑把在限制内的物品放入背包的情况
     */
    private static int cal1(int w, int[] items, int n) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
            /**
             * 这里需要注意必须从大到小，因为只用了一个一维数组来存，
             * 如果从小到大遍历会导致先计算的会影响到后计算的
             */
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，
     * 在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
     *
     * @param w       最大重量
     * @param weights 物品重量数组
     * @param values  物品价值数组
     * @param n       物品个数
     * @return int 物品最大价值
     * @date 2020-01-19
     */
    private static int cal2(int w, int[] weights, int[] values, int n) {
        // 二维数组不再只记录状态，而是统计物品的累计价值
        int[][] valCounts = new int[n][w + 1];
        // 必须初始化为负数，否则会导致结果不正确
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                valCounts[i][j] = -1;
            }
        }

        valCounts[0][0] = 0;
        valCounts[0][weights[0]] = values[0];
        for (int i = 1; i < n; i++) {
            // 不放第i个物品
            for (int j = 0; j <= w; j++) {
                // = 0 表示一直未放入的情况
                if (valCounts[i - 1][j] >= 0) {
                    valCounts[i][j] = valCounts[i - 1][j];
                }
            }

            // 放第i个物品
            for (int j = 0; j <= w - weights[i]; j++) {
                // = 0 表示一直未放入的情况
                if (valCounts[i - 1][j] >= 0) {
                    /**
                     * 相同重量下取价值更大的。考虑这样一个情况，当有一个物品
                     * 的重量刚好为限制值，且价值也为最大，而在此物品后还有其
                     * 它价值更小的物品，如果没有下面这个判断，就会导致最大的
                     * 价值被覆盖，如{2, 5, 2} {1, 5, 1}：
                     *      0 0 1 0 0 0
                     *      0 0 1 0 0 5
                     *      0 0 1 1 2 1
                     * 但如果先将二位数组初始化为负数也可避免出现上述情况：
                     *      0 -1 1 -1 -1 -1
                     *      0 -1 1 -1 -1 5
                     *      0 -1 1 -1 2 5
                     * 可以发现先初始化为负数后每个阶段才能区分重量是从未出现
                     * 过（-1）还是一直未放入物品（0）
                     */
                    int v = valCounts[i - 1][j] + values[i];
                    if (v > valCounts[i][j + weights[i]]) {
                        valCounts[i][j + weights[i]] = v;
                    }
                }
            }
        }

        int max = 0;
        for (int i = w; i >= 0; i--) {
            if (valCounts[n - 1][i] > max) {
                max = valCounts[n - 1][i];
            }
        }
        return max;
    }

}
