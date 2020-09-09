package cn.dark.observer;

/**
 * @author dark
 * @date 2018-11-17
 */
public class MainClass {

    public static void main(String[] args) {
        WeateherData weateherdata = new WeateherData();
        Observer observer = new CurrentConditionDisplay(weateherdata);

        weateherdata.changeData(11.2, 22.3, 33);
        weateherdata.changeData(11.2, 22.3, 33);

    }

}
