package cn.dark;

/**
 * @author dark
 * @date 2019-02-07
 */
public class Takeaway {

    // 这里不一定适用数组，list等也可
    private Menu[] menus;

    public Takeaway(Menu... menus) {
        this.menus = menus;
    }

    public void print() {
        for (Menu menu : menus) {
            print(menu.createIterator());
        }
    }

    private void print(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem item = (MenuItem) iterator.next();
            System.out.println("name:" + item.getName() + ", price:" + item.getPrice());
        }
    }


}
