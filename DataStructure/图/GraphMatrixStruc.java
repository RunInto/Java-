package 图;

import 队列.LinkQueue;

import java.util.Scanner;

public class GraphMatrixStruc {
    private int size; //图中结点的个数
    private int [][] graphArr; //二维数组保存图


    /**
     * 初始化邻接矩阵
     * @param num
     * @return
     */
    public static GraphMatrixStruc initGraph(int num) {
        int i, j;
        GraphMatrixStruc graph = new GraphMatrixStruc();
        graph.size = num;
        graph.graphArr = new int [num][num];
        for (i = 0; i<graph.size; i++) {
            for (j = 0; j < graph.size; j++) {
                graph.graphArr[i][j] = Integer.MAX_VALUE;
            }
        }
        return graph;
    }


    /**
     * 读入邻接矩阵
     * @param graph
     */
    public static void readGraph(GraphMatrixStruc graph) {
        int vex1, vex2, weight;
        Scanner sc = new Scanner(System.in);
        System.out.println("输入方式为点 点 权值，权值为0，则输入结束");
        vex1 = sc.nextInt();
        vex2 = sc.nextInt();
        weight = sc.nextInt();
        while (weight != 0) {
            graph.graphArr[vex1][vex2] = weight;
            vex1 = sc.nextInt();
            vex2 = sc.nextInt();
            weight = sc.nextInt();
        }
    }


    /**
     * 将图展示出来
     * @param graph
     */
    public static void writeGraph(GraphMatrixStruc graph) {
        int i, j;
        System.out.println("图的结构如下，输出方式为点 点 权值");
        for (i = 0; i < graph.size; i++) {
            for (j = 0; j < graph.size; j++) {
                if (graph.graphArr[i][j] < Integer.MAX_VALUE) {
                    System.out.printf("%d,%d,%d\n",i, j, graph.graphArr[i][j]);
                }
            }
        }
    }


    /**
     * 深度遍历，邻接矩阵表视图
     * @param graph 图
     */
    public static void DFSGraphMatrix(GraphMatrixStruc graph) {
        int i;
        int [] visited = new int[graph.size];
        for (i = 0; i < graph.size; i++) {
            visited[i] = 0;
        }
        for (i = 0; i < graph.size; i++) {
            //对未访问过的顶点调用DFS，若是连通图，只会执行一次
            if (visited[i] == 0) {
                DFS(graph, visited, i);
            }
        }
    }

    /**
     * 深度优先遍历递归算法，邻接矩阵表示图
     * @param graph 图
     * @param visited 做标记的（设置点是否被访问）一维数组
     * @param i 结点编号
     */
    public static void DFS(GraphMatrixStruc graph, int [] visited, int i) {
        int j;
        visited[i] = 1;
        System.out.print(i);
        for (j = 0; j < graph.size; j++) {
            //顶点之间有边相连切j没有被访问过
            if (graph.graphArr[i][j] != Integer.MAX_VALUE && visited[j] == 0) {
                DFS(graph, visited, j);
            }
        }
    }


    /**
     * 图的广度优先遍历，邻接矩阵表示图
     * @param graphMatrixStruc 图
     */
    public static void BFSGraphMatrix(GraphMatrixStruc graphMatrixStruc) {
        int i;
        int [] visited = new int[graphMatrixStruc.size];
        for (i = 0; i < graphMatrixStruc.size; i++) {
            visited[i] = 0;
        }
        //从0号结点开始进行广度优先遍历
        for (i = 0; i < graphMatrixStruc.size; i++) {
            BFS(graphMatrixStruc, visited, i);
        }
    }


    /**
     * 求连通分量
     * @param graphMatrixStruc
     */
    public static void connectedComponent(GraphMatrixStruc graphMatrixStruc) {
        int i;
        int brance = 0;
        int [] visited = new int[graphMatrixStruc.size];
        for (i = 0; i < graphMatrixStruc.size; i++) {
            visited[i] = 0;
        }
        //从0号结点开始进行广度优先遍历
        for (i = 0; i < graphMatrixStruc.size; i++) {
            if (visited[i] == 0) {
                ++brance;
                BFS(graphMatrixStruc, visited, i);
            }
        }
        if (brance ==1) {
            System.out.println("该图是连通图");
        }else {
            System.out.printf("该图不是连通图，连通分量个数为，%d",brance);
        }
    }


    /**
     * 图的广度优先遍历算法
     * @param graphMatrixStruc
     * @param visited
     * @param i
     */
    private static void BFS(GraphMatrixStruc graphMatrixStruc, int[] visited, int i) {
        int j,tempVex;
        LinkQueue waitingQueue = null;
        waitingQueue = LinkQueue.setNullQueueLink();
        //如果没有被访问过，则访问
        if (visited[i] == 0) {
            visited[i] = 1;
            System.out.print(i);
            //将刚被访问的结点放入队列
            waitingQueue.enQueueLink(waitingQueue,i);
            //访问结点，广度优先
            while (!waitingQueue.isNullQueueLink(waitingQueue)) {
                tempVex = waitingQueue.frontQueueLink(waitingQueue);
                waitingQueue.deQueueLink(waitingQueue);
                for (j = 0; j < graphMatrixStruc.size; j++) {
                    //如果其他顶点与当前顶点存在边且未被访问过
                    if (graphMatrixStruc.graphArr[tempVex][j] != Integer.MAX_VALUE && visited[j] == 0) {
                        visited[j] = 1;
                        waitingQueue.enQueueLink(waitingQueue,j);
                        System.out.print(j);
                    }
                }

            }
        }
    }
}
