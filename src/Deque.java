import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        public Item value = null;
        public Node next = null;
        public Node pre = null;
    }

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {

        size = 0;
        head = tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node temp = new Node();
        temp.value = item;
        if (head != null) {
            temp.next = head;
            head.pre = temp;
            head = temp;

        } else {
            head = tail = temp;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node temp = new Node();
        temp.value = item;
        if (tail != null) {
            temp.pre = tail;
            tail.next = temp;
            tail = temp;
        } else {
            head = tail = temp;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (size == 1) {
            Item value = head.value;
            head=tail=null;
            size--;
            return value;
        } else {
            Item value = head.value;

            Node temp = head.next;
            temp.pre = null;
            head = temp;
            size--;
            return value;
        }


    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        if (size == 1) {
            Item value = tail.value;
            head=tail=null;
            size--;
            return value;
        }
        else {
            Item value = tail.value;
            Node temp = tail.pre;
            temp.next = null;
            tail = temp;
            size--;
            return value;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item value = current.value;
            current = current.next;
            return value;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<>();

        test.addLast(7);
        test.addLast(6);
        test.addLast(5);
        test.addLast(4);
        System.out.println(test.removeFirst());
        System.out.println(test.removeFirst());
        System.out.println("test");

        for (Integer s : test)
            System.out.println(s);


    }

}