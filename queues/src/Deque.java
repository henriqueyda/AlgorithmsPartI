import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    // is the deque empty?
    public boolean isEmpty(){
        return first == null || last == null;
    }
    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if(isEmpty()) {
            last = first;
        }
        else {
            oldFirst.prev = first;
        }
        size++;
    }
    // add the item to the back
    public void addLast(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()){
            first = last;
        }
        else {
            oldLast.next = last;
        }
        size++;
    }
    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = first;
        }
        else {
            first.prev = null;
        }
        size--;
        return item;
    }
    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if(isEmpty()){
            first = last;
        }
        else {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(8);
        deque.addFirst(10);
        deque.addFirst(40);
        deque.removeLast();
        deque.removeLast();
        for (Object i : deque){
            System.out.println(i);
        }
    }

}