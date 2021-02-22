package cn.dark.interpreter;

/**
 * @author dark
 * @date 2019-02-13
 */
public class MainClass {

    public static void main(String[] args) {
        // 首先创建变量x, y
        Variable x = new Variable();
        Variable y = new Variable();

        // 获取上下文容器，保存变量值
        Context context = new Context();
        context.putValue(x, 5);
        context.putValue(y, 10);
        // 创建常量
        Expression constant = new Constant(100);

        // 计算(x + y) * 100 / x
        Expression expression = new DevExpression(new MulExpression(new AddExpression(x, y), constant), x);
        int res = expression.interpret(context);
        System.out.println(res);
    }

}
