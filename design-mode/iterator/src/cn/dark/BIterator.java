package cn.dark;

import java.util.ArrayList;

/**
 * @author dark
 * @date 2019-02-07
 */
public class BIterator implements Iterator {

    private int ind;
    private ArrayList lists;

    public BIterator(ArrayList lists) {
        this.lists = lists;
    }

    @Override
    public boolean hasNext() {
        if (ind >= lists.size()) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return lists.get(ind++);
    }
}
