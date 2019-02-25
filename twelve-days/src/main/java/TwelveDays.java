import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

   String verse(int verseNumber) {
      final String day = days.get(verseNumber - 1);
      final StringBuilder sb = new StringBuilder("On the " + day + " day of Christmas my true love gave to me, ");

      IntStream.range(1, verseNumber)
         .boxed()
         .sorted(Comparator.reverseOrder())
         .forEach(i -> sb.append(gifts.get(i)));

      if (verseNumber > 1) {
         sb.append("and ");
      }

      sb.append(gifts.get(0)).append("\n");

      return sb.toString();
   }

   String verses(int startVerse, int endVerse) {
      return IntStream.rangeClosed(startVerse, endVerse)
         .mapToObj(this::verse)
         .collect(Collectors.joining("\n"));
   }

   String sing() {
      return verses(1, days.size());
   }

   private List<String> days = Arrays.asList(
      "first",
      "second",
      "third",
      "fourth",
      "fifth",
      "sixth",
      "seventh",
      "eighth",
      "ninth",
      "tenth",
      "eleventh",
      "twelfth");

   private List<String> gifts = Arrays.asList(
      "a Partridge in a Pear Tree.",
      "two Turtle Doves, ",
      "three French Hens, ",
      "four Calling Birds, ",
      "five Gold Rings, ",
      "six Geese-a-Laying, ",
      "seven Swans-a-Swimming, ",
      "eight Maids-a-Milking, ",
      "nine Ladies Dancing, ",
      "ten Lords-a-Leaping, ",
      "eleven Pipers Piping, ",
      "twelve Drummers Drumming, ");
}
