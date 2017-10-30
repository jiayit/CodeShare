/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P02
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  a client program
 ******************************************************************************/
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomque = new RandomizedQueue<String>();
        StdOut.println("enter a interger");
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) { 

            String s = StdIn.readString();
            randomque.enqueue(s);

        }   
        for (int i = 0; i < k; i++) {
            StdOut.println(randomque.dequeue());
        }

    }  
}
