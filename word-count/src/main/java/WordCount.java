import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.Map;

public class WordCount {

   public Map<String, Integer> phrase(String phrase) {

      final String cleaned = phrase
         .replaceAll("[,.:!&@$%\\^]", " ")
         .replaceAll(" '|' ", " ")
         .replaceAll("\\s+", " ")
         .trim()
         .toLowerCase();

      final String[] split = cleaned.split(" ");

      return stream(split)
         .collect(groupingBy(identity(), summingInt(x -> 1)));
   }
}
