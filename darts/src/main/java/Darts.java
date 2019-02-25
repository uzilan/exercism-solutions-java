
class Darts {

   private final double radius;

   Darts(double x, double y) {
      radius = Math.sqrt(x * x + y * y);
   }

   int score() {
      if (radius > 10) {
         return 0;
      }
      if (radius > 5) {
         return 1;
      }
      if (radius > 1) {
         return 5;
      }
      return 10;
   }
}
