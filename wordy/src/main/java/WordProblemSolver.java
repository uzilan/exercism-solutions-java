import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class WordProblemSolver {

    private static final Pattern PATTERN =
            Pattern.compile("What is -?\\d+ [(plus|minus|multiplied by|divided by) -?\\d+]+\\?");

    public int solve(final String input) {
        if (!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        final var expression = input.substring(8, input.length() - 1);
        final var iter = new ThingIterator(expression);
        var x = iter.next().number;

        while (iter.hasNext()) {
            final var op = iter.next().op;
            final var y = iter.next().number;
            x = op.apply(x, y);
        }

        return x;
    }

    static class Thing {
        final Integer number;
        final BiFunction<Integer, Integer, Integer> op;

        Thing(final int number) {
            this.number = number;
            this.op = null;
        }

        Thing(final BiFunction<Integer, Integer, Integer> op) {
            this.number = null;
            this.op = op;
        }
    }

    class ThingIterator implements Iterator<Thing> {

        private int position = 0;
        private String input;

        ThingIterator(final String input) {
            this.input = input;
        }

        @Override
        public boolean hasNext() {
            return getNextSpace() != -1;
        }

        @Override
        public Thing next() {
            final var s = hasNext()
                    ? input.substring(position, getNextSpace())
                    : input.substring(position);

            try {
                final var thing = new Thing(parseInt(s));
                position += s.length() + 1;
                return thing;
            } catch (NumberFormatException e) {
                return parseOperation(s);
            }
        }

        private Thing parseOperation(final String operation) {
            switch (operation) {
                case "plus":
                    position += "plus ".length();
                    return new Thing(Integer::sum);
                case "minus":
                    position += "minus ".length();
                    return new Thing((x, y) -> x - y);
                case "multiplied":
                    position += "multiplied by ".length();
                    return new Thing((x, y) -> x * y);
                case "divided":
                    position += "divided by ".length();
                    return new Thing((x, y) -> x / y);
                default:
                    throw new NoSuchElementException();
            }
        }

        private int getNextSpace() {
            return input.indexOf(' ', position);
        }
    }
}
