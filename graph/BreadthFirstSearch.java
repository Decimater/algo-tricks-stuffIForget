import java.util.*;

/**
 * I can answer, search a graph for connections,
 *               shortest amount of hops between two vertices,
 *               are there any disconnected vertices
 */
public class BreadthFirstSearch {

    /**
     * @param source where to start
     * @param graph
     * @return amount of connected vertices equals the amount of vertices in the graph
     * @throws IllegalArgumentException if source or graph are null
     */
    public boolean isConnected(Vertex source, Graph graph) {
        if (source == null || graph == null) throw new IllegalArgumentException("Vertex and graph cannot be null");
        if (graph.getVertices().size() <= 1) return true;

        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> toExplore = new LinkedList<>();
        toExplore.add(source);

        while (!toExplore.isEmpty()) {
            Vertex vertex = toExplore.poll();
            List<Vertex> neighbours = graph.connectedVertices(vertex);
            visited.add(vertex);
            neighbours.removeAll(visited);
            toExplore.addAll(neighbours);
        }

        return visited.size() == graph.getVertices().size();
    }

    /**
     * Shortest possible amount of hops on an unweighted graph
     * @param source
     * @param target
     * @param graph
     * @return -1 if source or target do not exist in graph, or if not connected
     * @throws IllegalArgumentException if any argument is null
     */
    public int hopsBetween(Vertex source, Vertex target, Graph graph) {
        if (source == null || target == null || graph == null) throw new IllegalArgumentException("source, target, and graph cannot be null");

        int noConnection = -1;
        boolean containsSourceAndTarget = graph.getVertices().containsAll(Arrays.asList(source, target));
        if (!containsSourceAndTarget) return noConnection;

        Queue<Vertex> toExplore = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        toExplore.add(source);
        int hops = 0;
        boolean targetFound = false;

        while (!toExplore.isEmpty() && !targetFound) {
            hops++;
            Vertex vertex = toExplore.poll();
            visited.add(vertex);

            List<Vertex> neighbours = graph.connectedVertices(vertex);
            if (neighbours.contains(target)) {
                targetFound = true;
            } else {
                neighbours.removeAll(visited);
                toExplore.addAll(neighbours);
            }
        }

        return targetFound ? hops : noConnection;
    }

    /***
     * @param source
     * @param target
     * @param graph
     * @return Path between source and target, no possible path will be empty
     */
    public List<Vertex> shortestPath(Vertex source, Vertex target, Graph graph) {
        if (source == null || target == null || graph == null) throw new IllegalArgumentException("source, target, graph cannot be null");

        Queue<Vertex> toExplore = new LinkedList<>();
        toExplore.add(source);
        HashMap<Vertex, Vertex> whoseYourDaddy = new HashMap<>();
        whoseYourDaddy.put(source, null);
        Set<Vertex> visited = new HashSet<>();

        boolean targetFound = false;

        while(!toExplore.isEmpty() && !targetFound) {
            Vertex vertex = toExplore.poll();
            List<Vertex> neighbours = graph.connectedVertices(vertex);

            neighbours.stream().filter(v -> !whoseYourDaddy.containsKey(v)).forEach(v -> whoseYourDaddy.put(v, vertex));
            visited.add(vertex);

            if (neighbours.contains(target)) {
                targetFound = true;
            } else {
                neighbours.removeAll(visited);
                toExplore.addAll(neighbours);
            }
        }

        Vertex pathNode = target;
        List<Vertex> path = new LinkedList<>();

        while (pathNode != null) {
            path.add(0, pathNode);
            pathNode = whoseYourDaddy.get(pathNode);
        }

        return path;
    }
}
