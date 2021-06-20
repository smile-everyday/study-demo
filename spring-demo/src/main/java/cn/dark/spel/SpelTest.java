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

    @Test
    public void t2() {
        Student student = new Student("lwj", 12);
        Student student1 = new Student("lwj1", 14);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);

        String[] paramNames = {"age", "student", "students"};
        Object[] args = {13, student, students};
        String value = ElParser.getKeyByParameter("#student.name", paramNames, args);
        System.out.println(value);

        String value1 = ElParser.getKeyByParameter("#age", paramNames, args);
        System.out.println(value1);

        String value3 = ElParser.getKeyByParameter("#students.get(1).age", paramNames, args);
        System.out.println(value3);

        String value4 = ElParser.getKeyByParameter("#students[1].name", paramNames, args);
        System.out.println(value4);
    }

}

class Student{
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
