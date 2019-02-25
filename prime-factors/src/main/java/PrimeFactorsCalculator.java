import static java.util.Collections.emptyList;
import static java.util.stream.LongStream.rangeClosed;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

public class PrimeFactorsCalculator {

   public List<Long> calculatePrimeFactorsOf(long number) {
      final OptionalLong lowestPrime = rangeClosed(2, number)
         .filter(i -> number % i == 0)
         .findFirst();

      if (lowestPrime.isPresent()) {
         final List<Long> longs = new ArrayList<>();
         longs.add(lowestPrime.getAsLong());
         longs.addAll(calculatePrimeFactorsOf(number / lowestPrime.getAsLong()));
         return longs;
      }
      return emptyList();
   }
}
