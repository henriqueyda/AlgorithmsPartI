import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACTIY = 8;

    private Item[] q;
    private int n;
    private int first;
    private int last;

    // construct an empty randomized queue
    public RandomizedQueue(){
        q = (Item[]) new Object[INIT_CAPACTIY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++){
            copy[i] = q[i];
        }
        q = copy;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == q.length){
            resize(n*2);
        }
        q[last++] = item;
        n++;
    }
    private Item swapWithLast(int index) {
        Item lastItem = q[last - 1];
        Item removedItem = q[index];
        q[index] = lastItem;
        q[last-1] = null;
        last--;
        return removedItem;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if (n <= q.length/4){
            resize(q.length/4);
        }
        int randomIndex = StdRandom.uniformInt(n);
        Item removedItem = swapWithLast(randomIndex);
        n--;
        return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(n);
        return q[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        Item[] copy = (Item[]) new Object[n];
        for (int i = 0; i < n; i++){
            copy[i] = q[i];
        }
        q = copy;
        StdRandom.shuffle(q);
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements  Iterator<Item>{
        private int current = first;
        public boolean hasNext(){
            return current < n;
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = q[current];
            current++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(10);
        randomizedQueue.enqueue(15);
        randomizedQueue.enqueue(17);
        randomizedQueue.enqueue(20);
        randomizedQueue.enqueue(23);
        randomizedQueue.enqueue(21);
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        for (Object i : randomizedQueue){
            System.out.println(i);
        }
    }

}
