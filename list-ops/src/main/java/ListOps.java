import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        final var list = new ArrayList<>(list1);
        list.addAll(list2);
        return list;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        return listOfLists.stream().flatMap(Collection::stream).collect(toList());
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(toList());
    }

    static <T> int size(List<T> list) {
        return list.size();
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        return list.stream().map(transform).collect(toList());
    }

    static <T> List<T> reverse(List<T> list) {
        final var newList = new ArrayList<>(list);
        Collections.reverse(newList);
        return newList;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        if (list.isEmpty()) {
            return initial;
        }

        final var size = size(list);
        final var first = list.get(0);
        final var newInitial = f.apply(initial, first);
        return foldLeft(list.subList(1, size), newInitial, f);
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        if (list.isEmpty()) {
            return initial;
        }

        final var size = size(list);
        final var last = list.get(size - 1);
        final var newInitial = f.apply(last, initial);
        return foldRight(list.subList(0, size - 1), newInitial, f);
    }

    private ListOps() {
        // No instances.
    }
}
