package cn.dark.tools;

import cn.dark.bean.SubMetatdata;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author dark
 * @date 2021-01-24
 */
@Slf4j
public class ToolsTest {

    @Test
    public void metadata() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.dark");
        MetaDataDemo contextBean = applicationContext.getBean(MetaDataDemo.class);
        contextBean.metaData();
    }

    @Test
    public void classUtils() {
        log.info("SubMetadata package：{}", ClassUtils.getPackageName(SubMetatdata.class));
    }

    @Test
    public void reflectionUtils() {
        log.info("SubMetadata all declared methods：{}", Arrays.toString(ReflectionUtils.getAllDeclaredMethods(SubMetatdata.class)));
        log.info("SubMetadata declared methods：{}", Arrays.toString(ReflectionUtils.getDeclaredMethods(SubMetatdata.class)));
        log.info("SubMetadata unique declared methods：{}", Arrays.toString(ReflectionUtils.getUniqueDeclaredMethods(SubMetatdata.class)));
    }

    @Test
    public void beanUtils() {
        log.info("i：{}", BeanUtils.findPropertyType("i", SubMetatdata.class));
        log.info("j：{}", BeanUtils.findPropertyType("j", SubMetatdata.class));
        log.info("k：{}", BeanUtils.findPropertyType("k", SubMetatdata.class));
        log.info("l：{}", BeanUtils.findPropertyType("l", SubMetatdata.class));
    }

    @Test
    public void bridgeMethodResolver() {
        Method parent = ClassUtils.getMethod(SubMetatdata.class, "parent", new Class[]{Object.class});
        log.info(parent.isBridge() + "--" + parent.hashCode());

        Method parent1 = ClassUtils.getMethod(SubMetatdata.class, "parent", new Class[]{String.class});
        log.info(parent1.isBridge() + "--" + parent1.hashCode());

        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(parent);
        System.out.println(bridgedMethod);
        System.out.println(bridgedMethod.hashCode());
        Method bridgedMethod1 = BridgeMethodResolver.findBridgedMethod(parent1);
        System.out.println(bridgedMethod1);
        System.out.println(bridgedMethod1.hashCode());
    }

    @Test
    public void propertiesLoaderUtils() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties", ClassUtils.getDefaultClassLoader());
        System.out.println(properties);
    }

    @Test
    public void resolvableType() {
        ResolvableType subMetadatType = ResolvableType.forClass(SubMetatdata.class);
        log.info("sub metadata type：{}", subMetadatType);
        System.out.println(subMetadatType.resolveGeneric(0));
        System.out.println();

        ResolvableType superType = subMetadatType.getSuperType();
        log.info("super type：{}", superType);
        System.out.println(superType.resolveGeneric(0));
        System.out.println();

        ResolvableType i = ResolvableType.forField(ReflectionUtils.findField(SubMetatdata.class, "i"));
        System.out.println(i.getType());
        System.out.println(i.getSuperType());
        System.out.println(i.getRawClass());
        System.out.println();

        ResolvableType s = ResolvableType.forField(ReflectionUtils.findField(SubMetatdata.class, "s"));
        System.out.println(s.getType());
        System.out.println(s.getSuperType());
        System.out.println(s.getRawClass());
        System.out.println();
    }

    @Test
    public void spel() {
        ExpressionParser expression = new SpelExpressionParser();
    }
}
