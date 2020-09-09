package cn.dark.memento;

/**
 * @author dark
 * @date 2019-05-04
 */
public class Memento {

    private String content;

    public Memento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
