package cn.dark.memento;

/**
 * @author dark
 * @date 2019-05-04
 */
public class Editor {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Memento createMemento() {
        return new Memento(this.content);
    }

    public void undo(Memento memento) {
        this.content = memento.getContent();
    }
}
