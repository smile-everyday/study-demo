package cn.dark.facade;

/**
 * @author dark
 * @date 2019-02-05
 */
public class HomePartyFacade {

    private TV tv;
    private Bathtub bathtub;
    private RoomLamp roomLamp;
    private Amplifier amplifier;

    public HomePartyFacade(TV tv, Bathtub bathtub, RoomLamp roomLamp, Amplifier amplifier) {
        this.tv = tv;
        this.bathtub = bathtub;
        this.roomLamp = roomLamp;
        this.amplifier = amplifier;
    }

    public void startParty() {
        roomLamp.on();
        bathtub.on();
        amplifier.on();
        amplifier.setCd();
        tv.on();
    }

    public void endParty() {
        roomLamp.off();
        amplifier.off();
        tv.off();
    }

}
