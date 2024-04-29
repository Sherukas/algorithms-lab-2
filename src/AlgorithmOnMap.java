public class AlgorithmOnMap {
    static public CompressedCoordinateMap data_preparation(Rectangle[] rectangles) {
        CompressedCoordinateMap map = new CompressedCoordinateMap(rectangles);
        map.fillMap(rectangles);
        return map;
    }

    static public int[] query(Point[] points, CompressedCoordinateMap map) {
        int[] result = new int[points.length];
        for (int i = 0; i < points.length; ++i) {
            int X = CompressedCoordinateMap.binarySearch(map.getCompressedX(), points[i].getX());
            int Y = CompressedCoordinateMap.binarySearch(map.getCompressedY(), points[i].getY());
            if (X == -1 || Y == -1) {
                result[i] = 0;
            } else {
                result[i] = map.getMap()[X][Y];
            }
        }
        return result;
    }

    static public int[] algorithm(Rectangle[] rectangles, Point[] points) {
        CompressedCoordinateMap map = AlgorithmOnMap.data_preparation(rectangles);
        return query(points, map);
    }
}
