import static java.util.Collections.emptyMap;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

class Matrix {

   private final Map<Integer, List<Integer>> rows;
   private final Map<Integer, List<Integer>> columns;

   Matrix(List<List<Integer>> values) {
      if (values.isEmpty()) {
         rows = emptyMap();
         columns = emptyMap();
      } else {
         rows = initRows(values);
         columns = initColumns(values);
      }
   }

   Set<MatrixCoordinate> getSaddlePoints() {
      return range(0, rows.size())
         .boxed()
         .flatMap(rowNumber -> {
            final List<Integer> row = rows.get(rowNumber);
            return range(0, row.size())
               .filter(columnNumber -> isSaddlePoint(row, columns.get(columnNumber), row.get(columnNumber)))
               .mapToObj(columnNumber -> new MatrixCoordinate(rowNumber, columnNumber));
         })
         .collect(toSet());

   }

   private Map<Integer, List<Integer>> initRows(List<List<Integer>> values) {
      return IntStream.range(0, values.size())
         .boxed()
         .collect(toMap(Function.identity(), values::get));
   }

   private Map<Integer, List<Integer>> initColumns(List<List<Integer>> values) {
      return IntStream.range(0, values.get(0).size())
         .boxed()
         .collect(toMap(Function.identity(),
                        colIndex -> values.stream()
                           .map(row -> row.get(colIndex))
                           .collect(toList())));
   }

   private boolean isSaddlePoint(List<Integer> row, List<Integer> column, int current) {
      return current == max(row) && current == min(column);
   }
}
