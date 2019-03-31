import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> {

    private Node head;

    public SimpleLinkedList() {
    }

    public SimpleLinkedList(final T[] values) {
        for (T value : values) {
            head = new Node(value, head);
        }
    }

    public int size() {
        int i = 0;
        Node temp = head;
        while (temp != null) {
            i++;
            temp = temp.next;
        }
        return i;
    }

    public T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public void push(final T value) {
        head = new Node(value, head);
    }

    public void reverse() {
        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head = previous;
    }

    public T[] asArray(final Class<T> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, size());
        int i = 0;
        Node node = head;
        while (node != null) {
            array[i] = node.value;
            i++;
            node = node.next;
        }
        return array;
    }

    private class Node {

        private T value;
        private Node next;

        private Node(final T value, final Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
