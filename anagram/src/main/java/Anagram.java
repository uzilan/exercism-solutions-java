import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Anagram {

    private final String string;
    private final Map<Integer, Long> map;

    public Anagram(String string) {
        this.string = string;
        this.map = getWordMap(string);
    }

    public List<String> match(List<String> strings) {
        return strings.stream()
                .filter(word -> !word.equalsIgnoreCase(string) && getWordMap(word).equals(map))
                .collect(Collectors.toList());
    }

    private Map<Integer, Long> getWordMap(String word) {
        return word
                .toLowerCase()
                .codePoints()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
