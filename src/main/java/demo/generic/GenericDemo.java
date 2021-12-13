package demo.generic;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bhh
 * @description 泛型学习
 * @date Created in 2021-04-26 9:24
 * @modified By
 */
@Slf4j
public class GenericDemo {
    public static void main(String[] args) {
        List<? super String> link = new LinkedList();
        link.add("1");
    }

    /*
     * 泛型不可以声明在属性上
     * private String test2<T>
     * private String<T> test2
     * private <T> String test2
     */
//    private String test2<T> = new

    // 方法上泛型的声明方式
    public <T, E> void getT(T t, E e) {
        // 错误, 不能直接实例化
//        T t1 = new T();
        System.out.println(t + "" + e);
    }

    // 类上的泛型的表示方式
    class Test1<T extends String> {
        // 可以作为参数, 但是不可以实例化
        public void getT(T t) {
            // 类中的泛型也不能直接实例化
//            T t = new T();
            return;
        }

        public T getE() {
            // 类中的泛型也不能直接实例化
//            T t = new T();
            T t = null;
            return t;
        }
    }

    class Test2<T> extends Test1<String> {
        Test2() {
        }

        @Override
        // 重写方法, 泛型参数需要指定类型
        public void getT(String s) {
            super.getT(s);
        }

        @Override
        public String getE() {
            // 类中的泛型也不能直接实例化
//            T t = new T();
            T t = null;
            // 泛型返回值也需要指定类型
            return "t";
        }

        // 测试在声明时, 可以使用extends, 而不能使用super
        public <E extends String> void getG(){
        }
    }

    class Test3{
        // 使用泛型需要提前声明
        //public void Test(T t){}
    }


    /*- 怎么用
    - 声明

    泛型可以**声明在类, 方法**上, 不可以直接声明在属性上

        - 声明在类上的方式 : `class Test<T t>`
            - 声明在方法上的方式 : `public <T, E> void getT`
            - 注意: 声明时可以使用 `extends`, 但是无法使用 `super`
            - 使用
        - 泛型可以**使用在参数和返回值上, 也可以使用通配符替代类**
            - `List<?> list, List<? super String> list, List<? extend String> list`
            - 泛型不可以实例化, 可以创建引用
        - 继承泛型类
            - 未指定泛型, 则默认为 Object类型 , 例如 : `class Test2<T> extends Test1 == class Test2<T> extends Test1<Object>`
            - 重写具有泛型参数 / 泛型返回值的方法, 需要给特定类型*/
}
