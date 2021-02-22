package cn.dark.builder.ordered;

/**
 * @author dark
 * @date 2018-11-22
 */
public interface Builder {

    Room room = new Room();

    void makeDoor(String door);

    void makeWindow(String window);

    void makeFloor(String floor);

    default Room getRoom() {
        return room;
    }

}
