import static java.lang.Math.pow;
import static java.util.stream.IntStream.rangeClosed;

class DifferenceOfSquaresCalculator {

   int computeSquareOfSumTo(int input) {
      return square(rangeClosed(1, input).sum());
   }

   int computeSumOfSquaresTo(int input) {
      return rangeClosed(1, input).map(this::square).sum();
   }

   int computeDifferenceOfSquares(int input) {
      return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
   }

   private int square(int input) {
      return (int) pow(input, 2);
   }
}
