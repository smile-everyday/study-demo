package cn.dark.backtracing;

/**
 * 八皇后
 *
 * @author lwj
 * @date 2020-01-17
 */
public class EightQueen {

    // 存储皇后的位置，下标为行，值为列
    private static Integer[] result = new Integer[8];

    public static void main(String[] args) {
        cal(0);
    }

    private static void cal(int row) {
        if (row == 8) {
            print(result);
            return;
        }

        /**
         * 每一行都有8种放法，递归处理每一行，
         * 当前放法走不通时，就回溯换一列再试，
         * 直到完全摆完才会出现row = 8
         */
        for (int column = 0; column < 8; column++) {
            if (isOK(row, column)) {
                result[row] = column;
                cal(row + 1);
            }
        }
    }

    private static boolean isOK(int row, int column) {
        // 依次判断上一行的该列以及左上、右上对角线是否已经有皇后
        // int leftUp = column - 1, rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) {
                return false;
            }

            // 行互减的绝对值 == 列互减的绝对值即表示在一条斜线上
            if (Math.abs(row - i) == Math.abs(column - result[i])) {
                return false;
            }

            // if (result[i] == leftUp--) {
            //     return false;
            // }
            //
            // if (rightUp < 8) {
            //     if (result[i] == rightUp++) {
            //         return false;
            //     }
            // }
        }
        return true;
    }

    private static void print(Integer[] result) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (result[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
