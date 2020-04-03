package 树;

import 链栈.LinkStack;
import 队列.LinkQueue;

import java.util.Scanner;

/**
 * 二叉树的链式存储
 * 其实树跟链表都是一样的，无非是链表是一个指一个，
 * 树是每隔节点都指着两个节点
 * 优点：结构灵活，操作方便
 * 缺点：在二叉链表中无法由结点直接找到双亲结点
 */
public class BTreeNode {
    public String data;
    public BTreeNode leftChild;
    public BTreeNode rightChild;


    public static void main(String[] args) {
//        BTreeNode bt = null;
//        System.out.println("请输入二叉树的先序序列：");
//        bt = createBinTreeRecursion();
//        System.out.println("二叉树的链式存储结构建立完成！");
//        System.out.println("该二叉树的先序遍历序列为：");
//        preOrderRecursion(bt);
//        System.out.println("该二叉树的中序遍历序列为：");
//        inOrderRecursion(bt);
//        System.out.println("该二叉树的后序遍历序列为：");
//        postOrderRecursion(bt);
        //=====================非递归==============================
        BTreeNode bt;
        System.out.println("二叉树非递归建立：");
        bt = createBinTreeNRecursion();
        System.out.println("层次遍历序列是：");
        levelOrder(bt);
    }


    /**
     * 递归方式以前序构造二叉树
     * @return
     */
    public static BTreeNode createBinTreeRecursion() {
        Scanner sc = new Scanner(System.in);
        BTreeNode bTreeNode = null;
        String data = sc.next();
        if ("@".equals(data)) {
            return bTreeNode;
        }else {
            bTreeNode = new BTreeNode();
            bTreeNode.data = data;
            //构造左子树
            bTreeNode.leftChild = createBinTreeRecursion();
            //构造右子树
            bTreeNode.rightChild = createBinTreeRecursion();
        }
        return bTreeNode;
    }


    /**
     * 非递归创建二叉树
     * @return
     */
    public static BTreeNode createBinTreeNRecursion() {
        Scanner sc = new Scanner(System.in);
        LinkQueue queue = LinkQueue.setNullQueueLink();
        BTreeNode s,p,bt = null;//每次进循环都置bt为空
        String ch;
        int count = -1;
        System.out.println("按照层次输入二叉树的结点，以#结束：");
        ch = sc.next();
        while (!"#".equals(ch)) {
            s = null;//假设读入的是虚结点“@”
            if (!"@".equals(ch)) {
                s = new BTreeNode();
                s.data = ch;
            }
            //将新结点地址或虚结点地址入队
            queue.enQueueLink(queue, s);
            ++count;
            if (count == 0) {
                bt = s;
            } else {
                p = queue.frontQueueLink(queue);
                //当前的结点是虚拟结点或者它的父亲结点是虚拟结点都不会使他们连接
                if (s != null && p!= null) {
                    //count为奇数，新结点作为左孩子插入
                    if (count % 2 == 1) {
                        p.leftChild = s;
                    }else {
                        //为偶数，新结点作为右孩子插入
                        p.rightChild = s;
                    }
                }
                //为什么这个不把下面这个语句放上面那个偶数的判断中呢？主要是防止连续三个都是@虚拟结点的情况下，
                //队头结点一直不出栈
                if (count % 2 == 0) {
                    //说明队头俩个孩子处理完了，队头出队
                    queue.deQueueLink(queue);
                }
            }
            ch = sc.next();
        }
        return bt;
    }


    /**
     * 先序非递归遍历 - 迭代一(左右孩子都入栈)
     * 核心就是把树出栈后是先序次序的序列入栈
     * @param bt
     */
    public static void preOrderNRecursion(BTreeNode bt) {
        //定义链栈
        LinkStack lstack = LinkStack.setNullStackLink();
        BTreeNode p;
        //根结点入栈
        lstack.pushLink(lstack, bt);
        while (!LinkStack.isNullStackLink(lstack)) {
            p = lstack.topLink(lstack);
            //访问结点
            System.out.println(p.data);
            //因为我们要先序访问，访问完根结点后接下来是左孩子，但是栈
            //后进先出，所以我先让右孩子入栈
            if (p.rightChild != null) {
                lstack.pushLink(lstack, p.rightChild);
            }
            if (p.leftChild != null) {
                lstack.pushLink(lstack, p.leftChild);
            }
        }
    }


    /**
     * 先序非递归遍历 - 迭代二(只右孩子入栈)
     * @param bt
     */
    public static void preOrderNRecursion2(BTreeNode bt) {
        //定义链栈
        LinkStack lstack = LinkStack.setNullStackLink();
        BTreeNode p;
        //根结点入栈
        lstack.pushLink(lstack, bt);
        while (!LinkStack.isNullStackLink(lstack)) {
            p = lstack.topLink(lstack);
            while (p != null) {
                //访问结点
                System.out.println(p.data);
                if (p.rightChild != null) {
                    lstack.pushLink(lstack, p.rightChild);
                }
                p = p.leftChild;
            }

        }
    }


