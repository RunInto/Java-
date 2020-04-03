package 链栈;


import java.util.Scanner;

public class MazeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Maze maze = new Maze();
        //迷宫大小
        int mazeSize;
        int entryX, entryY, exitX, exitY;
        int find = 0;
        System.out.println("请输入迷宫大小：");
        mazeSize = sc.nextInt();
        entryX = 0;
        entryY=0;
        exitX = mazeSize - 1;
        exitY = exitX;
        maze = maze.inilMaze(mazeSize);
        maze.readMaze(maze);
        System.out.println("输入的迷宫结构如下：");
        System.out.println("请输入迷宫的入口点x,y,出口点x,y");
        entryX = sc.nextInt();
        entryY = sc.nextInt();
        exitX = sc.nextInt();
        exitY = sc.nextInt();
        //深度优先搜索
        find = maze.mazeDFS(entryX, entryY, exitX, exitY, maze);
        if (find == 0) {
            System.out.println("找不到路径");
        }
    }
}

/**
 * 迷宫实体
 */
class Maze {
    private int size;//迷宫大小
    private int [][] data;//二维数组保存迷宫结构

    /**
     * 初始化迷宫，分配空间，并将所有点置为0
     * @param size
     * @return
     */
    public Maze inilMaze (int size) {
        this.size = size;
        this.data = new int[size][size];
        return this;
    }


    /**
     * 读入迷宫结构，0代表可以走的路，1代表墙
     * @param maze
     */
    public void readMaze(Maze maze) {
        System.out.println("请用矩阵的形式输入迷宫的结构：");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i<this.size; i++) {
            for (int j = 0; j<this.size; j++) {
                this.data[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * 将迷宫的结构显示出来
     * @param maze
     */
    public void writeMaze(Maze maze) {

    }


    /**
     * 深度优先搜索路径
     * @param entryX 迷宫入口点x坐标
     * @param entryY 迷宫入口点y坐标
     * @param exitX  迷宫出口x坐标
     * @param exitY 迷宫出口y坐标
     * @param maze 迷宫结构
     * @return 1 - 成功 0 - 失败
     */
    public int mazeDFS(int entryX, int entryY, int exitX, int exitY, Maze maze) {
        //定义8个方向
        int [][] direction = {
                {0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}
        };
        //使用两个栈存储路径中点(x,y)的坐标
        LinkStack linkStackX = null;
        LinkStack linkStackY = null;
        //临时变量，存放当前探索点坐标(x,y)
        int posX,posY = -999;
        //临时变量，存放之前探索点坐标(x,y)
        int prePostX,prePostY = -999;
        //标记二维数组，标记哪些点走过，不再重复走
        int [][] mark;
        //循环变量
        int i,j = -999;
        //移动的方向
        int mov = -999;
        //给做标记的二维数组分配空间，并赋初值
        mark = new int[maze.size][maze.size];
        for (i = 0; i<maze.size; i++) {
            for (j = 0; j<maze.size; j++) {
                mark[i][j] = 0;
            }
        }

        //初始化栈
        linkStackX = new LinkStack().setNullStackLink();
        linkStackY = new LinkStack().setNullStackLink();
        //将入口点标志位设置为1
        mark[entryX][entryY] = 1;
        //入口点入栈
        linkStackX.pushLink(linkStackX, entryX);
        linkStackY.pushLink(linkStackY, entryY);
        //如果栈不为空且还没有找到迷宫出口点
        while (!new LinkStack().isNullStackLink(linkStackX)) {
            prePostX = linkStackX.topLink(linkStackX);
            prePostY = linkStackY.topLink(linkStackY);
            System.out.println("出栈元素是：(" + prePostX + ","+ prePostY +")");
            mov = 0;
            while (mov <8) {
                posX = prePostX + direction[mov][0];
                posY = prePostY + direction[mov][1];
                //到达终点
                if (posX == exitX && posY == exitY) {
                    //出口点入栈
                    linkStackX.pushLink(linkStackX, prePostX);
                    linkStackY.pushLink(linkStackY, prePostY);
                    System.out.println("深度搜索迷宫路径如下：");
                    System.out.println("("+exitX+","+exitY+")");
                    //将路径逆序输出
                    while (new LinkStack().isNullStackLink(linkStackX)) {
                        posX = linkStackX.topLink(linkStackX);
                        posY = linkStackY.topLink(linkStackY);
                        System.out.println("("+posX+","+posY+")");
                    }
                    return 1;
                }
                //这个位置是通路且没有走过
                if (maze.data[posX][posY] == 0 && mark[posX][posY] == 0) {
                    mark[posX][posY] = 1;
                    linkStackX.pushLink(linkStackX, prePostX);
                    linkStackY.pushLink(linkStackY, prePostY);
                    System.out.println("入栈元素是：("+prePostX+","+prePostY+")");
                    prePostX = posX;
                    prePostY = posY;
                    //已经往前走了，因此方向重新从0号方向开始搜索
                    mov = 0;
                }else {
                    mov++;
                }
            }
        }
        return 0;
    }
}




