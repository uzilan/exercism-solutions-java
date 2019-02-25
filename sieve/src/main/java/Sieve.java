import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Sieve {

   private final int maxPrime;
   private final List<MaybePrime> maybePrimes;
   private final List<Integer> primes;

   Sieve(int maxPrime) {
      this.maxPrime = maxPrime;
      this.maybePrimes = createListWithMaybePrimes();
      markAllPrimeMultiplesNonPrime();

      primes = maybePrimes.stream()
         .filter(mp -> mp.isPrime)
         .map(mp -> mp.number)
         .collect(Collectors.toList());
   }

   List<Integer> getPrimes() {
      return primes;
   }

   private List<MaybePrime> createListWithMaybePrimes() {
      return IntStream.rangeClosed(2, maxPrime)
         .boxed()
         .map(MaybePrime::new)
         .collect(Collectors.toList());
   }

   private void markAllPrimeMultiplesNonPrime() {
      maybePrimes.forEach(maybePrime -> {
         if (maybePrime.isPrime) {
            IntStream.rangeClosed(maybePrime.number + 1, maxPrime)
               .filter(i -> i % maybePrime.number == 0)
               .forEach(this::markNonPrime);
         }
      });
   }

   private void markNonPrime(Integer multiple) {
      maybePrimes.stream()
         .filter(i -> i.number == multiple)
         .findFirst()
         .map(mp -> mp.isPrime = false);
   }

   class MaybePrime {

      private int number;
      private boolean isPrime = true;

      MaybePrime(int number) {this.number = number;}
   }
}