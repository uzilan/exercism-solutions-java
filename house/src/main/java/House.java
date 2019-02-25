import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {

   private final String lyrics =
      "This is the horse and the hound and the horn\n" +
         "that belonged to the farmer sowing his corn\n" +
         "that kept the rooster that crowed in the morn\n" +
         "that woke the priest all shaven and shorn\n" +
         "that married the man all tattered and torn\n" +
         "that kissed the maiden all forlorn\n" +
         "that milked the cow with the crumpled horn\n" +
         "that tossed the dog\n" +
         "that worried the cat\n" +
         "that killed the rat\n" +
         "that ate the malt\n" +
         "that lay in the house that Jack built.";

   private final List<String> rows = Arrays.asList(lyrics.split("\n"));

   public String verse(int verse) {
      final int end = rows.size();
      final int start = end - verse;

      return IntStream.range(start, end)
         .mapToObj(i -> {
            final String row = rows.get(i);
            if (i == start) {
               final int the = row.indexOf("the");
               return "This is " + row.substring(the) + " ";
            }
            return row + " ";
         })
         .collect(Collectors.joining()).trim();
   }

   public String verses(int startVerse, int endVerse) {
      return IntStream.rangeClosed(startVerse, endVerse)
         .mapToObj(this::verse)
         .collect(Collectors.joining("\n"));
   }

   public String sing() {
      return verses(1, rows.size());
   }
}
