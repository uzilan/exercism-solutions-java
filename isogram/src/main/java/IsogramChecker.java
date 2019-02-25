import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class IsogramChecker {

   boolean isIsogram(String phrase) {
      final Map<Character, Long> map = phrase
         .codePoints()
         .mapToObj(c -> Character.toLowerCase((char) c))
         .filter(Character::isLetter)
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      return map.values().stream().noneMatch(i -> i > 1);
   }
}
