import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()){
            randomizedQueue.enqueue(StdIn.readString());
        }
        int j = 0;
        for (Object i : randomizedQueue){
            if (j < k){
                break;
            }
            System.out.println(i);
            j++;
        }
    }
}
