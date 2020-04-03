package 图;

class GraphListNode {
    public int nodeno; //图中结点的编号
    public GraphListNode next; //指向下一个结点的指针
}

public class GraphList {
    private int size; //邻接矩阵的结点数
    private GraphListNode [] graphListArray; //邻接矩阵存储结点的数组



    /**
     * 深度遍历，邻接矩阵表视图
     * @param graph 图
     */
    public static void DFSGraphMatrix(GraphList graph) {
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
     * 图的深度优先遍历递归算法，邻接矩阵表视图
     * @param graph 图
     * @param visited 做标记的（设置点是否被访问）
     * @param i 结点编号
     */
    private static void DFS(GraphList graph, int[] visited, int i) {
        int j;
        GraphListNode tempNode = null;
        visited[i] = 1;
        System.out.print(i);
        tempNode = graph.graphListArray[i].next;
        while (tempNode != null) {
            if (visited[tempNode.nodeno] == 0) {
                DFS(graph, visited, tempNode.nodeno);
            }
            tempNode = tempNode.next;
        }
    }
}







