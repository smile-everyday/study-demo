package cn.dark.builder;

import cn.dark.builder.disorder.Room;

/**
 * @author dark
 * @date 2018-11-22
 */
public class MainClass {

    public static void main(String[] args) {
        // Builder builder = new RoomBuilder();
        // Designer designer = new Designer(builder);
        // designer.build("door", "window", "floor");
        // Room room = builder.getRoom();

        Room room = new Room.Builder().makeDoor("door").makeWindow("window").makeFloor("floor").build();
    }

}
