import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    public Deque() {                     
        // construct an empty deque
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        // is the deque empty?
//        return first == null && last == null;
        return size == 0;
    }

    public int size() {
        // return the number of items on the deque
        return this.size;
    }

    public void addFirst(Item item) {   
        // insert the item at the front
        if (item == null) {
            throw new NullPointerException();
        }
        
        Node oldFirst = this.first;
        
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = oldFirst;
        newFirst.prev = null;
        
        if (oldFirst != null) 
            oldFirst.prev = newFirst;
        
        this.first = newFirst;

        if (this.size == 0)
            this.last = newFirst;
    
        this.size++;
    }

    public void addLast(Item item) {    
        // insert the item at the end
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldLast = this.last;

        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        newLast.prev = oldLast;
       
        if (this.size < 1)
            this.first = newLast;

        if (oldLast != null)
            oldLast.next = newLast;

        this.last = newLast; 

        this.size++;
    }

    public Item removeFirst() {         
        // delete and return the item at the front
        if (this.size() < 1) {
            throw new NoSuchElementException();
        }

        Item it = this.first.item;
        Node newFirst = this.first.next;
        if (newFirst != null)
            newFirst.prev = null;
        this.first = newFirst;

        this.size--;
        return it;
    }

    public Item removeLast() {
        // delete and return the item at the end
        if (this.size() < 1) {
            throw new NoSuchElementException();
        }

        Item it = this.last.item;
        Node newLast = this.last.prev;
        if (newLast != null)
            newLast.next = null;
        this.last = newLast;
    


        this.size--;
        if (this.size < 1)
            this.first = null;
        return it;
    }

    private void print() {
        StdOut.println("Printing");
        Iterator<Item> i = this.iterator();
        while (i.hasNext()) {
            Item it = i.next();
            StdOut.println(it);
        }
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */
        throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {   
        // test client
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            StdOut.println(dq.isEmpty());
            dq.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            dq.removeFirst();
            StdOut.println(dq.isEmpty());
        }


        for (int i : dq)
            StdOut.println(i);
        
    }
}

