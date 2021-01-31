package cn.dark.api;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author dark
 * @date 2021-01-31
 */
public class ReflectDmo {

    public <T> T getValue(T t) {
        return t;
    }

    public String getValue1() {
        return "";
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = ReflectDmo.class.getMethod("getValue", Object.class);
        System.out.println(method.getReturnType());
        System.out.println(method.getGenericReturnType());
    }

}
