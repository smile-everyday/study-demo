package cn.dark.dynamicproxy.custom.customize;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @title Customize proxy generator
 * @author Dark
 * @date 2018-08-26
 */
public class MyProxy {

    /**
     * Generate proxy instance
     *
     *@param loader the class loader to define the proxy class
     *@param interfaces For simplely use the single interface for the proxy class to implement, actual you should
     *                  use array
     *@param h The invocation handler for the proxy class to callback
     *@return Object A proxy object
     *@throws IllegalArgumentException if any of the parameters are invalid
     *@date 2018-08-26
     *
     */
    public static Object newProxyInstance(CustomizeClassLoader loader,
                                          Class<?> interfaces,
                                          CustomizeInvocationHandler h)
            throws IllegalArgumentException {

        File file = null;
        FileWriter fw = null;
        try {
            // 1.Generate source code
            String src = generate(interfaces);

            // 2.Create file endwith ".java"
            String path = MyProxy.class.getResource("").getPath();
            file = new File(path + "$Proxy0.java");
            fw = new FileWriter(file);
            fw.write(src);
            fw.flush();

            // 3.Compile the file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> objects = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, objects);
            task.call();
            manager.close();

            // 4.Load to jvm
            Class<?> proxy = loader.findClass("$Proxy0");

            // 5.Return agent
            Constructor<?> constructor = proxy.getConstructor(CustomizeInvocationHandler.class);
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file.exists()) {
                file.delete();
            }
        }

        return null;
    }

    /**
     * Generate source code
     *
     *@param interfaces The proxy class to implements
     *@return String source code
     *@date 2018-08-26
     *
     */
    private static String generate(Class<?> interfaces) {
        String ln = "\r\n";

        StringBuilder sb = new StringBuilder();
        sb.append("package cn.dark.dynamicproxy.custom.customize;" + ln)
                .append("import cn.dark.dynamicproxy.custom.customize.CustomizeInvocationHandler;" + ln)
                .append("import java.lang.reflect.Method;" + ln)
                .append("import java.lang.Throwable;" + ln)
                .append("public class $Proxy0 implements " + interfaces.getName() + " {" + ln)
                .append("private CustomizeInvocationHandler h;" + ln)
                .append("public $Proxy0 (CustomizeInvocationHandler h) {" + ln)
                .append("this.h = h;" + ln)
                .append("}" + ln);
        Method[] methods = interfaces.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();

            Parameter[] parameters = method.getParameters();

            // get parameters and parameterType from the method and add source code
            String s = Arrays.toString(parameters);
            String param = s.substring(s.indexOf("[") + 1, s.lastIndexOf("]"));

            StringBuilder args = null;
            StringBuilder argTypes = null;
            for (int i = 0; i < parameters.length; i++) {
                String[] arr = parameters[i].getType().toString().split(" ");
                if (args == null || "".equals(args)) {
                    args = new StringBuilder(parameters[i].getName());
                    argTypes = new StringBuilder(arr.length == 2 ? arr[1] : arr[0]).append(".class");
                } else {
                    args.append(", " + parameters[i].getName());
                    argTypes.append(", ").append(arr.length == 2 ? arr[1] : arr[0]).append(".class");
                }
            }
            sb.append("public " + method.getReturnType() + " " + methodName + "(" + param + ") throws Throwable {" + ln)
                    .append("Method m = " + interfaces.getName() + ".class.getMethod(\"" + methodName + "\", " + argTypes + ");" + ln)
                    .append("this.h.invoke(this, m, new Object[]{" + args.toString() + "});" + ln)
                    .append("}" + ln);
        }
        sb.append("}" + ln);

        return sb.toString();
    }

}
