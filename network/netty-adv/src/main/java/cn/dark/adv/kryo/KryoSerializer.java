package cn.dark.adv.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Kryo的序列化器，负责序列化和反序列化
 *
 * @author lwj
 * @date 2020-12-18
 */
public class KryoSerializer {

    private static Kryo kryo = KryoFactory.createKryo();

    public void serialize(Object object, ByteBuf out) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, object);
        output.flush();
        output.close();

        byte[] bytes = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.writeBytes(bytes);
    }

    public Object deserialize(ByteBuf out) {
        if (out == null) {
            return null;
        }

        Input input = new Input(new ByteBufInputStream(out));
        return kryo.readClassAndObject(input);
    }

}
