package cn.dark.dynamicproxy.custom;

import cn.dark.dynamicproxy.custom.customize.CustomizeClassLoader;
import cn.dark.dynamicproxy.custom.customize.CustomizeInvocationHandler;
import cn.dark.dynamicproxy.custom.customize.MyProxy;

import java.lang.reflect.Method;

/**
 * @Title: How to act as agent!
 * @Author: Dark
 * @Date: 2018-08-25
 */
public class MyInvocationHandler implements CustomizeInvocationHandler {

    private Game game;

    /**
     * Get proxy object
     *
     *@param：game Real object
     *@return： Proxy object
     *@date：2018-08-25
     *
     */
    public Game getInstance(Game game) {

        this.game = game;

        Class clazz = game.getClass();
        Game instance = (Game) MyProxy.newProxyInstance(new CustomizeClassLoader(), clazz.getInterfaces()[0], this);

        return instance;
    }

    /**
     * Agent's callback function
     *
     *@param：proxy Generate object by dynamic proxy
     *@param：method Actual invoke method by proxy object
     *@param：args Send parameters by client
     *@return： Method's return value
     *@throw： Throwable
     *@date：2018-08-25
     *
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Before operate......");
        // invoke real-object's method and return method's value,
        Object invoke = method.invoke(game, args);
        System.out.println("after operate......");

        return invoke;
    }
}
