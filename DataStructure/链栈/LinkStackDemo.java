package 链栈;

/**
 * 链栈
 */
public class LinkStackDemo {
    private static LinkStack s = new LinkStack();
    //进制转换
    public static void conversion(LinkStack ps, int n) {
        int temp;
        while (n != 0) {
            s.pushLink(ps, n%8);
            n /= 8;
        }
        System.out.println("转换为8进制后的结果：");
        while (!s.isNullStackLink(ps)) {
            temp = s.topLink(ps);
            System.out.print(temp);
        }
    }

    public static void main(String[] args) {
        LinkStack l = s.setNullStackLink();
        conversion(l,18);
    }
}



