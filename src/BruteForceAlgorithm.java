public class BruteForceAlgorithm {
    static private boolean isPointInsideRectangle(Point point, Rectangle rectangle) {
        double x = point.getX();
        double y = point.getY();

        double rectLeftX = rectangle.getFirst_coordinate().getX();
        double rectLowerY = rectangle.getFirst_coordinate().getY();
        double rectRightX = rectangle.getSecond_coordinate().getX();
        double rectUpperY = rectangle.getSecond_coordinate().getY();

        return x >= rectLeftX && x < rectRightX && y >= rectLowerY && y < rectUpperY;
    }

    static public int[] query(Rectangle[] rectangles, Point[] points) {
        int[] result = new int[points.length];
        for (int i = 0; i < points.length; ++i) {
            int counter = 0;
            for (Rectangle rectangle : rectangles) {
                if (isPointInsideRectangle(points[i], rectangle)) {
                    ++counter;
                }
            }
            result[i] = counter;
        }
        return result;
    }

    static public int[] algorithm(Rectangle[] rectangles, Point[] points) {
        return query(rectangles, points);
    }
}
