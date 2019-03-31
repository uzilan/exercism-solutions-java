import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        final List<String> noHyphens = removeHyphens(stringToVerify);

        if (!checkCharacters(noHyphens)) {
            return false;
        }

        final String last = noHyphens.get(9);
        if (!checkLastChar(last)) {
            return false;
        }

        final List<Integer> integers = convertToIntegers(noHyphens, last);
        return calculateSum(integers) % 11 == 0;
    }

    private List<String> removeHyphens(String stringToVerify) {
        return stringToVerify.replaceAll("-", "")
                .codePoints()
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.toList());
    }

    private boolean checkCharacters(List<String> noHyphens) {
        return noHyphens.size() == 10 &&
                noHyphens.subList(0, 9).stream().noneMatch(i -> i.matches("\\D"));
    }

    private boolean checkLastChar(String last) {
        return last.matches("\\d") || last.equals("X");
    }

    private List<Integer> convertToIntegers(List<String> noHyphens, String last) {
        final List<Integer> integers = noHyphens.subList(0, 9).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        integers.add(last.equals("X") ? 10 : Integer.parseInt(last));
        return integers;
    }

    private int calculateSum(List<Integer> integers) {
        return IntStream.rangeClosed(1, 10)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> integers.get(i - 1) * i)
                .sum();
    }
}
