public class Rectangle {
    private Point first_coordinate, second_coordinate;

    public void setSecond_coordinate(Point second_coordinate) {
        this.second_coordinate = second_coordinate;
    }

    public void setFirst_coordinate(Point first_coordinate) {
        this.first_coordinate = first_coordinate;
    }

    public Point getSecond_coordinate() {
        return second_coordinate;
    }

    public Point getFirst_coordinate() {
        return first_coordinate;
    }


    public Rectangle() {
        this(new Point(), new Point());
    }


    public Rectangle(Point first_coordinate, Point second_coordinate) {
        this.first_coordinate = first_coordinate;
        this.second_coordinate = second_coordinate;
        if (first_coordinate.getX() > second_coordinate.getX() || (first_coordinate.getX() == second_coordinate.getX() & first_coordinate.getY() > second_coordinate.getY())) {
            this.first_coordinate = second_coordinate;
            this.second_coordinate = first_coordinate;
        }
    }
}
