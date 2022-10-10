import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ObjectNode extends Node implements Iterable<String> {
    private HashMap<String, Node> nodes;
    private ArrayList<String> keys = new ArrayList<>();

    @Override
    public Iterator<String> iterator() {
        Iterator<String> iter = new Iterator<>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < keys.size() && keys.get(count) != null;
            }

            @Override
            public String next() {
                return keys.get(count++);
            }
        };
        return iter;
    }

    public ObjectNode() {
        this.nodes = new HashMap<>();
    }

    public Node get(String key) {
        return nodes.get(key);
    }

    public void set(String key, Node node) {
        nodes.put(key, node);
        keys.add(key);
        Collections.sort(keys);
    }

    public int size() {
        return nodes.size();
    }

}
