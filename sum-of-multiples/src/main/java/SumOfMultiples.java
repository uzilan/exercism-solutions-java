import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class SumOfMultiples {

   private final int sum;

   SumOfMultiples(int number, int[] set) {
      sum = IntStream.range(1, number)
         .filter(hasMultiplies(set))
         .sum();
   }

   int getSum() {
      return sum;
   }

   private IntPredicate hasMultiplies(int[] set) {
      return number -> Arrays.stream(set).anyMatch(i -> number % i == 0);
   }
}
