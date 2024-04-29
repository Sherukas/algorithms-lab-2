import java.util.TreeSet;

public class CompressedCoordinateMap {
    private Double[] compressedX, compressedY;
    private int[][] map;

    public CompressedCoordinateMap(Rectangle[] rectangles) {
        this.createMapCoord(rectangles);
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void setCompressedY(Double[] compressedY) {
        this.compressedY = compressedY;
    }

    public void setCompressedX(Double[] compressedX) {
        this.compressedX = compressedX;
    }

    public int[][] getMap() {
        return map;
    }

    public Double[] getCompressedY() {
        return compressedY;
    }

    public Double[] getCompressedX() {
        return compressedX;
    }

    public void fillMap(Rectangle[] rectangles) {
        this.map = new int[compressedX.length][compressedY.length];
        for (Rectangle rect : rectangles) {
            int X1 = binarySearch(this.compressedX, rect.getFirst_coordinate().getX());
            int X2 = binarySearch(this.compressedX, rect.getSecond_coordinate().getX());
            int Y1 = binarySearch(this.compressedY, rect.getFirst_coordinate().getY());
            int Y2 = binarySearch(this.compressedY, rect.getSecond_coordinate().getY());


            for (int i = X1; i < X2; ++i) {
                for (int j = Y1; j < Y2; ++j) {
                    ++this.map[i][j];
                }
            }
        }
    }

    public void createMapCoord(Rectangle[] rectangles) {
        TreeSet<Double> comprX = new TreeSet<>();
        TreeSet<Double> comprY = new TreeSet<>();
        for (Rectangle rect : rectangles) {
            comprX.add(rect.getFirst_coordinate().getX());
            comprX.add(rect.getSecond_coordinate().getX());
            comprY.add(rect.getFirst_coordinate().getY());
            comprY.add(rect.getSecond_coordinate().getY());
        }
        this.compressedX = comprX.toArray(new Double[comprX.size()]);
        this.compressedY = comprY.toArray(new Double[comprY.size()]);
        this.map = null;
    }

    static public int binarySearch(Double[] arr, double val) {
        int l = 0, r = arr.length - 1, res = -1;
        if (arr.length > 0) {
            if (val > arr[arr.length - 1] || val < arr[0]) return -1;
        }
        while (l <= r) {
            int m = (l + r) / 2;
            if (val > arr[m]) {
                l = m + 1;
                res = m;
            } else if (val < arr[m]) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return res;
    }
}
