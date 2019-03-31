import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Series {

    private List<Integer> series;

    public Series(String series) {
        this.series = series
                .codePoints()
                .map(Character::getNumericValue)
                .boxed()
                .collect(toList());
    }

    public List<Integer> getDigits() {
        return series;
    }

    public List<List<Integer>> slices(int number) {
        if (number > series.size()) {
            throw new IllegalArgumentException("Slice is bigger than series!");
        }
        return IntStream
                .rangeClosed(0, series.size() - number)
                .mapToObj(i -> series.subList(i, i + number))
                .collect(Collectors.toList());
    }
}
