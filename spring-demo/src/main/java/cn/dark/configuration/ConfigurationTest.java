package cn.dark.configuration;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lwj
 * @date 2021-01-28
 */
public class ConfigurationTest {

    @Test
    public void t1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.dark.configuration");
        ConfiguraionDemo.A bean = applicationContext.getBean(ConfiguraionDemo.A.class);
    }

}
