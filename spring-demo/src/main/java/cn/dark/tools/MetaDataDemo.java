package cn.dark.tools;

import cn.dark.bean.SubMetatdata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.annotation.Target;

/**
 * Spring注解操作工具
 *
 * @author dark
 * @date 2021-01-24
 */
@Component
@Slf4j
public class MetaDataDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    public void metaData() {
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory(resourceLoader);
        try {
            MetadataReader reader = factory.getMetadataReader(SubMetatdata.class.getName());
            log.info("reader：{}", reader);
            ClassMetadata classMetadata = reader.getClassMetadata();
            log.info("class metadata：{}", classMetadata);
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
            log.info("===================annotationMetadata info==================");
            log.info("metaData：{}", annotationMetadata);
            log.info("class Name：{}", annotationMetadata.getClassName());
            log.info("post construct methods: {}", annotationMetadata.getAnnotatedMethods(PostConstruct.class.getName())); // 获取标记有该注解的方法
            log.info("annotation types: {}", annotationMetadata.getAnnotationTypes()); // 获取类上的所有注解类型
            log.info("component types: {}", annotationMetadata.getMetaAnnotationTypes(Component.class.getName())); // 获取注解的父类型
            log.info("post construct types: {}", annotationMetadata.getMetaAnnotationTypes(PostConstruct.class.getName()));
            log.info("annotations: {}", annotationMetadata.getAnnotations());
            log.info("encloseing class name：{}", annotationMetadata.getEnclosingClassName());
            log.info("interface names：{}", annotationMetadata.getInterfaceNames());
            log.info("member class names：{}", annotationMetadata.getMemberClassNames()); // 获取内部类
            log.info("super class name：{}", annotationMetadata.getSuperClassName()); // 获取父类
            log.info("all annotation attributes by component：{}", annotationMetadata.getAllAnnotationAttributes(Component.class.getName())); // 获取指定注解的所有属性和值
            log.info("all annotation attributes by controller：{}", annotationMetadata.getAllAnnotationAttributes(Controller.class.getName()));
            log.info("has component methods：{}", annotationMetadata.hasAnnotatedMethods(Component.class.getName())); // 判断是否存在标记该注解的方法
            log.info("has post construct methods：{}", annotationMetadata.hasAnnotatedMethods(PostConstruct.class.getName()));
            log.info("has component：{}", annotationMetadata.hasAnnotation(Component.class.getName())); // 判断类上是否有该注解
            log.info("has post construct：{}", annotationMetadata.hasAnnotation(PostConstruct.class.getName()));
            log.info("has meta annotation(component)：{}", annotationMetadata.hasMetaAnnotation(Component.class.getName()));
            log.info("has meta annotation(target)：{}", annotationMetadata.hasMetaAnnotation(Target.class.getName()));
            log.info("is abstract：{}", annotationMetadata.isAbstract());
            log.info("is component：{}", annotationMetadata.isAnnotated(Component.class.getName()));
            log.info("is post construct ：{}", annotationMetadata.isAnnotated(PostConstruct.class.getName()));
            log.info("is annotation：{}", annotationMetadata.isAnnotation());

            AnnotationAttributes componentAttr = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(Component.class.getName()));
            log.info("component attributes：{}", componentAttr);
            AnnotationAttributes scopeAttr = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(Scope.class.getName()));
            log.info("scope attributes：{}", scopeAttr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
