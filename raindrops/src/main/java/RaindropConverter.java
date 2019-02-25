import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RaindropConverter {

   String convert(int number) {

      // Supplier is used here because count() is a collector and the stream cannot
      // otherwise be reused by mapToObj().
      // See http://www.baeldung.com/java-stream-operated-upon-or-closed-exception
      final Supplier<IntStream> factors = () -> IntStream.of(3, 5, 7)
         .filter(factor -> number % factor == 0);

      if (factors.get().count() == 0) {
         return Integer.toString(number);
      }

      return factors.get().mapToObj(factor -> {
         switch (factor) {
            case 3:
               return "Pling";
            case 5:
               return "Plang";
            case 7:
               return "Plong";
            default:
               return "";
         }
      }).collect(Collectors.joining());
   }
}
