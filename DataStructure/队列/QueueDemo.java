package 队列;


import java.util.Scanner;

public class QueueDemo {
}






/**
 * 顺序循环队列
 * 存在假溢出(判断队列空和队列满需要牺牲一个元素空间)
 */
class SeqQueue {
    private int MAX;
    private int f;
    private int r;
    private int [] elem;


    public SeqQueue setNullQueueSeq(int m) {
        SeqQueue seqQueue = new SeqQueue();
        seqQueue.elem = new int [m];
        seqQueue.MAX = m;
        seqQueue.f = 0;
        seqQueue.r = 0;
        return seqQueue;
    }

    public void enQueueSeq(SeqQueue seqQueue, int x) {
        //为了和队空判断分开，故意牺牲一个空间，这儿 % MAX也是为了防止假溢出
        if ((seqQueue.r + 1) % seqQueue.MAX == seqQueue.f) {
            System.out.println("it is FULL Queue!");
        }else {
            seqQueue.elem[seqQueue.r] = x;
            //防止假溢出
            seqQueue.r = (seqQueue.r + 1) % seqQueue.MAX;
        }
    }


    public boolean isNullQueueSeq(SeqQueue seqQueue) {
        return seqQueue.f == seqQueue.r;
    }


    public void deQueueSeq(SeqQueue seqQueue) {
        if (seqQueue.isNullQueueSeq(seqQueue)) {
            System.out.println("it is empty queue!");
        }else {
            seqQueue.f = (seqQueue.f + 1) % seqQueue.MAX;
        }
    }

    public int frontQueueSeq(SeqQueue seqQueue) {
        if (seqQueue.f == seqQueue.r) {
            System.out.println("it is empty queue!");
            return -999;
        }else {
            return seqQueue.elem[seqQueue.f];
        }
    }

    public static void main(String[] args) {
        SeqQueue seqQueue = new SeqQueue().setNullQueueSeq(5);
        int data;
        System.out.println("请输入进队的元素，以-999结束：");
        Scanner sc = new Scanner(System.in);
        data = sc.nextInt();
        while (data != -999) {
            seqQueue.enQueueSeq(seqQueue, data);
            data = sc.nextInt();
        }
        System.out.println("出队元素的顺序为：");
        while (!seqQueue.isNullQueueSeq(seqQueue)) {
            System.out.print(seqQueue.frontQueueSeq(seqQueue) + " ");
            seqQueue.deQueueSeq(seqQueue);
        }
    }
}
