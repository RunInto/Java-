package 代理.动态代理.多接口代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyHandler implements InvocationHandler {

    private Cook cook;

    public ProxyHandler(Cook cook) {
        this.cook = cook;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy类型：" + proxy.getClass().getName());
        System.out.println("调用方法" + method.getClass().getName() + "参数为：" + Arrays.deepToString(args));
        Object result = method.invoke(cook,args);
        return result;
    }
}
