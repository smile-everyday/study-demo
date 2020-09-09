package cn.dark.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Title: How to act as agent!
 * @Author: Dark
 * @Date: 2018-08-26
 */
public class MyInterceptor implements MethodInterceptor {

    /**
     * Get proxy object
     *
     *@param：obj Real object
     *@return： Proxy object
     *@date：2018-08-25
     *
     */
    public Object getInstance(Object obj) {
        // proxy class generater
        Enhancer enhancer = new Enhancer();
        // Set the proxy class will extend the superclass
        enhancer.setSuperclass(obj.getClass());
        // Set reference to the proxy class and all methods to use the callback
        enhancer.setCallback(this);

        // return the proxy class
        return enhancer.create();
    }

    /**
     * Agent's callback function
     *
     *@param：obj Generate object by dynamic proxy
     *@param：method intercepted Method
     *@param：args Send parameters by client
     *@param：methodProxy used to invoke super (non-intercepted method); may be called
     * as many times as needed
     *@return： Method's return value
     *@throw： Throwable
     *@date：2018-08-25
     *
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before...");
        Object o = methodProxy.invokeSuper(obj, args);
        System.out.println("after...");

        return o;
    }
}
