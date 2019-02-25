import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Matrix {

   private final List<List<Integer>> matrix;

   Matrix(String matrixAsString) {
      matrix = Arrays.stream(matrixAsString.split("\n"))
         .map(this::parseRow)
         .collect(Collectors.toList());
   }

   private List<Integer> parseRow(String row) {
      return Arrays.stream(row.split(" "))
         .mapToInt(Integer::parseInt)
         .boxed()
         .collect(Collectors.toList());
   }

   int[] getRow(int rowNumber) {
      return matrix.get(rowNumber)
         .stream()
         .mapToInt(i -> i)
         .toArray();
   }

   int[] getColumn(int columnNumber) {
      return matrix
         .stream()
         .mapToInt(r -> r.get(columnNumber))
         .toArray();
   }

   int getRowsCount() {
      return matrix.size();
   }

   int getColumnsCount() {
      return matrix.get(0).size();
   }
}
