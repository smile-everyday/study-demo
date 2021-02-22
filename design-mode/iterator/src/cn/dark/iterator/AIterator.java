package cn.dark.iterator;

/**
 * @author dark
 * @date 2019-02-07
 */
public class AIterator implements Iterator {

    private int ind;
    private MenuItem[] menuItems;

    public AIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (ind >= menuItems.length || menuItems[ind] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return menuItems[ind++];
    }
}
