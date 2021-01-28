package cn.dark.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dark
 * @date 2021-01-24
 */
abstract class SuperMetaData<T> {

    public abstract void parent(T t);

}

@Component
@Scope
public class SubMetatdata extends SuperMetaData<String> {

    private int i;
    int j;
    protected int k;
    private int l;
    private String s;

    @Component
    class InnerMetaData {
        @PostConstruct
        public void body() {}

        @PostConstruct
        protected void bod1() {

        }

        @PostConstruct
        void bod2() {

        }

        @PostConstruct
        private void bod3() {

        }

        public void sub() {}
    }

    @PostConstruct
    public void body() {}

    @PostConstruct
    protected void bod1() {

    }

    @PostConstruct
    void bod2() {

    }

    // @PostConstruct
    // private void bod3() {}

    @Override
    public void parent(String param) {

    }
}