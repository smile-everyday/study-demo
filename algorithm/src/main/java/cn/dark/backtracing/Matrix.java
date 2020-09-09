package cn.dark.backtracing;

/**
 * 矩阵
 *
 * @author lwj
 * @date 2020-01-21
 */
public class Matrix {

    private static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        minDist(matrix, 0, 0, 0, matrix.length);
        System.out.println(minDist);
    }

    /**
     * 回溯法求最短路径
     *
     * @param matrix 矩阵
     * @param dist 累计距离
     * @param i 行号
     * @param j 列号
     * @param n 矩阵边长
     * @return void
     * @date 2020-01-21
     *
     */
    private static void minDist(int[][] matrix, int dist, int i, int j, int n) {
        // 必须在判断前计算好，否则会少计算最后一个格子
        dist += matrix[i][j];
        if (i >= n - 1 && j >= n - 1) {
            if (minDist > dist) {
                minDist = dist;
            }
            return;
        }

        // 向下走
        if (i < n - 1) {
            minDist(matrix, dist, i + 1, j, n);
        }

        // 向上走
        if (j < n - 1) {
            minDist(matrix, dist, i, j + 1, n);
        }

    }

}
