import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.util.Random;

public class Robot {

   private String name;
   private final Random random = new Random();
   private final String atoz = rangeClosed('A', 'Z')
      .mapToObj(i -> "" + (char) i)
      .collect(joining(""));

   public Robot() {
      reset();
   }

   public String getName() {
      return name;
   }

   public void reset() {

      final String letters = range(0, 2)
         .mapToObj(i -> nextLetter())
         .collect(joining(""));

      final String numbers = format("%3d", random.nextInt(1000))
         .replaceAll(" ", "0");

      name = letters + numbers;
   }

   private String nextLetter() {
      final int index = random.nextInt(atoz.length());
      return atoz.substring(index, index + 1);
   }
}
