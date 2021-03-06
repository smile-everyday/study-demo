package cn.dark.observer;

import java.util.ArrayList;

/**
 * @author dark
 * @date 2018-11-17
 */
public interface Subject {

    ArrayList<Observer> observers = new ArrayList<>();

    default void register(Observer observer) {
        observers.add(observer);
    }

    default void remove(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 主题只推送少量数据给观察者（这里是没有推送数据），但把主题本身一同推送过去，
     * 观察者如果需要更多的数据则需要使用拉的方式从主题对象获取需要的数据
     *
     *@date 2018-11-17
     *
     */
    default void notifyAllObservers() {
        notifyAllObservers(null);
    }

    /**
     * 主题将所有数据推送给观察者
     *
     *@param object 需要更新的数据封装的对象
     *@date 2018-11-17
     *
     */
    default void notifyAllObservers(Object object) {
        for (Observer observer : observers) {
            observer.update(this, object);
        }
    }

}
