package demo.singleton;

public class StarvingSingleton {
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();
    private StarvingSingleton(){};
    public static StarvingSingleton getStarvingSingleton(){
        return starvingSingleton;
    }
}
