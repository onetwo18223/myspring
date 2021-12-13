package demo.annotation;

/**
 * @author bhh
 * @description 测试 外观类
 * @date Created in 2021-04-26 15:13
 * @modified By
 */
@TestAnnotationClass(value = "这是方法类")
public class TestExterior {

    @TestAnnotationFiled(judge = true)
    public String wantTo = "study";

    @TestAnnotationMethod(num = 2)
    public void method(){
        System.out.println("这是method()方法");
    }
}
