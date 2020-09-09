package cn.dark;

/**
 * @author dark
 * @date 2019-02-13
 */
public class DevExpression implements Expression {

    private Expression left, right;

    public DevExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) / right.interpret(context);
    }
}
