package cn.dark;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dark
 * @date 2019-02-08
 */
public class Folder extends File {

    private ArrayList<File> files;

    public Folder(String name) {
        super(name);
        files = new ArrayList<>();
    }

    /**
     * 添加文件到文件夹并计算总大小
     */
    @Override
    public boolean addFile(File file) {
        this.files.add(file);
        this.size += file.getSize();
        return true;
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public boolean print() {
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            File file = iterator.next();
            // 当前文件是文件夹，则递归显示内部子文件
            if (file.isFolder()) {
                System.out.println("Folder name: " + file.getName() + ", total size: " + file.getSize());
                file.print();
            } else {
                System.out.println("File name: " + file.getName() + ", file size: " + file.getSize());
            }
        }
        return true;
    }
}
