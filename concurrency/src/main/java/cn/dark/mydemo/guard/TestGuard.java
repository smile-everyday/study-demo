package cn.dark.mydemo.guard;

/**
 * @author dark
 * @date 2020-09-23
 */
public class TestGuard {

    public static void main(String[] args) throws InterruptedException {
        GuardResponse guardResponse = new GuardResponse();
        new Thread(() -> {
            try {
                System.out.println("模拟数据库操作");
                Thread.sleep(4000);
                guardResponse.setData(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(guardResponse.getData(15000));
    }

}
