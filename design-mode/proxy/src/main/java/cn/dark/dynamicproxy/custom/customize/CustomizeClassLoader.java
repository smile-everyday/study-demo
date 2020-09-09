package cn.dark.dynamicproxy.custom.customize;

import java.io.*;

/**
 * @title customize class loader
 * @author Dark
 * @date 2018-08-26
 */
public class CustomizeClassLoader extends ClassLoader {

    private File baseDir;

    /**
     * Initialize the base path of the class
     *
     *@date 2018-08-26
     *
     */
    public CustomizeClassLoader() {
        String path = CustomizeClassLoader.class.getResource("").getPath();
        this.baseDir = new File(path);
    }

    /**
     * Finds the class with the specified 'name'
     *
     *@param name to find the class
     *@return Class<?> the class
     *@throws ClassNotFoundException if the class couldn't be found
     *@date 2018-08-26
     *
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = CustomizeClassLoader.class.getPackage().getName() + "." + name;
        File file = new File(baseDir, name.replaceAll("\\.", "/") + ".class");
        if (file.exists()) {
            FileInputStream fi = null;
            ByteArrayOutputStream bo = null;
            try {
                fi = new FileInputStream(file);
                bo = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len = -1;
                while ((len = fi.read(buff)) != -1) {
                    bo.write(buff, 0, len);
                }

                return defineClass(className, bo.toByteArray(), 0, bo.size());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (file.exists()) {
                    file.delete();
                }
            }
        }

        return super.findClass(name);
    }
}
