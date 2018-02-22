public class Edge {
    private Vertex v1;
    private Vertex v2;
    private final int WEIGHT;

    /***
     * @throws IllegalArgumentException if either vertices are nulls
     * @param v1
     * @param v2
     */
    public Edge(Vertex v1, Vertex v2, int weight) {
        if (v1 == null || v2 == null) throw new IllegalArgumentException("Edge constructor will not accept nulls");

        this.WEIGHT = weight;
        this.v1 = v1;
        this.v2 = v2;
    }

    /***
     * Unweighted edge, weight is defaulted to zero
     * @param v1
     * @param v2
     */
    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, 0);
    }

    public Vertex getV1() {
        return this.v1;
    }

    public Vertex getV2() {
        return this.v2;
    }

    public int getWeight() {
        return this.WEIGHT;
    }
}
