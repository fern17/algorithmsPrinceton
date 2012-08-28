
public class Subset {
    public static void main(String[] args) {   
        // test client
            RandomizedQueue<String> ss = new RandomizedQueue<String>();
            int K = Integer.parseInt(args[0]);
            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
                ss.enqueue(s);
            }
           
            for (int i = 0; i < K; i++) {
                String ret = ss.dequeue();
                StdOut.println(ret);
            }
    }
}
