package cn.dark.dynamicproxy.custom.customize;

import java.lang.reflect.Method;

/**
 * @Title: Customize invocation handler
 * @Author: Dark
 * @Date: 2018-08-26
 */
public interface CustomizeInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}
