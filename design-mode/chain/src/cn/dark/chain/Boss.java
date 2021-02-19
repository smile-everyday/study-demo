package cn.dark.chain;

/**
 * @author dark
 * @date 2019-02-11
 */
public class Boss extends Adminstration {
    @Override
    public Level getLevel() {
        return new Level(4);
    }
}
