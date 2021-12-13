package demo.singleton;

public enum EnumSingleton {
    INSTANCE;
    private EnumSingleton getInstance(){
        return INSTANCE;
    }

}
