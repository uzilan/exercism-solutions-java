class CollatzCalculator {

   int computeStepCount(int start) {
      if (start < 1) {
         throw new IllegalArgumentException("Only natural numbers are allowed");
      }
      if (start == 1) {
         return 0;
      }
      if (start % 2 == 0) {
         return 1 + computeStepCount(start / 2);
      }
      return 1 + computeStepCount(start * 3 + 1);
   }
}
