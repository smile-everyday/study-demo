package cn.dark.interpreter;

/**
 * @author dark
 * @date 2019-02-13
 */
public class AddExpression implements Expression {

    private Expression left, right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}
