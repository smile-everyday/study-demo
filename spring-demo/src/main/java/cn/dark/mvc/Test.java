package cn.dark.mvc;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class Test {

    @MyAnno
    private Integer i;
    @MyAnno
    private String s;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
