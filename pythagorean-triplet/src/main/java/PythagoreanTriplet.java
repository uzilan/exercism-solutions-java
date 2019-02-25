import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PythagoreanTriplet {

   private final List<Integer> sides;

   public PythagoreanTriplet(int a, int b, int c) {
      sides = Stream.of(a, b, c).sorted().collect(toList());
   }

   public int calculateSum() {
      return sides.stream().mapToInt(i -> i).sum();
   }

   public long calculateProduct() {
      return sides.stream().reduce(1, (x, y) -> x * y);
   }

   public boolean isPythagorean() {
      return square(sides.get(0)) + square(sides.get(1)) == square(sides.get(2));
   }

   private int square(int number) {
      return (int) Math.pow((double) number, 2);
   }

   public static Builder makeTripletsList() {
      return new Builder();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      return Objects.equals(sides, ((PythagoreanTriplet) o).sides);
   }

   @Override
   public int hashCode() {
      return Objects.hash(sides);
   }

   static class Builder {

      private int minFactor = 2;
      private int maxFactor = Integer.MAX_VALUE;
      private long expectedSum;

      public Builder withFactorsGreaterThanOrEqualTo(int minFactor) {
         this.minFactor = minFactor;
         return this;
      }

      public Builder withFactorsLessThanOrEqualTo(int maxFactor) {
         this.maxFactor = maxFactor;
         return this;
      }

      public Builder thatSumTo(long expectedSum) {
         this.expectedSum = expectedSum;
         return this;
      }

      public List<PythagoreanTriplet> build() {
         final ArrayList<PythagoreanTriplet> triplets = new ArrayList<>();
         for (int a = minFactor; a <= maxFactor; a++) {
            for (int b = minFactor; b < a; b++) {
               for (int c = minFactor; c < b; c++) {
                  final PythagoreanTriplet triplet = new PythagoreanTriplet(a, b, c);
                  if (triplet.isPythagorean() && !triplets.contains(triplet)) {
                     if (expectedSum == 0 || expectedSum == triplet.calculateSum()) {
                        triplets.add(triplet);
                     }
                  }
               }
            }
         }
         return triplets;
      }
   }
}
