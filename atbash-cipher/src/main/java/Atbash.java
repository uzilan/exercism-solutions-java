import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.rangeClosed;

public class Atbash {

    private final List<Integer> plain = rangeClosed('a', 'z')
            .boxed()
            .collect(toList());

    private final List<Integer> cypher = rangeClosed('a', 'z')
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(toList());

    public String encode(String text) {
        final AtomicInteger counter = new AtomicInteger(0);

        final Collection<List<Integer>> chunked = replaceChars(text)
                .boxed()
                .collect(groupingBy(it -> counter.getAndIncrement() / 5)) // split the code points into chunks of 5
                .values();

        return chunked.stream()
                .map(l -> l.stream()
                        .map(i -> "" + (char) (int) i)
                        .collect(joining(""))) // join every chunk into a string
                .collect(Collectors.joining(" ")); // join everything together
    }

    public String decode(String text) {
        return replaceChars(text)
                .mapToObj(c -> "" + (char) c)
                .collect(joining(""));
    }

    private IntStream replaceChars(String text) {
        return text
                .replaceAll("\\W", "")
                .toLowerCase()
                .codePoints()
                .map(this::getCypher);
    }

    private int getCypher(Integer c) {
        if (Character.isDigit(c)) {
            return c;
        } else if (plain.contains(c)) {
            return cypher.get(plain.indexOf(c));
        }
        return (int) ' ';
    }
}
