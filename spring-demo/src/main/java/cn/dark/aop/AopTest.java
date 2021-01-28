package cn.dark.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lwj
 * @date 2021-01-28
 */
@Slf4j
public class AopTest {

    @Test
    public void t1() throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.dark.aop");
        StudentService bean = applicationContext.getBean(StudentService.class);
        bean.study("");

        byte[] proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{bean.getClass()});
        FileOutputStream fos = new FileOutputStream("$Proxy.class");
        fos.write(proxy0s);
        fos.close();
    }

}
