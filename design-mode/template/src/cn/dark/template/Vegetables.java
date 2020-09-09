package cn.dark.template;

/**
 * @author dark
 * @date 2018-10-20
 */
public abstract class Vegetables {

    public final void doCooking() {
        preCooking();
        if (isPeel()) {
            peel();
        }
        cooking();
        putPlate();
    }

    private void preCooking() {
        System.out.println("Wash vegetables and light a fire!");
    }

    private void peel() {
        System.out.println("Peel!");
    }

    protected abstract void cooking();

    private void putPlate() {
        System.out.println("Put plate!");
    }

    protected boolean isPeel() {
        return true;
    }

}
