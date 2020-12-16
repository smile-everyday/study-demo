package cn.dark.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lwj
 * @date 2020-12-15
 */
@Configuration
public class MyWebMvc implements WebMvcConfigurer {

    // @Autowired
    // private MyAnnotationFormatterFactory formatterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new MyAnnotationFormatterFactory());
    }

}
