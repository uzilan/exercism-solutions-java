import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Alphametics {

   private final List<String> words;
   private final List<String> adds;
   private final String result;
   private final Map<Character, Integer> map = new LinkedHashMap<>();

   public Alphametics(String expression) throws UnsolvablePuzzleException {
      words = Stream.of(expression.split("\\+|=="))
         .map(String::trim)
         .collect(toList());

      adds = words.subList(0, words.size() - 1);
      result = words.get(words.size() - 1);

      final List<Character> letters = words.stream()
         .flatMap(w -> w.codePoints()
            .mapToObj(cp -> (char) cp))
         .distinct()
         .collect(Collectors.toList());

      if (!exhaustiveSolve(letters)) {
         throw new UnsolvablePuzzleException();
      }
   }

   public Map<Character, Integer> solve() {
      return map;
   }

   private boolean exhaustiveSolve(List<Character> lettersToAssign) {
      if (lettersToAssign.isEmpty()) {
         return isPuzzleSolved();
      }

      for (long digit = 9; digit >= 0; digit--) {
         if (assignLetterToDigit(lettersToAssign.get(0), digit)) {
            if (exhaustiveSolve(lettersToAssign.subList(1, lettersToAssign.size()))) {
               return true;
            }
            unassignLetterFromDigit(lettersToAssign.get(0));
         }
      }
      return false;
   }

   private boolean isPuzzleSolved() {
      long sum = adds.stream()
         .mapToLong(this::replaceWithNumbers)
         .sum();

      final long resultSum = replaceWithNumbers(result);

      return sum == resultSum && noWordBeginWithZero();
   }

   private boolean noWordBeginWithZero() {
      return words.stream().noneMatch(w -> map.get(w.charAt(0)) == 0);
   }

   private long replaceWithNumbers(String s) {
      final String collect = IntStream.range(0, s.length())
         .mapToObj(i -> "" + map.get(s.charAt(i)))
         .collect(joining());

      return Long.valueOf(collect);
   }

   private boolean assignLetterToDigit(char c, Long digit) {
      final int i = digit.intValue();
      if (map.containsKey(c) || map.containsValue(i)) {
         return false;
      }

      map.put(c, i);
      return true;
   }

   private void unassignLetterFromDigit(char c) {
      map.remove(c);
   }

}