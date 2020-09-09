package cn.dark;

/**
 * @author dark
 * @date 2019-02-08
 */
public class TextFile extends File {

    public TextFile(String name, double size) {
        super(name);
        this.size = size;
    }

    @Override
    public boolean edit() {
        System.out.println("修改成功！");
        return true;
    }
}
