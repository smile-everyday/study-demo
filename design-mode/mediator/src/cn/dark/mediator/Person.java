package cn.dark.mediator;

/**
 * @author dark
 * @date 2019-05-02
 */
public class Person {

    protected String name;
    protected String message;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.name + "收到消息：" + this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
