package cn.dark.interpreter;

/**
 * @author dark
 * @date 2019-02-13
 */
public class Constant implements Expression {

    private int value;

    public Constant(int value) {
        this.value = value;
    }

    @Override
    public int interpret(Context context) {
        return value;
    }
}
