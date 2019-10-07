import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    private int count = 0;

    public RandomizedQueue() {          // construct an empty randomized queue
        size = 0;

        items = (Item[]) new Object[10];
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        items[size++] = item;
        if (size == items.length) resize(2 * items.length);
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        Item item = items[r];
        items[r] = items[size - 1];
        items[--size] = null;
        if (size > 0 && size == items.length / 4) resize(items.length / 2);
        return item;
    }


    // return a random item (but do not remove it)
    public Item sample() {


        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        return items[r];


    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = size;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return items[order[--i]];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        test.enqueue(5);
        test.enqueue(6);
        test.enqueue(7);
        test.dequeue();
        for(Integer s : test){
            System.out.println(s);
        }
    }

}

