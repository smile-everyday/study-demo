package cn.dark.ordered;

/**
 * @author dark
 * @date 2018-11-22
 */
public class RoomBuilder implements Builder {

    @Override
    public void makeDoor(String door) {
        room.setDoor(door);
    }

    @Override
    public void makeWindow(String window) {
        room.setWindow(window);
    }

    @Override
    public void makeFloor(String floor) {
        room.setFloor(floor);
    }
}
