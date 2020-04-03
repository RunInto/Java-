package 代理.静态代理;

import 代理.Subject;
import 代理.SubjectImpl;


/**
 * 代理对象
 */
class StaticProxy implements Subject {

    //实际目标对象
    private Subject subject;

    public StaticProxy(Subject s) {
        this.subject = s;
    }

    @Override
    public void request() {
        //条件审查
        System.out.println("preprocess");
        subject.request();
        //目标对象处理完执行一些后置处理
        System.out.println("postprocess");
    }
}

public class StaticProxyDemo {
    public static void main(String[] args) {
        //创建实际对象
        SubjectImpl impl = new SubjectImpl();
        //把实际对象封装到代理对象中
        StaticProxy p = new StaticProxy(impl);
        p.request();
    }
}
