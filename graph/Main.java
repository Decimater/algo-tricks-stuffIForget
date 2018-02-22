import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Vertex v1 = new Vertex(1), v2 = new Vertex(2), v3 = new Vertex(3),  v4 = new Vertex(4),
                v5 = new Vertex(5), v6 = new Vertex(6);

        Edge e1 = new Edge(v1, v2), e2 = new Edge(v3, v4);

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        Graph graph = new Graph(new HashSet<>(Arrays.asList(e1, e2)));
        System.out.println(bfs.isConnected(v1, graph));

        Edge e3 = new Edge(v2, v3);
        graph = new Graph(new HashSet<>(Arrays.asList(e1, e2, e3)));
        System.out.println(bfs.isConnected(v1, graph));
        System.out.println(bfs.hopsBetween(v1, v3, graph));

        System.out.println("test shortest path");
        List<Vertex> shortestPath =  bfs.shortestPath(v1, v4, graph);
        System.out.println(shortestPath);

        /*
        ____v2___v3___
        |             |
        v1            v4
        |______v5_____|
         */

        Edge e4 = new Edge(v1, v5), e5 = new Edge(v5, v4);
        graph = new Graph(new HashSet<>(Arrays.asList(e1, e2, e3, e4, e5)));
        shortestPath = bfs.shortestPath(v1, v4, graph);
        System.out.println(shortestPath);

        System.out.println();
        System.out.println("Djikstra testing");

        /* Set up graph with costs
        _5__v2_5__v3___5___
        |                 |
        v1                v4
        |__10____v5___10__|

        Cost along the top 15 but more nodes
        Cost along the bottom 20 with fewer nodes
         */
        e1 = new Edge(v1, v2, 5); e2 = new Edge(v2, v3, 5); e3 = new Edge(v3, v4, 5);
        e4 = new Edge(v1, v5, 10); e5 = new Edge(v5, v4, 10);

        graph = new Graph(new HashSet<>(Arrays.asList(e1, e2, e3, e4, e5)));
        shortestPath = new Dijkstra().shortestPath(v1, v4, graph);
        System.out.println("dj shortest path");
        System.out.println(shortestPath);
    }
}