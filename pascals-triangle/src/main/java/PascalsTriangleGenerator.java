import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PascalsTriangleGenerator {

    private List<List<Integer>> triangle;

    public int[][] generateTriangle(int rows) {
        if (rows < 0) {
            throw new IllegalArgumentException("Number of rows may not be negative");
        }

        createTheTriangel(rows);
        manipulateTriangleValues(rows);
        return getTheTriangleAsDoubleArray();
    }

    private void createTheTriangel(int rows) {
        triangle = IntStream.range(0, rows)
                .mapToObj(row -> IntStream.rangeClosed(0, row)
                        .map(col -> 1)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private void manipulateTriangleValues(int rows) {
        IntStream
                .range(0, rows)
                .forEach(row -> IntStream
                        .range(0, row)
                        .forEach(col -> recalculateCellValue(row, col)));
    }

    private int[][] getTheTriangleAsDoubleArray() {
        return triangle.stream()
                .map(row -> row.stream().mapToInt(i -> i).toArray())
                .toArray(int[][]::new);
    }

    private void recalculateCellValue(int r, int c) {
        triangle.get(r).set(c, addParentsValues(r, c));
    }

    private int addParentsValues(int row, int col) {
        if (row == 0 && col == 0) {
            return 1;
        }
        return getAt(row - 1, col - 1) + getAt(row - 1, col);
    }

    private int getAt(int row, int col) {
        if (row < 0 || col < 0 || col >= triangle.get(row).size()) {
            return 0;
        }
        return triangle.get(row).get(col);
    }
}
