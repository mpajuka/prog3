import java.util.ArrayList;
import java.util.Iterator;

public class ArrayNode extends Node implements Iterable<Node> {
    private ArrayList<Node> nodes;
    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> iter = new Iterator<>() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < nodes.size() && nodes.get(count) != null;
            }

            @Override
            public Node next() {
                return nodes.get(count++);
            }

        };
        return iter;
    }

    public ArrayNode() {
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public int size() {
        return nodes.size();
    }
}
