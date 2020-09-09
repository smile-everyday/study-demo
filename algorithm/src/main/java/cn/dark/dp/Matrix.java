package cn.dark.dp;

/**
 * 矩阵
 *
 * @author lwj
 * @date 2020-01-21
 */
public class Matrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        int[][] mem = new int[matrix.length][matrix.length];
        minDist(matrix, mem, matrix.length, matrix.length - 1, matrix.length - 1);
        System.out.println(mem[matrix.length - 1][matrix.length - 1]);
    }

    /**
     * 动态规划（状态转移表法）求最短路径
     *
     * @param matrix 矩阵
     * @param n 矩阵边长
     * @return void
     * @date 2020-01-21
     *
     */
    private static int minDist(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        states[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            // 先填满第一行和第一列
            states[i][0] = matrix[i][0] + matrix[i - 1][0];
            states[0][i] = matrix[0][i] + matrix[0][i - 1];
        }

        // 逐个填满每个格子
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = Math.min(matrix[i][j] + matrix[i - 1][j], matrix[i][j] + matrix[i][j - 1]);
            }
        }

        return states[n - 1][n - 1];
    }

    /**
     * 动态规划（状态转移方程（递归 + 备忘录）法）求最短路径
     *
     * @param matrix
     * @param mem 缓存已经计算过的格子
     * @param n
     * @param i
     * @param j
     * @return int
     * @date 2020-01-21
     *
     */
    private static int minDist(int[][] matrix, int[][] mem, int n, int i, int j) {
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }

        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        // 从右下角开始先递归求左边的路径
        int minLeft = Integer.MAX_VALUE;
        if (i >= 1) {
            minLeft = minDist(matrix, mem, n, i - 1, j);
        }

        // 从右下角开始先递归求上边的路径
        int minUp = Integer.MAX_VALUE;
        if (j >= 1) {
            minUp = minDist(matrix, mem, n, i, j - 1);
        }

        // 获取两种选择的最短路径填入到备忘录中
        int dist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = dist;
        return dist;
    }

}
