package cn.dark.nio;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author dark
 * @date 2020-12-05
 */
public class BufferMethod {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a')
                .put((byte) 'b')
                .put((byte) 'c')
                .put((byte) 'd')
                .put((byte) 'e')
                .put((byte) 'f');
        System.out.println("buffer: " + buffer);

        buffer.flip();
        System.out.println("after flip: " + buffer);

        System.out.println((char) buffer.get());
        System.out.println("after get: " + buffer);

        // 将buffer内容写入byte数组，offset是指目标数组的起始位置，length是写入多少个元素
        byte[] dest = new byte[10];
        buffer.get(dest, 1, 2);
        System.out.println("after get(dest, 1, 2): " + buffer);
        System.out.println("dest: " + new String(dest));
        System.out.println("dest arr: " + Arrays.toString(dest));

        // 绝对读写不会改变pos
        ByteBuffer buffer1 = ByteBuffer.allocate(32);
        buffer1.put(8, (byte) 'h');
        System.out.println("after put(8, h): " + Arrays.toString(buffer1.array()));
        // buffer1.flip();
        System.out.println(buffer1.get(8));
        System.out.println("after get(8): " + buffer1);

        ByteBuffer buffer2 = ByteBuffer.allocate(32);
        buffer2.put((byte) 'a');
        System.out.println("before clear: " + buffer2);
        buffer2.clear();
        System.out.println("after clear: " + buffer2);
        System.out.println("after clear arr: " + Arrays.toString(buffer2.array()));

        buffer2.put("bcde".getBytes());
        System.out.println("after put 4: " + buffer2);
        System.out.println("after put 4: " + Arrays.toString(buffer2.array()));
        buffer2.flip();
        System.out.println("after flip: " + buffer2);
        buffer2.get();
        System.out.println("after get(): " + buffer2);
        System.out.println("remaining: " + buffer2.remaining());

        // 将所有未读元素拷贝到起始位置，并将pos设置成最后一个未读元素的后一个元素的索引，
        // 该方法主要是用于压缩空间后写，不适用于压缩后读（可能会读到重复元素，另外
        // put/compacat返回值均是原对象）
        ByteBuffer compact = buffer2.compact();
        System.out.println("after compact: " + buffer2);
        System.out.println("after compact: " + compact);
        System.out.println("after compact: " + Arrays.toString(compact.array()));
        compact.put((byte) 'f');
        System.out.println("after compact put: " + Arrays.toString(compact.array()));
        System.out.println("after compact put: " + Arrays.toString(buffer2.array()));

        System.out.println("before rewind buffer: " + buffer);
        buffer.rewind();
        System.out.println("after rewind buffer: " + buffer);
        buffer.position(5);
        buffer.mark();
        buffer.get();
        System.out.println("before reset: " + buffer);
        buffer.reset();
        System.out.println("after reset: " + buffer);
    }


}
