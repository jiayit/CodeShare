/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Model a double-ended queue
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int num;
    private class Node {
        Item item;
        Node next;
    }
    public Deque() {
        first = null;
        last = null;
        num = 0;
    }                           // construct an empty deque
    public boolean isEmpty() {
        //        return (first == null || (last == null && first.next == null));
        return first == null || (last == null && first.next == null);
    }                // is the deque empty?
    public int size() {
        return num;
    }                       // return the number of items on the deque
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        num++;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last = first; //initialize the last node
        else first.next = oldFirst; 
    }         // add the item to the front
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        num++;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last; // initialize the first node
        else  oldLast.next = last;
    }          // add the item to the end
    public Item removeFirst() {
        if (isEmpty()) {
            last = null;
            throw new NoSuchElementException();
        }
        num--;
        Item item = first.item;
        if (first == last) {  // consider that there is only one node last
            first = null;
            last = null;
            return item;
        }
        first = first.next;
        return item;
    }               // remove and return the item from the front
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        num--;
        Item item = last.item;
        Node lastbef = first;
        if (first == last) {  //considering that there is only one node last
            first = null;
            last = null;
            return item;
        }

        while (lastbef.next != last) { // move the pointer to the end
            lastbef = lastbef.next;
        }
        last = lastbef;
        return item;
    }                // remove and return the item from the end
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }        // return an iterator over items in order from front to end
    private class ListIterator implements Iterator<Item> {
        private Node current;
        public ListIterator() {
            current = first;
        }
        @Override
        public boolean hasNext() { return current != null; }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
        @Override
        public Item next() {
            if (!hasNext()) {  
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println(deque.isEmpty());
        deque.addFirst(1);
        StdOut.println(deque.removeFirst());

        deque.addFirst(3);
        StdOut.println(deque.isEmpty());
        StdOut.println(deque.removeLast());
        //        StdOut.println("(" + deque.size() +" left on the deque)");  

    }  // unit testing (optional)
}
