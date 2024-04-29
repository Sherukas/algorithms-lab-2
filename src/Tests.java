import java.io.FileWriter;
import java.io.IOException;

public class Tests {
    static private long prime_n1 = 10104569;
    static private long prime_n2 = 10150697;

    public static void setPrime_n2(long prime_n2) {
        Tests.prime_n2 = prime_n2;
    }

    public static void setPrime_n1(long prime_n1) {
        Tests.prime_n1 = prime_n1;
    }

    public static long getPrime_n2() {
        return prime_n2;
    }

    public static long getPrime_n1() {
        return prime_n1;
    }

    public static long powerMod(long base, long exponent, long modulus) {
        if (modulus == 1) return 0;

        long result = 1;
        base = base % modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent = exponent / 2;
        }
        return result;
    }

    public static Rectangle[] GenerateTestRectangles(int n) {
        Rectangle[] rectangles = new Rectangle[n];
        for (int i = 0; i < n; ++i) {
            rectangles[i] = new Rectangle(new Point(10 * i, 10 * i), new Point(10 * (2 * n - i), 10 * (2 * n - i)));
        }
        return rectangles;
    }

    public static Point[] GenerateTestPoints(int n, int m) {
        Point[] points = new Point[m];
        for (int i = 0; i < m; ++i) {
            points[i] = new Point(powerMod(prime_n1 * i, 31, 20 * n), powerMod(prime_n2 * i, 31, 20 * n));
        }
        return points;
    }

    public static void Test() {
        String filename = "results.txt";
        try {
            FileWriter writer = new FileWriter(filename);
            int n = 1, m = 1;
            int max_rect = 12, max_point = 12;
            for (int rect = 0; rect <= max_rect; ++rect) {
                Rectangle[] rectangles = GenerateTestRectangles(n);
                writer.write("Результат для " + n + " прямоугольников (BruteForceAlgorithm, AlgorithmOnMap, AlgorithmOnTree):\n");
                writer.write("Подготовка данных:\n");

                long BruteForceAlgorithmTimePreparation = 0;


                long startTime = System.nanoTime();
                CompressedCoordinateMap map = AlgorithmOnMap.data_preparation(rectangles);
                long endTime = System.nanoTime();
                long AlgorithmOnMapTimePreparation = endTime - startTime;


                startTime = System.nanoTime();
                Object[] objects = AlgorithmOnTree.data_preparation(rectangles);
                endTime = System.nanoTime();
                long AlgorithmOnTreeTimePreparation = endTime - startTime;
                writer.write(BruteForceAlgorithmTimePreparation + " " + AlgorithmOnMapTimePreparation + " " + AlgorithmOnTreeTimePreparation + " ns\n");
                writer.write("Запросы:\n");

                for (int point = 0; point <= max_point; ++point) {
                    Point[] points = GenerateTestPoints(n, m);


                    startTime = System.nanoTime();
                    int[] result = BruteForceAlgorithm.query(rectangles, points);
                    endTime = System.nanoTime();
                    long BruteForceAlgorithmTime = endTime - startTime;


                    startTime = System.nanoTime();
                    int[] result1 = AlgorithmOnMap.query(points, map);
                    endTime = System.nanoTime();
                    long AlgorithmOnMapTime = endTime - startTime;


                    startTime = System.nanoTime();
                    double[] result2 = AlgorithmOnTree.query(points, (CompressedCoordinateMap) objects[0], (PersistentSegmentTree) objects[1]);
                    endTime = System.nanoTime();
                    long AlgorithmOnTreeTime = endTime - startTime;
                    writer.write(m + "т " + BruteForceAlgorithmTime + " " + AlgorithmOnMapTime + " " + AlgorithmOnTreeTime + " ns\n");

                    m *= 2;
                }
                writer.write("\n\n\n");
                m = 1;
                n *= 2;
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл " + filename);
            e.printStackTrace();
        }
    }
}
