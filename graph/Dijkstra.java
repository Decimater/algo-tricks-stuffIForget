import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {
    /***
     * Edges are treated as v1 -> v2, can handle cycles, no negative or 0 edges
     * @param source
     * @param target
     * @param graph
     * @return
     */
    public List<Vertex> shortestPath(Vertex source, Vertex target, Graph graph) {
        Queue<Vertex> toExplore = new LinkedList<>();
        toExplore.add(source);
        Map<Vertex, Vertex> whoseYourDaddy = new HashMap<>();
        Map<Vertex, Integer> vertexCost = new HashMap<>();
        graph.getVertices().forEach(v -> vertexCost.put(v, Integer.MAX_VALUE));

        while (!toExplore.isEmpty()) {
            Vertex vertex = toExplore.poll();
            List<Edge> connectedEdges = graph.edgesContainingVertex(vertex)
                    .stream()
                    .filter(e -> e.getV1() == vertex)
                    .collect(Collectors.toList());

            for (Edge edge : connectedEdges) {
                int parentCost = vertex == source ? 0 : vertexCost.get(vertex);
                int newTotalWeight = edge.getWeight() + parentCost;
                int currentWeight = vertexCost.get(edge.getV2());

                if (newTotalWeight < currentWeight) {
                    Vertex childOfVertex = edge.getV2();
                    whoseYourDaddy.put(childOfVertex, vertex);
                    vertexCost.put(childOfVertex, newTotalWeight);
                    toExplore.add(childOfVertex);
                }
            }
        }

        Vertex temp = target;
        List<Vertex> shortestPath = new LinkedList<>();
        shortestPath.add(temp);
        while (temp != source) {
            shortestPath.add(0, whoseYourDaddy.get(temp));
            temp = whoseYourDaddy.get(temp);
        }

        return shortestPath;
    }
}