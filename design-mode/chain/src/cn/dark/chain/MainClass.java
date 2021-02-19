package cn.dark.chain;

/**
 * @author dark
 * @date 2019-02-11
 */
public class MainClass {

    public static void main(String[] args) {
        Adminstration teamLeader = new TeamLeader();
        Adminstration manager = new Manager();
        Adminstration director = new Director();
        Adminstration boss = new Boss();

        teamLeader.setNext(manager);
        manager.setNext(director);
        director.setNext(boss);

        Request request = new Request(new Level(3));
        teamLeader.process(request);

    }

}
