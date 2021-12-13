package demo.singleton;

import java.lang.reflect.Constructor;

public class EnumStarvingSingletonDemo {
    public static void main(String[] args) throws Exception{
        System.out.println(EnumStarvingSingleton.getInstance());

        Class clazz = Class.forName("demo.singleton.EnumStarvingSingleton");
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton)declaredConstructor.newInstance();
        System.out.println(enumStarvingSingleton);

    }
}
