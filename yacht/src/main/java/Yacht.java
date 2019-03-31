import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.rangeClosed;

class Yacht {

    private int score;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        score = calculateScore(dice, yachtCategory);
    }

    int score() {
        return score;
    }

    private int calculateScore(int[] dice, YachtCategory yachtCategory) {
        switch (yachtCategory) {
            case YACHT:
                return yachtScore(dice);
            case ONES:
                return numbersScore(dice, 1);
            case TWOS:
                return numbersScore(dice, 2);
            case THREES:
                return numbersScore(dice, 3);
            case FOURS:
                return numbersScore(dice, 4);
            case FIVES:
                return numbersScore(dice, 5);
            case SIXES:
                return numbersScore(dice, 6);
            case FULL_HOUSE:
                return fullHouseScore(dice);
            case FOUR_OF_A_KIND:
                return fourOfAKindScore(dice);
            case LITTLE_STRAIGHT:
                return straightScore(dice, 1, 5);
            case BIG_STRAIGHT:
                return straightScore(dice, 2, 6);
            case CHOICE:
                return choiceScore(dice);
            default:
                return 0;
        }
    }

    private int yachtScore(final int[] dice) {
        var isYacht = stream(dice).distinct().count() == 1;
        return isYacht ? 50 : 0;
    }

    private int numbersScore(final int[] dice, final int number) {
        return (int) stream(dice).filter(d -> d == number).count() * number;
    }

    private int fullHouseScore(final int[] dice) {
        final var grouped = groupResults(dice);
        return findDiceByPredicate(grouped, entry -> entry.getValue() == 3L)
                .map(n -> stream(dice).sum())
                .orElse(0);
    }

    private int fourOfAKindScore(final int[] dice) {
        final var grouped = groupResults(dice);
        return findDiceByPredicate(grouped, entry -> entry.getValue() >= 4)
                .map(n -> n * 4)
                .orElse(0);
    }

    private int straightScore(final int[] dice, final int from, final int to) {
        final var list = stream(dice).boxed().collect(toList());
        final var range = rangeClosed(from, to).boxed().collect(toList());
        return list.containsAll(range) ? 30 : 0;
    }

    private int choiceScore(final int[] dice) {
        return stream(dice).sum();
    }

    // keys = dice, values = count similar dice
    private Map<Integer, Long> groupResults(final int[] dice) {
        return stream(dice).boxed().collect(groupingBy(identity(), counting()));
    }

    private Optional<Integer> findDiceByPredicate(
            final Map<Integer, Long> grouped,
            Predicate<Entry<Integer, Long>> predicate
    ) {
        return grouped
                .entrySet()
                .stream()
                .filter(predicate)
                .map(Entry::getKey)
                .findFirst();
    }

}
