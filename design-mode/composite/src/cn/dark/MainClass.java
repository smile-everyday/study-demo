package cn.dark;

/**
 * @author dark
 * @date 2019-02-08
 */
public class MainClass {

    public static void main(String[] args) {
        File root = new Folder("root");

        File imageFile = new ImageFile("a", 10);
        File imageFile1 = new ImageFile("b", 4);
        File imageFile2 = new ImageFile("c", 12);
        Folder folder = new Folder("图片");
        folder.addFile(imageFile);
        folder.addFile(imageFile1);
        folder.addFile(imageFile2);

        File textFile = new TextFile("1", 2);
        File textFile1 = new TextFile("2", 5);
        File textFile2 = new TextFile("3", 6);
        Folder folder1 = new Folder("文本");
        folder1.addFile(textFile);
        folder1.addFile(textFile1);
        folder1.addFile(textFile2);

        File file = new ImageFile("d", 53);
        File file1 = new TextFile("4", 34);
        root.addFile(file);
        root.addFile(file1);
        root.addFile(folder);
        root.addFile(folder1);

        root.print();
    }

}
