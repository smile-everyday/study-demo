package cn.dark;

/**
 * @author dark
 * @date 2019-02-13
 */
public class Variable implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getValue(this);
    }

}
