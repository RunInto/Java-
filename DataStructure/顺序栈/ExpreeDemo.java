package 顺序栈;


import java.util.Scanner;

public class ExpreeDemo {
}


class ExpressValue {

    private SeqStack seqStack = SeqStack.setNullStackSeq(20);

    public int countLastExpree(char [] expree) {
        char c;
        int i = 0;
        for (; i<expree.length; i++) {
            c = expree[i];
            //如果是操作数，则压入栈中
            if (c >= 48 && c <= 57 ) {
                seqStack.pushSeq(seqStack, c);
            }
            //如果是操作符，则从栈中弹出两个操作数进行计算并将结果压入栈中
            if (c == '+' || c == '-' || c == '*' || c== '/') {
                countExpree(c);
            }
        }
        //扫描结束时，栈顶元素就是表达式的值
        if (i == expree.length) {
            return seqStack.topSeq(seqStack);
        }
        return -999;
    }


    private void countExpree(char c) {

        int valueTop, valueDown, result = -999;

        valueTop = seqStack.topSeq(seqStack);
        seqStack.popSeq(seqStack);
        valueDown = seqStack.topSeq(seqStack);
        seqStack.popSeq(seqStack);

        if (c == '+') {
            result = valueDown + valueTop;
        }

        if (c == '-') {
            result = valueDown - valueTop;
        }

        if (c == '*') {
            result = valueDown * valueTop;
        }

        if (c == '/') {
            result = valueDown / valueTop;
        }

        seqStack.pushSeq(seqStack, result);
    }

    public static void main(String[] args) {
        char temp;
        ExpressValue expressValue = new ExpressValue();
        char [] expr = new char [20];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入中缀表达式，以-1结束：");
        for (int i = 0; i<expr.length; i++) {
            temp =  sc.next().charAt(0);
            if (temp == '=') {
                break;
            }
            expr[i] = temp;
        }
        System.out.println("输入的表达式为：");
        for (int i = 0; i<expr.length; i++) {
            System.out.print(expr[i] + " ");
        }
        int result = expressValue.countLastExpree(expr);
        System.out.println(result);
    }
}