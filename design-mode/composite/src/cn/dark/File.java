package cn.dark;

/**
 * @author dark
 * @date 2019-02-08
 */
public abstract class File {

    protected String name;
    protected double size;

    public File(String name) {
        this.name = name;
    }

    /**
     * 往文件夹添加文件，返回false表示不支持添加或添加失败
     */
    public boolean addFile(File file) {
        return false;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public boolean isFolder() {
        return false;
    }

    /**
     * 修改文件内容，返回false表示不支持修改
     */
    public boolean edit() {
        return false;
    }

    /**
     * 显示文件夹下所有的文件，返回false表示不是文件夹不支持该操作
     */
    public boolean print() {
        return false;
    }
}
