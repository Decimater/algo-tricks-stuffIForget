public class Vertex {
    private int id;

    public Vertex (int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return Integer.toString(this.getId());
    }
}
