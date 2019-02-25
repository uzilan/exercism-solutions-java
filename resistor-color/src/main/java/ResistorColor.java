import static java.util.Arrays.stream;

class ResistorColor {

   private enum Colors {
      black, brown, red, orange, yellow, green, blue, violet, grey, white
   }

   int colorCode(String color) {
      return Colors.valueOf(color).ordinal();
   }

   String[] colors() {
      return stream(Colors.values())
         .map(Enum::name)
         .toArray(String[]::new);
   }
}
