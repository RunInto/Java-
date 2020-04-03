package 代理.动态代理.多接口代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class MultipleInterfacesProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //生成目标对象
        Cook cook = new CookImpl();
        ClassLoader cl = MultipleInterfacesProxyTest.class.getClassLoader();
        //生成代理处理器
        ProxyHandler handler = new ProxyHandler(cook);
        //生成代理类型，既是Cook类型也是Driver类型
        Class<?> proxyClass = Proxy.getProxyClass(cl, new Class<?> [] {Driver.class,Cook.class});
        //生成代理对象
        Object proxy = proxyClass.getConstructor(new Class [] {InvocationHandler.class})
                .newInstance(new Object[] {handler});
        //生成的代理对象是否是代理类型
        System.out.println(Proxy.isProxyClass(proxyClass));
        Proxy p  = (Proxy) proxy;
        System.out.println(p.getInvocationHandler(proxy).getClass().getName());
        System.out.println("proxy类型"+proxyClass.getName());
        //代理对象都继承于java.lang.reflect.Proxy,但是获取父类却是Object而不是Proxy
        Class father = proxyClass.getSuperclass();
        System.out.println("proxy的父类类型："+father.getName());
        Class[] cs = proxy.getClass().getInterfaces();
        for (Class c:cs) {
            System.out.println("proxy的父接口类型："+c.getName());
        }
        System.out.println("===============================");

        //在多个接口的代理对象中调用同名的方法，以第一个接口的方法为准
        Cook c = (Cook) proxy;
        c.doWork();
        Driver d = (Driver) proxy;
        d.doWork();
    }
}
