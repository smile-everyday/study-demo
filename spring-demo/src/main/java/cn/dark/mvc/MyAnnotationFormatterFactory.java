package cn.dark.mvc;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author lwj
 * @date 2020-12-15
 */
public class MyAnnotationFormatterFactory implements AnnotationFormatterFactory<MyAnno> {


    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> objects = new HashSet<>();
        objects.add(String.class);
        objects.add(Integer.class);
        objects.add(Test.class);
        return objects;
    }

    @Override
    public Printer<?> getPrinter(MyAnno myAnno, Class<?> aClass) {
        System.out.println("getParser:" + aClass);
        return new Printer<Object>() {
            @Override
            public String print(Object object, Locale locale) {
                System.out.println("getPrinter->print");
                return null;
            }
        };
    }

    @Override
    public Parser<?> getParser(MyAnno myAnno, Class<?> aClass) {
        System.out.println("getPrinter:" + aClass);
        return new Parser<Object>() {
            @Override
            public Object parse(String text, Locale locale) throws ParseException {
                System.out.println("getParser -> parse");
                return null;
            }
        };
    }

}
