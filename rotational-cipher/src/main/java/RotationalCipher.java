import static java.lang.Character.isLetter;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

class RotationalCipher {

   private final String letters;
   private final String rotated;

   RotationalCipher(int shiftKey) {
      letters = rangeClosed('a', 'z')
         .mapToObj(cp -> "" + (char) cp)
         .collect(joining(""));

      rotated = letters.substring(shiftKey) + letters.substring(0, shiftKey);
   }

   String rotate(String data) {
      return data.codePoints()
         .mapToObj(c -> "" + getRotatedLetter(c))
         .collect(joining(""));
   }

   private char getRotatedLetter(int codePoint) {
      final char ch = (char) codePoint;
      if (!isLetter(ch)) {
         return ch;
      }

      final int i = letters.indexOf(toLowerCase(ch));
      final char c = rotated.charAt(i);

      return isLowerCase(ch) ? c : toUpperCase(c);
   }
}
