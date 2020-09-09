package cn.dark.memento;

import java.util.Stack;

/**
 * @author dark
 * @date 2019-05-04
 */
public class Caretaker {

    private Stack<Memento> mementos = new Stack<>();

    public void savePoint(Memento memento) {
        mementos.push(memento);
    }

    public Memento getMemento() {
        return mementos.pop();
    }

}
