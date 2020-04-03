package 代理;

/**
 * 静态代理Demo
 * 代理对象持有目标对象的句柄
 * 所有调用目标对象的方法，都调用代理对象的方法
 * 对每隔方法，都需要静态编码（理解简单，操作繁琐）
 * 如果目标对象方法较多要使用动态代理
 */
public interface Subject {
    void request();
}

