import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinesweeperBoard {

   private List<String> inputBoard;

   public MinesweeperBoard(List<String> inputBoard) {
      this.inputBoard = inputBoard;
   }

   public List<String> withNumbers() {
      return IntStream
         .range(0, inputBoard.size())
         .mapToObj(row -> IntStream
            .range(0, inputBoard.get(row).length())
            .mapToObj(col -> findCellContent(row, col))
            .collect(Collectors.joining())
         ).collect(Collectors.toList());
   }

   private String findCellContent(int row, int col) {
      if (isThereABombAt(row, col)) {
         return "*";
      }

      final long bombCount = lookAround(row, col);
      return bombCount == 0 ? " " : Long.toString(bombCount);
   }

   private long lookAround(int row, int col) {
      return IntStream
         .rangeClosed(row - 1, row + 1)
         .flatMap(r ->
                     IntStream
                        .rangeClosed(col - 1, col + 1)
                        .map(c -> lookAtNeighbourCell(row, col, r, c))
         ).sum();
   }

   private int lookAtNeighbourCell(int row, int col, int neighbourRow, int neighbourCol) {
      if (neighbourRow == row && neighbourCol == col) {
         return 0;
      }
      return isThereABombAt(neighbourRow, neighbourCol) ? 1 : 0;
   }

   private boolean isThereABombAt(int row, int col) {
      if (row < 0 || row >= inputBoard.size() || col < 0 || col >= inputBoard.get(0).length()) {
         return false;
      }
      return inputBoard.get(row).charAt(col) == '*';
   }
}
