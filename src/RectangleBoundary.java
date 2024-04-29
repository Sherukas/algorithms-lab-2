public class RectangleBoundary {
    private int startY;
    private int endY;
    private int X;
    private int condition;

    public RectangleBoundary() {
        this(0, 0, 0, 1);
    }

    public RectangleBoundary(int startY, int endY, int X, int condition) {
        this.startY = startY;
        this.endY = endY;
        this.X = X;
        this.condition = condition;
    }

    public void setX(int x) {
        X = x;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getCondition() {
        return condition;
    }

    public int getX() {
        return X;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }


    static public int compare(RectangleBoundary rect1, RectangleBoundary rect2) {
        return Double.compare(rect1.getX(), rect2.getX());
    }
}
