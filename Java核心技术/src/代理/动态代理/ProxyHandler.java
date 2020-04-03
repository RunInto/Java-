package 代理.动态代理;

import 代理.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理处理类
 */
public class ProxyHandler implements InvocationHandler {

    /**
     * 持有目标对象的句柄
     */
    private Subject subject;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }


    /**
     * 此函数在代理对象调用任何一个方法时都会被调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理对象名字
        System.out.println(proxy.getClass().getName());
        //定义预处理的工作，也可以根据有method的不同进行不同的预处理
        System.out.println("===before===");
        Object o = method.invoke(subject,args);
        System.out.println("===after===");
        return o;
    }
}
