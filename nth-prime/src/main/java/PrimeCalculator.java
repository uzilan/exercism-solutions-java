import static java.util.stream.IntStream.rangeClosed;

class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException();
        }

        return rangeClosed(2, Integer.MAX_VALUE)
                .filter(this::isPrime)
                .limit(nth)
                .max()
                .orElse(0);
    }

    private boolean isPrime(int number) {
        return rangeClosed(2, number / 2)
                .filter(i -> number % i == 0)
                .count() == 0;
    }
}
