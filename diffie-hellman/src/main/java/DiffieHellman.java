import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import static java.math.BigInteger.ZERO;
import static java.util.stream.Stream.iterate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class DiffieHellman {

   private Random random = new Random();

   public BigInteger privateKey(BigInteger p) {
      return getPrimeList(p).get(random.nextInt(getPrimeList(p).size()));
   }

   public BigInteger publicKey(BigInteger p, BigInteger g, BigInteger a) {
      return g.modPow(a, p);
   }

   public BigInteger secret(BigInteger p, BigInteger b, BigInteger a) {
      return b.modPow(a, p);
   }

   private List<BigInteger> getPrimeList(BigInteger max) {
      return iterate(TWO, bigInteger -> bigInteger.add(ONE))
         .takeWhile(current -> !current.equals(max))
         .reduce(new ArrayList<>(), accumulator, combiner);
   }

   private final BiFunction<ArrayList<BigInteger>, BigInteger, ArrayList<BigInteger>> accumulator =
      (acc, current) -> {
         if (isPrime(current, acc)) {
            acc.add(current);
         }
         return acc;
      };

   private final BinaryOperator<ArrayList<BigInteger>> combiner =
      (accumulator, bigIntegers) -> {
         accumulator.addAll(bigIntegers);
         return accumulator;
      };

   private boolean isPrime(BigInteger bigInteger, List<BigInteger> list) {
      return list.stream().noneMatch(current -> bigInteger.mod(current).equals(ZERO));
   }
}
