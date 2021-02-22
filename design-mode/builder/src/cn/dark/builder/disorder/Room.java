package cn.dark.builder.disorder;

/**
 * @author dark
 * @date 2018-11-22
 */
public class Room {

    private String door;
    private String window;
    private String floor;

    public Room(Builder builder) {
        this.door = builder.door;
        this.floor = builder.floor;
        this.window = builder.window;
    }

    public static class Builder {
        private String door;
        private String window;
        private String floor;

        public Builder makeDoor(String door) {
            this.door = door;
            return this;
        }
        public Builder makeWindow(String window) {
            this.window = window;
            return this;
        }
        public Builder makeFloor(String floor) {
            this.floor = floor;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

}
