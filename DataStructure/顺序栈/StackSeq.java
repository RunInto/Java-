package 顺序栈;

import java.util.Scanner;

/**
 * 顺序栈
 */
 class SeqStack {
    private int MAX = -1; //最大容量
    private int top; //栈顶索引
    private int [] array; //存放元素的数组

    /**
     * 创建空顺序栈
     * @param max
     * @return
     */
    public static SeqStack setNullStackSeq(int max) {
        SeqStack sstack = new SeqStack();
        sstack.array = new int[max];
        sstack.MAX = max;
        sstack.top = -1;
        return sstack;
    }


    /**
     * 判断一个栈是否空
     * @param seqStack
     * @return
     */
    public boolean isNullStackSeq(SeqStack seqStack) {
        return seqStack.top == -1;
    }


    /**
     * 入栈
     * @param seqStack
     * @param x
     */
    public void pushSeq(SeqStack seqStack, int x) {
        //检查栈是否满
        if (seqStack.top >= seqStack.MAX -1) {
            System.out.println("Overflow!");
        }else {
            //若不满
            seqStack.top++;
            seqStack.array[seqStack.top] = x;
        }
    }


    /**
     * 出栈
     * @param seqStack
     */
    public void popSeq(SeqStack seqStack) {
        if (isNullStackSeq(seqStack)) {
            System.out.println("Underflow!");
        }else {
            seqStack.top--;
        }
    }


    /**
     * 求栈顶元素
     * @param seqStack
     * @return
     */
    public int topSeq(SeqStack seqStack) {
        if (isNullStackSeq(seqStack)) {
            System.out.println("it is empty!");
            return -1;
        }else {
            return seqStack.array[seqStack.top];
        }

    }



}

public class StackSeq {
    public static void main(String[] args) {
        SeqStack seqStack = SeqStack.setNullStackSeq(10);
        int data=-1;
        System.out.println("请输入进栈的元素，以-1结束：");
        Scanner sc = new Scanner(System.in);
        data = sc.nextInt();
        while (data != -1) {
            System.out.println("进入循环");
            seqStack.pushSeq(seqStack, data);
            data = sc.nextInt();
        }
        System.out.println("出栈元素的顺序是：");
        while (!seqStack.isNullStackSeq(seqStack)) {
            System.out.print(seqStack.topSeq(seqStack));
            seqStack.popSeq(seqStack);
        }
    }
}