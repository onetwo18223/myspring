package org.simpleframework.until;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类的集合
     * @param packageName 包名
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
//        1.获取类加载器
        ClassLoader classLoader = getClassLoader();

//        2.通过类加载器获取需要加载的资源（getResource(packageName)参数使用的分隔符号是/，需进行处理）
        URL url = classLoader.getResource(packageName.replace(".","/"));
        if(url == null){
            log.warn("ClassUtil extractPackageClass + package == null : " +packageName);
            return null;
        }

//        3.依据不同的资源类型（符合条件的文件，不符合的条件文件，文件夹），采用不同的方式获取资源
        Set<Class<?>> classSet = null;
        if(! url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            return null;
        }
        classSet = new HashSet<Class<?>>();
//      url.getPath() : /F:/maker/JAVA/sources/myspring/target/classes/com/bhh/controller
        File packageDirectory = new File(url.getPath());
        extractClassFile(classSet, packageDirectory, packageName);
        return classSet;
    }

    /**
     * 通过递归来获取指定目录package下的Class文件（包括子package下的class文件）
     * @param classSet 存储class
     * @param packageDirectory //File文件或者目录
     * @param packageName //指定包名
     * @return 类集合
     */
    private static void extractClassFile(Set<Class<?>> classSet, File packageDirectory, String packageName) {
//        非包直接返回
        if(! packageDirectory.isDirectory()){
            return ;
        }
//        如果是文件夹，则调用方法listFiles方法获取文件夹下的文件以及文件夹
//        FileFilter():文件过滤器
        File[] files = packageDirectory.listFiles(new FileFilter() {
//            需要对非class文件进行过滤
            @SneakyThrows
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }
//                获取绝对值路劲
                String absolutePath = file.getAbsolutePath();
                //file.getPath();
                if(absolutePath.endsWith(".class")){
//                    直接将class文件放置到classSet中
                    addToClassSet(absolutePath);
                }
                return false;
            }

//            根据绝对值路径，获取且生成Class对象，并将其添加到classSet中
            private void addToClassSet(String absolutePath){
//                1.从absolutePath中获取包名
                absolutePath = absolutePath.replace(File.separator,".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
//                2.通过反射机制获取Class对象，并加入对应的classset中
                Class<?> clazz = loadClass(className);
                classSet.add(clazz);
            }
        });
        if(files != null){
            for (File file : files) {
                extractClassFile(classSet, file, packageName);
            }
        }
    }

    /**
     * 根据包名+类名获取Class对象
     * @param className 包名+类名
     * @return Class对象
     */
    public static Class<?> loadClass(String className){
        try {
            Class<?> clazz = Class.forName(className);
            return clazz;
        } catch (ClassNotFoundException e) {
            log.warn("loadClass ClassNotFound "+className);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取类加载器classloader
     * @return
     */
    public static ClassLoader getClassLoader(){
//        Thread.currentThread()获取当前线程
//        getContextClassLoader()获取当前线程使用的类加载器classloader
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取实现类
     * @param clazz 类对象
     * @param accessible 是否启用暴力调用
     * @param <T>
     * @return 反射通过构造函数生成的实例化对象
     */
    public static<T> T newInstatnce(Class<?> clazz, boolean accessible){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            T t = (T)constructor.newInstance();
            return t;
        } catch (Exception e) {
            log.warn("Exception in newInstatnce");
            throw new RuntimeException(e);
        }
    }

    /**
     * 为属性赋值
     * @param field 具体的属性
     * @param objInstance 类实例
     * @param fieldValue 赋予的值
     * @param accessible 是否允许变量为私有
     */
    public static void setField(Field field, Object objInstance, Object fieldValue, boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(objInstance, fieldValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Exception in setField() : 为属性赋值失败");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Set<Class<?>> classes = extractPackageClass("com.bhh.entity");
        System.out.println(classes);
    }

}
