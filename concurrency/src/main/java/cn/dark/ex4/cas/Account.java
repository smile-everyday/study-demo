package cn.dark.ex4.cas;

public interface Account {
    //查询
    Integer query();

    //获取 取钱
    void acquire(Integer i);
}
