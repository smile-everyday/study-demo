package cn.dark.memento;

/**
 * @author dark
 * @date 2019-05-04
 */
public class MainClass {

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.setContent("1");
        System.out.println("第一次修改内容：" + editor.getContent());

        // 编辑内容并保存
        Caretaker caretaker = new Caretaker();
        caretaker.savePoint(editor.createMemento());
        editor.setContent("2");
        System.out.println("第二次修改后内容：" + editor.getContent());
        caretaker.savePoint(editor.createMemento());
        editor.setContent("3");
        System.out.println("第三次次修改后内容：" + editor.getContent());
        caretaker.savePoint(editor.createMemento());
        editor.setContent("4");
        System.out.println("第四次次修改后内容：" + editor.getContent());

        editor.undo(caretaker.getMemento());
        System.out.println("第一次恢复后的内容：" + editor.getContent());
        editor.undo(caretaker.getMemento());
        System.out.println("第二次恢复后的内容：" + editor.getContent());
        editor.undo(caretaker.getMemento());
        System.out.println("第三次恢复后的内容：" + editor.getContent());
    }

}
