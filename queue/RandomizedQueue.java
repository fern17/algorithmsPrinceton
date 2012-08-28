import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N = 0;
    
    public RandomizedQueue() {
        // construct an empty randomized queue
        this.items = (Item[]) new Object[1];
    }

    private void resize(int newsize) {
        Item[] it = (Item[]) new Object[newsize];
        for (int i = 0; i < newsize && i < this.items.length; i++) {
            it[i] = this.items[i];
        }
        this.items = it;
    }

    
    public boolean isEmpty() {
        // is the queue empty?
        return N == 0;
    }
    
    public int size() {
        // return the number of items on the queue
        return N;
    }
    
    public void enqueue(Item item) {
        // add the item
        if (item == null)
            throw new NullPointerException();
        if (N >= items.length)
            resize(2 * items.length);
        this.items[N] = item;
        N++;
    }
    
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        // delete and return a random item
        int pos = StdRandom.uniform(N);
        Item i = this.items[pos];
        for (int j = pos; j < this.N-1; j++) {
            this.items[j] = this.items[j+1];
        }
        //this.items[N] = null;
        
        this.N--;

        if (this.N < this.items.length*0.25) {
            int newsize = (int) Math.floor(this.items.length*0.5);
            resize(newsize);
        }
        return i;
    }
    
    public Item sample() {
        // return (but do not delete) a random item
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int pos = StdRandom.uniform(N);
        return this.items[pos];
    }
    
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Item[] values;
        private int pos;
        private int size;
        
        public ListIterator() {
            this.values = (Item[]) new Object[N];
            for (int i = 0; i < N; i++)
                this.values[i] = items[i];
            this.pos = 0;
            this.size = N;
            StdRandom.shuffle(values);
        }

        public boolean hasNext() {
            return pos != size;
        }

        public void remove() {
            /* not supported */
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = values[pos];
            pos++;
            return item;
        }
    }
    public static void main(String[] args) {   
        // test client
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            StdOut.println(rq.isEmpty());
            rq.enqueue(i);
        }
        /*        
        for (int i = 0; i < 11; i++) {
            rq.dequeue();
            StdOut.println(rq.size());
        }
        */
    }


}
