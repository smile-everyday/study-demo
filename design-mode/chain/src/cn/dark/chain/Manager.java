package cn.dark.chain;

import cn.dark.Level;

/**
 * @author dark
 * @date 2019-02-11
 */
public class Manager extends Adminstration {
    @Override
    public Level getLevel() {
        return new Level(2);
    }
}
