import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N = 0;
    
    private void resize(int newsize) {
        Item[] it = (Item[]) new Object[newsize];
        for (int i = 0; i < this.items.length; i++) {
            it[i] = this.items[i];
        }
        this.items = it;
    }

    public RandomizedQueue() {
        // construct an empty randomized queue
        this.items = (Item[]) new Object[1];
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
        if (N == items.length)
            resize(2 * items.length);
        this.items[N] = item;
        N++;
    }
    
    public Item dequeue() {
        // delete and return a random item
        int pos = StdRandom.uniform(N);
        Item i = this.items[pos];

        if (this.items.length*0.25 > this.N)
            resize((Integer) this.items.length*0.25);
    }
    
    public Item sample() {
        // return (but do not delete) a random item
        int pos = StdRandom.uniform(N);
        return this.items[pos];
    }
    
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Items[] values;
        private int pos;
        private int size;
        
        public ListIterator() {
            this.values = (Item[]) new Object[items.length];
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


}
