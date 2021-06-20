package cn.dark.tools;

import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author dark
 * @date 2021-06-20
 */
public class BridgeMethodDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        for (Method method : Child.class.getMethods()) {
            if (method.isBridge()) {
                System.out.println(method + ":" + Arrays.toString(method.getParameterTypes()));
                // 获取我们自己定义的方法，而不是编译器生成的桥接方法
                System.out.println(BridgeMethodResolver.findBridgedMethod(method));
            }
        }

        System.out.println("--------------------------");

        for (Method method : Super.class.getMethods()) {
            if (method.isBridge()) {
                System.out.println(method + ":" + Arrays.toString(method.getParameterTypes()));
                // 获取我们自己定义的方法，而不是编译器生成的桥接方法
                System.out.println(BridgeMethodResolver.findBridgedMethod(method));
            }
        }
    }

}

class Super<T> {
    public Number getNumber(T t) {
        return 0;
    }
}

class Child extends Super<String> {
    @Override
    public Integer getNumber(String s) {
        return 1;
    }
}
