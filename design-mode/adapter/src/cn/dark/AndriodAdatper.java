package cn.dark;

/**
 * @author dark
 * @date 2018-12-08
 */
public class AndriodAdatper extends IPhone {

    private Andriod andriod;

    public AndriodAdatper(Andriod andriod) {
        this.andriod = andriod;
    }

    @Override
    public void iPhoneCharging() {
        andriod.andriodCharging();
    }

}
