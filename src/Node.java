public class Node {
    private int start;
    private int end;
    private double value;
    private Node left;

    private Node right;


    public void setValue(double value) {
        this.value = value;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getValue() {
        return value;
    }

    public int getStart() {
        return start;
    }

    public Node getRight() {
        return right;
    }

    public int getEnd() {
        return end;
    }

    public Node getLeft() {
        return left;
    }

    Node() {
        this(0, 0, 0, null, null);
    }

    Node(Node node) {
        this(node.start, node.end, node.value, node.left, node.right);
    }

    Node(int start, int end, double value, Node left, Node right) {
        this.start = start;
        this.end = end;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
