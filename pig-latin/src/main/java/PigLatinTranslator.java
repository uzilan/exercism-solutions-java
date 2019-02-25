import java.util.Arrays;
import java.util.stream.Collectors;

public class PigLatinTranslator {

   public String translate(String string) {
      return Arrays.stream(string.split(" "))
         .map(s -> aLotOfRegexMagic(s) + "ay")
         .collect(Collectors.joining(" "));
   }

   private String aLotOfRegexMagic(String s) {
      if (s.matches("^([aeiou]|yt|xr).*")) {
         return s;
      }
      if (s.matches("^(thr|sch|[bcdfghjklmnpqrstvwxyz]qu).*")) {
         return rotate(s, 3);
      }
      if (s.matches("^(ch|qu|th|([bcdfghjklmnpqrstvwxyz]){2,}y).*")) {
         return rotate(s, 2);
      }
      return rotate(s, 1);
   }

   private String rotate(String s, int i) {
      return s.substring(i) + s.substring(0, i);
   }
}
