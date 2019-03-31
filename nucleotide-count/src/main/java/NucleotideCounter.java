import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class NucleotideCounter {

    private Map<Character, Integer> map = new HashMap<>();

    public NucleotideCounter(String dna) {
        if (!dna.matches("^[ACGT]*$")) {
            throw new IllegalArgumentException("Alien DNA is not allowed!");
        }

        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);

        map.putAll(
                dna.codePoints()
                        .mapToObj(i -> (char) i)
                        .collect(groupingBy(identity(), counting())));
    }

    public Map<Character, Integer> nucleotideCounts() {
        return map;
    }

    // adopted from Collectors.counting(), changed to count Integers instead of Longs
    private Collector<Character, ?, Integer> counting() {
        return reducing(0, e -> 1, Integer::sum);
    }
}



