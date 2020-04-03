package 队列;

import 树.BTreeNode;

//import java.util.Scanner;

class PNode {
    public BTreeNode data;
    public PNode link;
}

/**
 * 链队
 * 不存在假溢出问题(判断队列空和队列满需要牺牲一个元素空间)
 */
public class LinkQueue {
    public PNode f;
    public PNode r;


    public static LinkQueue setNullQueueLink() {
        return new LinkQueue();
    }

    public boolean isNullQueueLink(LinkQueue lqueue) {
        return lqueue.f == null;
    }

    public void enQueueLink(LinkQueue lqueue, BTreeNode x) {
        PNode p = new PNode();
        p.data = x;
        //由于队列刚初始化时队首队尾指针应指向同一位置，所以这里做队列为空的特殊处理
        if (lqueue.f == null) {
            lqueue.f = p;
            lqueue.r = p;
        }else {
            lqueue.r.link = p;//让队列中的最后一个元素的next域指向下一个元素
            lqueue.r = p;//修改队尾指针，队尾指针时刻指向最后一个元素
        }
    }

    /**
     * 出队
     * @param lqueue
     */
    public void deQueueLink(LinkQueue lqueue) {
        PNode p;
        if (lqueue.f == null) {
            System.out.println("it is empty queue!");
        }else {
            p = lqueue.f;
            lqueue.f = lqueue.f.link;
            p.link = null;
            p = null;
        }
    }

    public BTreeNode frontQueueLink(LinkQueue lqueue) {
        if (lqueue.f == null) {
            System.out.println("it is empty queue!");
            return null;
        }else {
            return lqueue.f.data;
        }
    }

  /*  public static void main(String[] args) {
        LinkQueue linkQueue = new LinkQueue().setNullQueueLink();
        int data;
        System.out.println("请输入进队的元素，以-999结束：");
        Scanner sc = new Scanner(System.in);
        data = sc.nextInt();
        while (data != -999) {
            linkQueue.enQueueLink(linkQueue, data);
            data = sc.nextInt();
        }
        System.out.println("出队元素的顺序为：");
        System.out.println("耗用时间：" + System.currentTimeMillis());
        while (!linkQueue.isNullQueueLink(linkQueue)) {
            System.out.print(linkQueue.frontQueueLink(linkQueue) + " ");
            linkQueue.deQueueLink(linkQueue);
        }
        System.out.println("耗用时间：" + System.currentTimeMillis());
    }*/
}