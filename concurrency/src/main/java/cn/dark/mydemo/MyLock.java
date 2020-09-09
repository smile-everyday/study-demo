package cn.dark.mydemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author dark
 * @date 2020-08-29
 */
public class MyLock {

    private volatile int state;
    private static long valueOffset;
    private List<Thread> aqs = new LinkedList<>();
    private static Unsafe unsafe;
    private Thread current;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe =  (Unsafe)field.get(null);

            valueOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        if (!compareAndSet(0, 1)) {
            aqs.add(Thread.currentThread());
            LockSupport.park();
        }
    }

    public void unlock() {
        this.state = 0;
        if (!aqs.isEmpty()) {
            LockSupport.unpark(aqs.get(0));
        }
    }

    private boolean compareAndSet(int exp, int newValue) {
        return unsafe.compareAndSwapInt(this, valueOffset, exp, newValue);
    }

}
