import java.util.stream.IntStream;

class Hamming {

   private final String leftStrand;
   private final String rightStrand;

   Hamming(String leftStrand, String rightStrand) {
      if (leftStrand.length() != rightStrand.length()) {
         throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
      }

      this.leftStrand = leftStrand;
      this.rightStrand = rightStrand;
   }

   long getHammingDistance() {
      return IntStream.range(0, leftStrand.length())
         .filter(i -> leftStrand.charAt(i) != rightStrand.charAt(i))
         .count();
   }
}
