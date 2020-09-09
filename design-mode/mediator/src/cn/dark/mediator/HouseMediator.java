package cn.dark.mediator;

/**
 * @author dark
 * @date 2019-05-02
 */
public class HouseMediator extends AbstractMediator {

    @Override
    public void contact(String message, Person person) {
        person.setMessage(message);
    }
}