    /**
     * 中序遍历非递归实现
     * @param bt
     */
    public static void inOrderNRecursion1(BTreeNode bt) {
        LinkStack lstack = LinkStack.setNullStackLink();
        BTreeNode p;
        p = bt;
        if (p == null) {return;}
        //根结点入栈
        lstack.pushLink(lstack, bt);
        //进入左子树
        p = p.leftChild;
        while (p != null || !LinkStack.isNullStackLink(lstack)) {
            while (p != null) {
                lstack.pushLink(lstack, p);
                p = p.leftChild;
            }
            //当左孩子的左孩子为空时开始访问父亲结点
            p = lstack.topLink(lstack);
            System.out.println(p.data);
            //访问完父亲结点后开始访问右孩子，
            //右孩子都在栈中，所以继续循环判断栈空否
            p = p.rightChild;
        }
    }


    /**
     * 后序遍历非递归实现
     * @param bt
     */
    public static void postOrderNRecursion(BTreeNode bt) {
        BTreeNode p = bt;
        //定义链栈
        LinkStack lstack;
        if (bt == null) {return;}
        lstack = LinkStack.setNullStackLink();
        while (p != null || !LinkStack.isNullStackLink(lstack)) {
            //一直遍历到最后一个左孩子
            while (p != null) {
                lstack.pushLink(lstack, p);
                //当p的左孩子为空并且右孩子也为空则跳出循环
                p = p.leftChild != null ? p.leftChild : p.rightChild;
            }
            p = lstack.topLink(lstack);
            System.out.println(p.data);
            if (!LinkStack.isNullStackLink(lstack) && lstack.topLink(lstack).leftChild == p) {
                p = lstack.topLink(lstack).rightChild;
            }else {
                p = null;
            }
        }
    }

    /**
     * 先序递归遍历
     * @param bt
     */
    public static void preOrderRecursion(BTreeNode bt) {
        if (bt == null ) {return;}
        System.out.print(bt.data);
        preOrderRecursion(bt.leftChild);
        preOrderRecursion(bt.rightChild);
    }


    /**
     * 递归中序遍历
     * @param bt
     */
    public static void inOrderRecursion(BTreeNode bt) {
        if (bt == null) {return;}
        inOrderRecursion(bt.leftChild);
        System.out.print(bt.data);
        inOrderRecursion(bt.rightChild);
    }


    /**
     * 递归后续遍历
     * @param bt
     */
    public static void postOrderRecursion(BTreeNode bt) {
        if (bt == null) {return;}
        postOrderRecursion(bt.leftChild);
        postOrderRecursion(bt.rightChild);
        System.out.print(bt.data);
    }


    /**
     * 销毁二叉树，二叉树构造完成后，存在引用关系，JVM短期不会自动垃圾回收，
     * 在对内存要求高的地方可以手动把对象置为空加快垃圾回收
     * @param bt
     */
    public static void destroyBinTree(BTreeNode bt) {
        if (bt != null) {
            destroyBinTree(bt.leftChild);
            destroyBinTree(bt.rightChild);
            bt = null;
        }
    }


    /**
     * 广度优先遍历(层次遍历)
     * @param bt
     */
    public static void levelOrder(BTreeNode bt) {
        BTreeNode p;
        //初始化队列
        LinkQueue linkQueue = new LinkQueue();
        if (bt == null) {return;}
        p = bt;
        linkQueue.enQueueLink(linkQueue,bt);
        while (linkQueue.isNullQueueLink(linkQueue) == false) {
            //出队
             p = linkQueue.frontQueueLink(linkQueue);
             linkQueue.deQueueLink(linkQueue);
            //访问队头节点的数据域
            System.out.println(p.data);
            //左孩子不空，则入队
            if (p.leftChild != null) {
                linkQueue.enQueueLink(linkQueue, p.leftChild);
            }
            if (p.rightChild != null) {
                linkQueue.enQueueLink(linkQueue, p.rightChild);
            }
        }
    }


    /**
     * 二叉排序树查找元素
     * @param bt
     * @param key
     * @return
     */
    public static BTreeNode bSTSearch(BTreeNode bt, String key) {
        BTreeNode p, parent;
        p = bt;
        parent = p;//记录待插入结点的父结点

        while(p != null) {
            parent = p;
            if (p.data.equals(key)) {
                System.out.println("exist this key");
                return null;
            }
            if (p.data.compareTo(key) > 0) {
                p = p.leftChild;
            }else {
                p = p.rightChild;
            }
        }
        return parent;
    }


    /**
     * 二叉排序树插入算法
     * @param bt
     * @param key
     * @return
     */
    public static int bBSTInsert(BTreeNode bt, String key) {
        BTreeNode p, temp;
        temp = bSTSearch(bt, key);
        if (temp == null) {
            System.out.println("exist this key");
            return 1;
        }
        p = new BTreeNode();
        p.data = key;
        p.leftChild = p.rightChild = null;
        if (key.compareTo(temp.data) < 0) {
            temp.leftChild = p;
        }else {
            temp.rightChild = p;
        }
        return 0;
    }
}
