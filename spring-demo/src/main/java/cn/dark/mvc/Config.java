package cn.dark.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lwj
 * @date 2020-12-15
 */
@Configuration
public class Config {
    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean() {
        FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean = new FormattingConversionServiceFactoryBean();
        Set set = new HashSet<>();
        set.add(new MyAnnotationFormatterFactory());
        formattingConversionServiceFactoryBean.setFormatters(set);
        return formattingConversionServiceFactoryBean;
    }

}
