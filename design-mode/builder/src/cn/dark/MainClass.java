package cn.dark;

import cn.dark.disorder.Room;
import cn.dark.ordered.Builder;
import cn.dark.ordered.Designer;
import cn.dark.ordered.RoomBuilder;

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
