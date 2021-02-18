package cn.dark.api;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * @author dark
 * @date 2021-01-31
 */
public class ReflectDmo {

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("========getTypeName=========");
        System.out.println(Test.class.getTypeName());
        System.out.println(Son.class.getTypeName());
        System.out.println(Son1.class.getTypeName());

        System.out.println("========getTypeParameters=========");
        System.out.println(Arrays.toString(Test.class.getTypeParameters()));
        System.out.println(Arrays.toString(Son1.class.getTypeParameters()));
        System.out.println(Arrays.toString(Son2.class.getTypeParameters()));

        System.out.println("========getGenericInterfaces=========");
        System.out.println(Arrays.toString(Son.class.getGenericInterfaces()));
        System.out.println(Arrays.toString(Son1.class.getGenericInterfaces()));
        System.out.println(Arrays.toString(Son2.class.getGenericInterfaces()));

        System.out.println("========getGenericSuperclass=========");
        System.out.println(Son.class.getGenericSuperclass());
        System.out.println(Son1.class.getGenericSuperclass());
        System.out.println(Son2.class.getGenericSuperclass());

        ParameterizedType parameterizedType = (ParameterizedType) Son.class.getGenericSuperclass();
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));

        // ParameterizedType parameterizedType1 = (ParameterizedType) Test.class.getGenericSuperclass();
        // System.out.println(Arrays.toString(parameterizedType1.getActualTypeArguments()));
        //
        // ParameterizedType parameterizedType2 = (ParameterizedType) Son1.class.getGenericSuperclass();
        // System.out.println(Arrays.toString(parameterizedType2.getActualTypeArguments()));

        System.out.println(Father.class.getMethod("test", Object.class).getReturnType());
        System.out.println(Father.class.getMethod("test1", null).getReturnType());
        System.out.println(Father.class.getMethod("test2", null).getReturnType());
        System.out.println(Father.class.getMethod("test", Object.class).getGenericReturnType());
        System.out.println(Father.class.getMethod("test1", null).getGenericReturnType());
        System.out.println(Father.class.getMethod("test2", null).getGenericReturnType());
    }

}

class Test {}

class Father<T> {
    public T test(T t) {
        return t;
    }

    public void test1() {

    }

    public String test2() {
        return "";
    }
}

interface FatherInter<T> {
}

class Son extends Father<String> implements FatherInter<String> {

}

class Son1<T> extends Father implements FatherInter<T> {}

class Son2<T, K> extends Father<T> implements FatherInter<T> {

}
