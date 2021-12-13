package demo.proxy.jdkproxy;

public class DoStudy implements DoStudyService{
    @Override
    public void study() {
        System.out.println("爱学习，爱生活");
    }
}