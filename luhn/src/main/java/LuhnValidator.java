import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LuhnValidator {

    boolean isValid(String candidate) {
        final String noSpaces = candidate.replaceAll(" ", "");

        if (noSpaces.length() <= 1 || !noSpaces.matches("\\d+")) {
            return false;
        }

        final List<Integer> numbers = noSpaces
                .codePoints()
                .mapToObj(c -> Integer.parseInt("" + (char) c))
                .collect(Collectors.toList());

        final IntStream doubled = IntStream.range(0, numbers.size())
                .map(index -> doubleOrGet(index, numbers.get(numbers.size() - index - 1)));

        return doubled.sum() % 10 == 0;
    }

    private int doubleOrGet(int index, Integer number) {
        if ((index + 1) % 2 == 0) {
            int doub = number * 2;
            return doub > 9 ? doub - 9 : doub;
        }
        return number;
    }
}
