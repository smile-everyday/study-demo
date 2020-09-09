package cn.dark.backtracing;

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

    private static int max = 0;

    public static void main(String[] args) {
        int[] items = {5, 22, 1, 3, 24, 12};
        cal(0, 0, 57, items);
        System.out.println(max);
    }

    /**
     * 计算背包最大可放入物品重量
     *
     * @param i 已考察物品个数
     * @param cw 已放入物品总重量
     * @param w 背包限制重量
     * @param items 物品重量
     * @date 2020-01-17
     *
     */
    private static void cal(int i, int cw, int w, int[] items) {
        if (max >= w || i == items.length) {
            if (cw > max) {
                max = cw;
            }
            return;
        }

        /**
         * 每个物品都有放和不放两种选择，下面就是枚举所有的情况
         * 第一个递归表示初始都不在背包的时候，当递归最后一层时
         * 返回将倒数第二个物品放入背包，此时的递归才是枚举计算
         * 剩下的物品放入或不放入背包的重量，以此类推
         */
        cal(i + 1, cw, w, items);
        if (cw + items[i] <= w) {
            cal(i + 1, cw + items[i], w, items);
        }
    }
}
