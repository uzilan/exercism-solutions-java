import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

   private String acronym;

   Acronym(String phrase) {
      this.acronym = Arrays.stream(phrase.split("[ -]"))
         .map(s -> s.substring(0, 1))
         .collect(Collectors.joining())
         .toUpperCase();
   }

   String get() {
      return acronym;
   }
}
