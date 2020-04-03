package 代理;

/**
 * 目标对象
 */
public class SubjectImpl implements Subject {

    @Override
    public void request() {
        System.out.println("process target object method");
    }
}