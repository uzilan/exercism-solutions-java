import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

class DiamondPrinter {

    List<String> printToList(char ch) {
        final List<String> strings = rangeClosed('A', ch)
                .mapToObj(i -> {
                    final String outerSpace = spaceTimesX(ch - i);
                    if (i == 'A') {
                        return format("%sA%s", outerSpace, outerSpace);
                    }
                    final char c = ((char) i);
                    final String innerSpace = spaceTimesX(2 * (i - 'A') - 1);
                    return format("%s%c%s%c%s", outerSpace, c, innerSpace, c, outerSpace);
                })
                .collect(toList());

        final List<String> copied = new ArrayList<>(strings);
        final List<String> reversed = copied.subList(0, copied.size() - 1)
                .stream()
                .sorted(reverseOrder())
                .collect(toList());
        strings.addAll(reversed);
        return strings;

    }

    private String spaceTimesX(int count) {
        return range(0, count)
                .mapToObj(i -> " ")
                .collect(joining(""));
    }
}
