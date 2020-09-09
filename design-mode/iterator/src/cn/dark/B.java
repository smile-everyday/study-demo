package cn.dark;

import java.util.ArrayList;

/**
 * @author dark
 * @date 2019-02-07
 */
public class B implements Menu{

    private ArrayList lists = new ArrayList<>();

    public B() {
        add("红烧茄子", 53);
        add("青椒肉丝", 46);
        add("佛跳墙", 35);
        add("红烧鲫鱼", 46);
    }

    public void add(String name, double price) {
        lists.add(new MenuItem(name, price));
    }

    @Override
    public Iterator createIterator() {
        return new BIterator(lists);
    }
}
