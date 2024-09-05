import java.util.Queue;

public class Producer extends Thread{
    private Semaphore p_mutex;
    private static int count = 0;
    private Queue<Integer> queue;
    private Semaphore first_consumer;
    private int k;

    public Producer(Semaphore p_mutex, Queue<Integer> queue, Semaphore first_consumer, int k){
        this.p_mutex = p_mutex;
        this.queue = queue;
        this.first_consumer = first_consumer;
        this.k = k;
        this.start();
    }
    public void run(){
        while(true){
            p_mutex.down();
            queue.add((int) getId());
            System.out.println("Thread " + getId() + " just added itself");
            count = (count + 1) % k;
            if(count == 0) first_consumer.up();
            else p_mutex.up();
        }
    }
}
