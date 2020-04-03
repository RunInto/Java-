package 链栈;

import 树.BTreeNode;

public class LinkStack {
    private BTreeNode data;
    private LinkStack next;

    public static LinkStack setNullStackLink() {
        LinkStack top = new LinkStack();
        return top;
    }


    public static boolean isNullStackLink(LinkStack top) {
        if (top.next == null) {
            return true;
        }else {
            return false;
        }
    }

    public void pushLink(LinkStack top, BTreeNode x) {
        LinkStack p = new LinkStack();
        p.data = x;
        p.next = top.next;
        top.next = p;
    }

    /**
     * 删除栈顶元素
     * @param top
     */
    public void popLink(LinkStack top) {
        LinkStack p;
        if (top.next == null) {
            System.out.println("it is empty stack!");
        }else {
            p = top.next;
            top.next = p.next;
            p = null;
        }
    }


    public BTreeNode topLink(LinkStack top) {
        if (top.next == null) {
            System.out.println("empty stack!");
            return null;
        }else {
            BTreeNode data =  top.next.data;
            this.popLink(top);
            return data;
        }
    }
}