package cn.dark.builder;

/**
 * @author dark
 * @date 2018-11-25
 */
public class Designer {

    private CoffeBuilder coffeBuilder;

    public Designer(CoffeBuilder coffeBuilder) {
        this.coffeBuilder = coffeBuilder;
    }

    public void build() {
        coffeBuilder.addMilk();
        coffeBuilder.addSugar();
    }

}
