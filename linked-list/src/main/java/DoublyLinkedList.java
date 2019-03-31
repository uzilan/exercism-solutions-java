public class DoublyLinkedList<T> {

    private Node first;
    private Node last;

    public void push(T value) {
        if (first == null) {
            addFirst(value);
        } else {
            last.next = new Node(value, last, null);
            last = last.next;
        }
    }

    public T pop() {
        if (last == null) {
            return null;
        }
        final T value = last.value;
        last = last.previous;
        if (last != null) {
            last.next = null;
        }
        return value;
    }

    public T shift() {
        if (first == null) {
            return null;
        }
        final T value = first.value;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        return value;
    }

    public void unshift(T value) {
        if (first == null) {
            addFirst(value);
        } else {
            first.previous = new Node(value, null, first);
            first = first.previous;
        }
    }

    private void addFirst(T value) {
        first = new Node(value, null, null);
        last = first;
    }

    private class Node {

        private T value;
        private Node previous;
        private Node next;

        private Node(T value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
