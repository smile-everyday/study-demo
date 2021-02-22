package cn.dark.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dark
 * @date 2019-02-13
 */
public class Context {

    private Map<Variable, Integer> map = new HashMap<>();

    public void putValue(Variable x, Integer y) {
        map.put(x, y);
    }

    public Integer getValue(Variable x) {
        return map.get(x);
    }

}
