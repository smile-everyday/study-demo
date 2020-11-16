package cn.dark.mydemo;

/**
 * @author lwj
 * @date 2020-11-11
 */
public class BitCase {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("CAPACITY = " + CAPACITY);
        System.out.println("CAPACITY = " + Integer.toBinaryString(CAPACITY));
        System.out.println("CAPACITY.len = " + Integer.toBinaryString(CAPACITY).length());
        System.out.println("COUNT_BITS = " + COUNT_BITS);
        System.out.println("-1 = " + Integer.toBinaryString(-1));
        System.out.println("RUNNING = " + Integer.toBinaryString(RUNNING));
        System.out.println("RUNNING.len = " + Integer.toBinaryString(RUNNING).length());
        System.out.println("SHUTDOWN = " + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP = " + Integer.toBinaryString(STOP));
        System.out.println("STOP.len = " + Integer.toBinaryString(STOP).length());
        System.out.println("TIDYING = " + Integer.toBinaryString(TIDYING));
        System.out.println("TIDYING.len = " + Integer.toBinaryString(TIDYING).length());
        System.out.println("TERMINATED = " + Integer.toBinaryString(TERMINATED));
        System.out.println("TERMINATED.len = " + Integer.toBinaryString(TERMINATED).length());
    }

}
