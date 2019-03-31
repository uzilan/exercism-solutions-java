import static java.lang.Character.toUpperCase;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class BeerSong {

    public String sing(final int from, final int count) {
        var rows = range(from - count + 1, from + 1)
                .mapToObj(i -> {
                    if (i == 0) {
                        return format(
                                "%s of beer on the wall, %s of beer.\n" +
                                        "Go to the store and buy some more, %s of beer on the wall.\n",
                                capitalize(bottles(i)), bottles(i), bottles(99));
                    } else {
                        return format(
                                "%s of beer on the wall, %s of beer.\n" +
                                        "Take %s down and pass it around, %s of beer on the wall.\n",
                                bottles(i), bottles(i), one(i), bottles(i - 1));
                    }
                })
                .collect(toList());

        reverse(rows);
        return join("\n", rows) + "\n";
    }

    public String singSong() {
        return sing(99, 100);
    }

    private String bottles(int count) {
        switch (count) {
            case 0:
                return "no more bottles";
            case 1:
                return "1 bottle";
            default:
                return count + " bottles";
        }
    }

    private String capitalize(String string) {
        return toUpperCase(string.charAt(0)) + string.substring(1);
    }

    private String one(int count) {
        if (count == 1) {
            return "it";
        } else {
            return "one";
        }
    }
}
