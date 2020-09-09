package cn.dark;

/**
 * @author dark
 * @date 2019-02-05
 */
public class MainClass {

    public static void main(String[] args) {
        HomePartyFacade partyFacade = new HomePartyFacade(new TV(), new Bathtub(),
                new RoomLamp(), new Amplifier());
        partyFacade.startParty();
        partyFacade.endParty();
    }

}
