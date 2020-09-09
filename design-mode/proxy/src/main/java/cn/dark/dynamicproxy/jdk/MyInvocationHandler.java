package cn.dark.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @title How to act as agent!
 * @author Dark
 * @date 2018-08-25
 */
public class MyInvocationHandler implements InvocationHandler {

    private Game game;

    /**
     * Get proxy object
     *
     *@param game Real object
     *@return Proxy object
     *@date 2018-08-25
     *
     */
    public Game getInstance(Game game) {

        this.game = game;

        Class clazz = game.getClass();
        Game instance = (Game) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);

        return instance;
    }

    /**
     * Agent's callback function
     *
     *@param proxy Generate object by dynamic proxy
     *@param method Actual invoke method by proxy object
     *@param args Send parameters by client
     *@return Method's return value
     *@throws Throwable Throwable
     *@date 2018-08-25
     *
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Before operate...");
        // invoke real-object's method and return method's value,
        Object invoke = method.invoke(game, args);
        System.out.println("after operate...");

        return invoke;
    }
}
