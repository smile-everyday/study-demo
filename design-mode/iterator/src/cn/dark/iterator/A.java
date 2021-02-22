package cn.dark.iterator;

/**
 * @author dark
 * @date 2019-02-07
 */
public class A implements Menu {

    private int count;
    private MenuItem[] menuItems = new MenuItem[10];

    public A() {
        add("包子", 43);
        add("馒头", 32);
        add("豆浆", 34);
        add("牛奶", 26);
    }

    public void add(String name, double price) {
        MenuItem menuItem = new MenuItem(name, price);
        if (count >= menuItems.length) {
            System.out.println("Sorry!You can't add the new menuItem!");
        } else {
            menuItems[count++] = menuItem;
        }
    }

    @Override
    public Iterator createIterator() {
        return new AIterator(menuItems);
    }
}
