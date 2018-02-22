import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {
    Set<Edge> edges;
    Set<Vertex> vertices;

    /**
     * @throws IllegalArgumentException if any argument is null
     * @param edges
     */
    public Graph(Set<Edge> edges) {
        if (edges == null) throw new IllegalArgumentException();

        this.edges = new HashSet<>(edges);
        this.vertices = edges.stream().flatMap(e -> Stream.of(e.getV1(), e.getV2())).collect(Collectors.toSet());
    }

    /**
     * @return Unmodifiable list
     */
    public List<Edge> getEdges() {
        return Collections.unmodifiableList(new LinkedList<>(edges));
    }

    /**
     * @return Unmodifiable list
     */
    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(new LinkedList<>(vertices));
    }

    public List<Vertex> connectedVertices(Vertex vertex) {
        return this.getEdges().stream()
                .filter(e -> e.getV1() == vertex || e.getV2() == vertex)
                .map(e -> e.getV1() == vertex ? e.getV2() : e.getV1())
                .collect(Collectors.toList());
    }

    public List<Edge> edgesContainingVertex(Vertex vertex) {
        return this.getEdges().stream()
                .filter(e -> e.getV1() == vertex || e.getV2() == vertex)
                .collect(Collectors.toList());
    }
}