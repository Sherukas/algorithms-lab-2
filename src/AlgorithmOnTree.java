import java.util.Arrays;

public class AlgorithmOnTree {
    static public Object[] data_preparation(Rectangle[] rectangles) {
        CompressedCoordinateMap map = new CompressedCoordinateMap(rectangles);

        RectangleBoundary[] boundary = new RectangleBoundary[2 * rectangles.length];
        for (int i = 0; i < rectangles.length; ++i) {
            int startY = CompressedCoordinateMap.binarySearch(map.getCompressedY(), rectangles[i].getFirst_coordinate().getY());
            int endY = CompressedCoordinateMap.binarySearch(map.getCompressedY(), rectangles[i].getSecond_coordinate().getY());
            int X1 = CompressedCoordinateMap.binarySearch(map.getCompressedX(), rectangles[i].getFirst_coordinate().getX());
            int X2 = CompressedCoordinateMap.binarySearch(map.getCompressedX(), rectangles[i].getSecond_coordinate().getX());

            boundary[2 * i] = new RectangleBoundary(startY, endY - 1, X1, 1);
            boundary[2 * i + 1] = new RectangleBoundary(startY, endY - 1, X2, -1);
        }
        Arrays.sort(boundary, (rect1, rect2) -> Integer.compare(rect1.getX(), rect2.getX()));

        PersistentSegmentTree tree = new PersistentSegmentTree(new double[map.getCompressedY().length]);

        int X = -2000000000;
        for (RectangleBoundary boun : boundary) {
            if (boun.getX() != X) {
                tree.getVersions().add(tree.update(boun.getStartY(), boun.getEndY(), boun.getCondition(), tree.getVersions().get(tree.getVersion())));
                tree.setVersion(tree.getVersion() + 1);
                X = boun.getX();
            } else {
                tree.getVersions().set(tree.getVersion(), tree.update(boun.getStartY(), boun.getEndY(), boun.getCondition(), tree.getVersions().get(tree.getVersion())));
            }
        }
        Object[] objects = new Object[2];
        objects[0] = map;
        objects[1] = tree;
        return objects;
    }

    static public double[] query(Point[] points, CompressedCoordinateMap map, PersistentSegmentTree tree) {
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; ++i) {
            int X_p = CompressedCoordinateMap.binarySearch(map.getCompressedX(), points[i].getX());
            int Y_p = CompressedCoordinateMap.binarySearch(map.getCompressedY(), points[i].getY());
            if (X_p == -1 || Y_p == -1) {
                result[i] = 0;
            } else {
                result[i] = tree.queryAtIndex(X_p + 1, Y_p);
            }
        }
        return result;
    }

    static public double[] algorithm(Rectangle[] rectangles, Point[] points) {
        Object[] objects = AlgorithmOnTree.data_preparation(rectangles);
        return AlgorithmOnTree.query(points, (CompressedCoordinateMap) objects[0], (PersistentSegmentTree) objects[1]);
    }
}
