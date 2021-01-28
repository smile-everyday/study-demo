package cn.dark.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwj
 * @date 2021-01-28
 */
@Configuration
public class ConfiguraionDemo {

    @Bean
    public A a() {
        this.b();
        return new A();
    }

    @Bean
    public B b() {
        return new B();
    }

    public class A {}

    public class B {}
}
