import java.util.*;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToIntFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.rangeClosed;

public class PalindromeCalculator {

    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(format("invalid input: min is %d and max is %d", min, max));
        }

        final var palindromes =
                findProducts(min, max).stream()
                        .filter(PalindromeCalculator::isPalindrome)
                        .collect(toList());

        if (palindromes.isEmpty()) {
            throw new NoSuchElementException(format("no palindrome with factors in the range %d to %d", min, max));
        }

        final var minPalindrome = palindromes.get(0);
        final var maxPalindrome = palindromes.get(palindromes.size() - 1);

        return new TreeMap<>(
                Map.of(minPalindrome, findFactors(minPalindrome, min, max),
                        maxPalindrome, findFactors(maxPalindrome, min, max)));
    }

    private List<Long> findProducts(long from, long to) {
        final LongFunction<LongStream> toProducts =
                x -> rangeClosed(from, to)
                        .map(y -> x * y);

        return rangeClosed(from, to)
                .flatMap(toProducts)
                .distinct()
                .sorted()
                .boxed()
                .collect(toList());
    }

    private static boolean isPalindrome(long x) {
        final var ltr = Long.toString(x);
        final var rtl = new StringBuilder(ltr).reverse().toString();
        return ltr.equals(rtl);
    }

    private List<List<Integer>> findFactors(long number, int from, int to) {
        final LongPredicate divisibleByNumber = i -> number % i == 0;
        final LongPredicate inRange = i -> number / i >= from && number / i <= to;
        final LongToIntFunction toInt = i -> (int) i;
        final Function<Integer, List<Integer>> toFactorList =
                i -> Stream.of(i, (int) number / i)
                        .sorted()
                        .collect(toList());

        return rangeClosed(from, to)
                .filter(divisibleByNumber)
                .filter(inRange)
                .mapToInt(toInt)
                .boxed()
                .map(toFactorList)
                .distinct()
                .collect(toList());
    }
}
