/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Model a randomized queue
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> { // using  array at the function dequeue and iterator is more easy than linked-list.
    private Item[] a;
    private int num;
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        num = 0;
    }                // construct an empty randomized queue
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < num; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }
    public boolean isEmpty() {
        return num == 0;
    }                // is the queue empty?
    public int size() {
        return num;
    }                       // return the number of items on the queue
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (num == a.length) {
            resize(2 * a.length);
        }
        a[num++] = item;
    }          // add the item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(num); // choose a item randomly and switch it with the last item
        Item item = a[random];
        a[random] = a[--num];
        if (num > 0 && num == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }                   // remove and return a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(num);
        return a[random];
    }                    // return (but do not remove) a random item
    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }        // return an independent iterator over items in random order

    private class RandomIterator implements Iterator<Item> {
        int n = num;
        Item[] random;
        public RandomIterator() {
            random = (Item[]) new Object[n]; //create a new array that stores the items in a random order
            for (int i = 0; i < num; i++) {
                random[i] = a[i];
            }
            StdRandom.shuffle(random);
        }
        @Override
        public boolean hasNext() {
            return n != 0;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return random[--n];
        }

    }
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(138);
        StdOut.println(rq.dequeue());
        rq.enqueue(496);
        rq.enqueue(244);
        rq.enqueue(470);
        StdOut.println(rq.dequeue());
        rq.enqueue(316);
        rq.enqueue(140);
        rq.enqueue(487);
        StdOut.println(rq.dequeue());
        rq.enqueue(117);
        rq.enqueue(451);

    }  // unit testing (optional)
}