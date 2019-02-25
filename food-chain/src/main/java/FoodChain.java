import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoodChain {

   private static List<String> lyrics =
      Arrays.asList(
         "fly#I don't know why she swallowed the fly. Perhaps she'll die.",
         "spider#It wriggled and jiggled and tickled inside her.",
         "bird#How absurd to swallow a bird!",
         "cat#Imagine that, to swallow a cat!",
         "dog#What a hog, to swallow a dog!",
         "goat#Just opened her throat and swallowed a goat!",
         "cow#I don't know how she swallowed a cow!",
         "horse#She's dead, of course!");

   public String verse(int verse) {
      final String[] row = lyrics.get(verse - 1).split("#");

      final StringBuffer sb = new StringBuffer(
         String.format("I know an old lady who swallowed a %s.\n%s\n", row[0], row[1]));

      if (verse < lyrics.size()) {
         helper(verse - 1, sb, row[0]);
      }

      return sb.toString().trim();
   }

   public String verses(int startVerse, int endVerse) {
      return IntStream.rangeClosed(startVerse, endVerse)
         .mapToObj(this::verse)
         .collect(Collectors.joining("\n\n"));
   }

   private void helper(int verse, StringBuffer sb, String previous) {
      if (verse == 0) {
         return;
      }

      final String[] row = lyrics.get(verse - 1).split("#");
      sb.append(String.format("She swallowed the %s to catch the %s", previous, row[0]));

      switch (verse) {
         case 1:
            sb.append(".\n").append(row[1]);
            break;
         case 2:
            sb.append(row[1].replace("It", " that")).append("\n");
            break;
         default:
            sb.append(".\n");
      }

      helper(verse - 1, sb, row[0]);
   }
}
