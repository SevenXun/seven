package com.wang.provider.jvm;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassUtil {
    private static JavaCompiler compiler;
    private static String CLASS_PATH;

    static {
        compiler = ToolProvider.getSystemJavaCompiler();
        try {
            CLASS_PATH = new File("").getAbsolutePath();
            CLASS_PATH += "\\seven-provider\\src\\main\\java\\";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取java文件路径
     *
     * @param file
     * @return
     */
    private static String getFilePath(String file) {
        int last1 = file.lastIndexOf('/');
        int last2 = file.lastIndexOf('\\');
        return file.substring(0, last1 > last2 ? last1 : last2) + File.separatorChar;
    }

    /**
     * 编译java文件
     *
     * @param ops   编译参数
     * @param files 编译文件
     */
    private static void javac(List<String> ops, String... files) {
        StandardJavaFileManager manager = null;
        try {
            manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
            task.call();
            for (String file : files)
                System.out.println("Compile Java File:" + file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载类
     *
     * @param name 类名
     * @return
     */
    private static Class<?> load(String name) {
        Class<?> cls = null;
        ClassLoader classLoader = null;
        try {
            classLoader = ClassUtil.class.getClassLoader();
            cls = classLoader.loadClass(name);
            System.out.println("Load Class[" + name + "] by " + classLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cls;
    }

    /**
     * 编译代码并加载类
     *
     * @param filePath java代码路径
     * @param source   java代码
     * @param clsName  类名
     * @param ops      编译参数
     * @return
     */
    public static Class<?> loadClass(String filePath, String source, String clsName, List<String> ops) {
        try {
            javac(ops, CLASS_PATH + filePath);
            ClassLoader myByteClassLoader = new MyByteClassLoader();
            return myByteClassLoader.loadClass(CLASS_PATH + clsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用类方法
     *
     * @param cls        类
     * @param methodName 方法名
     * @param paramsCls  方法参数类型
     * @param params     方法参数
     * @return
     */
    public static Object invoke(Class<?> cls, String methodName, Class<?>[] paramsCls, Object[] params) {
        Object result = null;
        try {
            Method method = cls.getDeclaredMethod(methodName, paramsCls);
            Object obj = cls.newInstance();
            result = method.invoke(obj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        System.out.println(new BusServiceImpl().doIt("before xxx"));
        String cPath = CLASS_PATH + "com.wang.provider.jvm.BusServiceImpl".replace(".", "\\") + ".java";
        File file = new File(cPath);
        System.out.println(cPath + ";" + file.exists());
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");

        BufferedReader br = new BufferedReader(isr);
        String textLine = null;
        StringBuilder sb = new StringBuilder();
        while ((textLine = br.readLine()) != null) {
            sb.append(textLine);
        }
        br.close();

        //设置编译参数
        ArrayList<String> ops = new ArrayList<String>();
        ops.add("-Xlint:unchecked");
        //编译代码，返回class
        Class<?> cls = ClassUtil.loadClass("com\\wang\\provider\\jvm\\BusServiceImpl.java", sb.toString(), "com\\wang\\provider\\jvm\\BusServiceImpl.class", ops);
        /**
         * 不同的加载器的类加载的 不是同一个类，不可强制转换
         * BusServiceImpl busService = (BusServiceImpl)cls.newInstance();
         * busService.doIt("???????");
         */
        //执行测试方法
        Object result = ClassUtil.invoke(cls, "doIt", new Class[]{String.class}, new Object[]{"testx"});
        //输出结果
        System.out.println(result);
        BusService busService = new BusServiceImpl();
        //busService 与 cls不是同一个类，因为是不同的加载器加载的
        busService.doIt("un_change");

    }
}
