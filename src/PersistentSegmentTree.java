import java.util.ArrayList;

public class PersistentSegmentTree {
    private int version;

    private ArrayList<Node> versions;

    public void setVersions(ArrayList<Node> versions) {
        this.versions = versions;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ArrayList<Node> getVersions() {
        return versions;
    }

    public int getVersion() {
        return version;
    }

    PersistentSegmentTree() {
        this.version = 0;
        this.versions = new ArrayList<>();
    }

    PersistentSegmentTree(double[] arr) {
        this();
        this.versions.add(build(arr, 0, arr.length - 1));
    }


    public Node build(double[] arr, int l, int r) {
        if (l > r) return null;
        if (l == r) {
            return new Node(l, r, arr[l], null, null);
        }
        int m = (l + r) / 2;
        Node left = build(arr, l, m);
        Node right = build(arr, m + 1, r);
        return new Node(l, r, Math.max(left.getValue(), right.getValue()), left, right);
    }


    public double max(int l, int r, Node node) {
        if (node.getStart() >= r || node.getEnd() <= l) return 0;
        if (node.getStart() >= l & node.getEnd() <= r) return node.getValue();
        return Math.max(max(l, r, node.getLeft()), max(l, r, node.getRight()));
    }

    public Node update(int l, int r, int x, Node node) {
        if (node.getStart() > r || node.getEnd() < l) return node;
        if (node.getLeft() == null || node.getRight() == null) {
            Node node_new = new Node(node);
            node_new.setValue(node_new.getValue() + x);
            return node_new;
        }
        if (node.getStart() >= l & node.getEnd() <= r) {
            Node node_new = new Node(node);
            node_new.setLeft(update(l, r, x, node.getLeft()));
            node_new.setRight(update(l, r, x, node.getRight()));
            node_new.setValue(Math.max(node.getLeft().getValue(), node.getRight().getValue()));
            return node_new;
        }
        Node node_new = new Node(node);
        node_new.setLeft(update(l, r, x, node.getLeft()));
        node_new.setRight(update(l, r, x, node.getRight()));
        node_new.setValue(Math.max(node_new.getLeft().getValue(), node_new.getRight().getValue()));
        return node_new;
    }

    public double queryAtIndex(int version, int index) {
        Node root = this.versions.get(version);
        return queryHelper(root, index);
    }

    private double queryHelper(Node root, int index) {
        if (root.getStart() == root.getEnd() && root.getStart() == index) {
            return root.getValue();
        }
        if (index <= (root.getStart() + root.getEnd()) / 2) {
            return queryHelper(root.getLeft(), index);
        } else {
            return queryHelper(root.getRight(), index);
        }
    }
}
