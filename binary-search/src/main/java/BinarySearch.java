import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class BinarySearch<T extends Comparable<T>> {

    private final int NOT_FOUND = -1;
    private List<Node> nodeList;

    public BinarySearch(List<T> list) {
        this.nodeList = range(0, list.size())
                .mapToObj(i -> new Node(list.get(i), i))
                .collect(toList());
    }

    public int indexOf(T item) {
        return findInSubList(item, nodeList);
    }

    private int findInSubList(T s, List<Node> subList) {
        if (subList.isEmpty()) {
            return NOT_FOUND;
        }

        if (subList.size() == 1 && !subList.get(0).value.equals(s)) {
            return NOT_FOUND;
        }

        final int middle = subList.size() / 2;
        final Node middleNode = subList.get(middle);

        if (middleNode.value.equals(s)) {
            return middleNode.index;
        }

        if (s.compareTo(middleNode.value) < 0) {
            return findInSubList(s, subList.subList(0, middle));
        }

        return findInSubList(s, subList.subList(middle, subList.size()));
    }

    private class Node {

        private Node(T value, int index) {
            this.value = value;
            this.index = index;
        }

        private T value;
        private int index;
    }
}
