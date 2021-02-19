package cn.dark.chain;

/**
 * @author dark
 * @date 2019-02-11
 */
public abstract class Adminstration {

    protected Adminstration next;

    public abstract Level getLevel();

    public void setNext(Adminstration next) {
        this.next = next;
    }

    public void process(Request request) {
        int requestType = request.getLevel().getNum();
        int mangeType = this.getLevel().getNum();
        if (requestType == mangeType) {
            System.out.println("由" + this.getClass().getName() + "审批");
            return;
        }

        if (next == null) {
            System.out.println("没有人具有该审批权限！");
            return;
        }
        next.process(request);
    }

}
