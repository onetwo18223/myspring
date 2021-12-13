package demo.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StarvingSingletonDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(StarvingSingleton.getStarvingSingleton());
        System.out.println(StarvingSingleton.getStarvingSingleton());
        Class clazz = Class.forName("demo.singleton.StarvingSingleton");
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        StarvingSingleton starvingSingleton = (StarvingSingleton)declaredConstructor.newInstance();
        System.out.println(starvingSingleton);
    }
}
