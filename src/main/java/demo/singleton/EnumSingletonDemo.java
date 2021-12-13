package demo.singleton;

import java.lang.reflect.Constructor;

public class EnumSingletonDemo {
    public static void main(String[] args) throws Exception {
        System.out.println((EnumSingleton)EnumSingleton.INSTANCE);

        Class clazz = Class.forName("demo.singleton.EnumSingleton");
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton)declaredConstructor.newInstance();
        System.out.println(enumStarvingSingleton);
    }
}
