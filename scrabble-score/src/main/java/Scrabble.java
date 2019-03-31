import java.util.HashMap;
import java.util.Map;

class Scrabble {

    private static Map<String, Integer> letterMap = new HashMap<>();

    static {
        letterMap.put("AEIOULNRST", 1);
        letterMap.put("DG", 2);
        letterMap.put("BCMP", 3);
        letterMap.put("FHVWY", 4);
        letterMap.put("K", 5);
        letterMap.put("JX", 8);
        letterMap.put("QZ", 10);
    }

    private final int score;

    Scrabble(String word) {
        score = word.toUpperCase()
                .codePoints()
                .map(c -> findValue((char) c))
                .sum();
    }

    int getScore() {
        return score;
    }

    private int findValue(char c) {
        return letterMap.keySet()
                .stream()
                .filter(s -> s.indexOf(c) >= 0)
                .findFirst()
                .map(i -> letterMap.get(i))
                .orElse(0);
    }
}