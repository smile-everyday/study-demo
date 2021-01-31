package cn.dark.spel;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dark
 * @date 2021-01-30
 */
public class SpelTest {

    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void t1() {
        Expression expression = parser.parseExpression("#test");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("test", "aa");
        String value = expression.getValue(context, String.class);
        System.out.println(value);
    }

}
