package cn.dark.builder.ordered;

/**
 * @author dark
 * @date 2018-11-22
 */
public class Designer {

    private Builder builder;

    public Designer(Builder builder) {
        this.builder = builder;
    }

    public void build(String door, String window, String floor) {
        builder.makeDoor(door);
        builder.makeDoor(window);
        builder.makeFloor(floor);
    }

}
