import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Etl {

   public Map<String, Integer> transform(Map<Integer, List<String>> old) {
      return old.entrySet().stream()
         .flatMap(entry -> entry.getValue().stream()
            .collect(toMap(
               String::toLowerCase,
               s -> entry.getKey()))
            .entrySet().stream())
         .collect(toMap(Entry::getKey, Entry::getValue));
   }
}