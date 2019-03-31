import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LargestSeriesProductCalculator {

    private List<Integer> numbers;

    LargestSeriesProductCalculator(String inputNumber) {
        if (!inputNumber.matches("\\d*")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }

        this.numbers = inputNumber.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits > numbers.size()) {
            throw new IllegalArgumentException(
                    "Series length must be less than or equal to the length of the string to search.");
        }

        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }

        final int noOfSeries = numbers.size() - numberOfDigits + 1;

        return IntStream.range(0, noOfSeries)
                .map(calculateSerieProduct(numberOfDigits))
                .max()
                .orElse(0);
    }

    private IntUnaryOperator calculateSerieProduct(int numberOfDigits) {
        return index -> numbers.subList(index, numberOfDigits + index).stream()
                .reduce((x, y) -> x * y)
                .orElse(1);
    }
}
