package cn.dark.mediator;

/**
 * @author dark
 * @date 2019-05-02
 */
public class MainClass {

    public static void main(String[] args) {
        AbstractMediator mediator = new HouseMediator();

        Person landlord = new Landlord("房东");
        mediator.contact("我要租房", landlord);
        System.out.println(landlord.getMessage());

        Tenant tenant = new Tenant("租客");
        mediator.contact("一个月500", tenant);
        System.out.println(tenant.getMessage());
    }

}
