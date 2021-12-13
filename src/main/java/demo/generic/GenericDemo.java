package demo.generic;

import com.sun.xml.internal.txw2.DatatypeWriter;
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
    public <T , E> void getT(T t, E e) {
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
        // 可以作为参数, 但是不可以实例化

        @Override
        public void getT(String s) {
            super.getT(s);
        }

        @Override
        public String getE() {
            // 类中的泛型也不能直接实例化
//            T t = new T();
            T t = null;
            return "t";
        }
    }

    /*
     * 总结 : 泛型可以声明在 类, 方法上, 不可以声明在属性上
     *       泛型不能直接实例化, (可以创建引用指向null, T t = null;)
     *       泛型可以使用在 参数声明, 返回值
     *          public void getT(T t), public T getE()
     *       泛型定义:
     *          + 声明在类上的方式 ：class Test<T t>
     *          + 声明在方法上的方式 : public <T, E> void getT
     *          + 可以在声明时为 泛型添加上限 (无法使用 super) :
     *              + public <T extends String> void getT
     *              + class Test1<T extends Object>
     *       泛型使用:
     *          + 继承( 继承类带泛型 )泛型类( 未指定泛型, 则默认为 Object类型 , 例如:
     *                  class Test2<T> extends Test1 == class Test2<T> extends Test1<Object>)时,
     *              重写方法, 若是泛型充当参数, 则需要给具体类型
     *                    , 若是充当返回值, 则可以继续使用泛型
     *                    ( 因为, 新声明的泛型默认是Object子类, 所以可以 (子类重写返回对象是 父类返回类型的子类就行了) )
 *              + 若是继承的 仍是泛型 (, 例如 : class Test2<T> extends Test1<T> , 这种继承 T 不可以被更换),
 *                  重写方法, 可以继续使用泛型
     *
     *          + 当泛型类需要声明对象 : List<String> list
     *              + 当不确定传入泛型的是哪种类型是 可以使用通配符 ? (不常单独使用) : List<?> list
     *              + 还可以使用 List<? super String> list  泛型的子类必须有String
     *              + 还可以使用 List<? extend String> list 泛型必须继承 String
     *       泛型接口使用类似
     */
}
