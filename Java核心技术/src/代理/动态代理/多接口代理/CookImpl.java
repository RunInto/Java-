package 代理.动态代理.多接口代理;

public class CookImpl implements Cook {
    @Override
    public void doWork() {
        System.out.println("cook for you");
    }
}
