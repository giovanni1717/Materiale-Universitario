import java.util.Random;

public class Producer extends Thread {
    private static Semaphore[] producers_mutexes;
    private Semaphore personal_mutex;
    private static Semaphore first_consumer_mutex;
    private static Random rand = new Random();
    private static Queue queue;

    public static void Setup(Semaphore[] producers, Semaphore consumer, Queue q){
        producers_mutexes = producers;
        first_consumer_mutex = consumer;
        queue = q;
    }

    public Producer(Semaphore personal_mutex){
        this.personal_mutex = personal_mutex;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            queue.enqueue((int) getId());
            System.out.println("Thread " + getId() + "enqueued");
            if(queue.isFull()){
                first_consumer_mutex.up();
            }
            else{
                int next = rand.nextInt(producers_mutexes.length);
                producers_mutexes[next].up();
            }
        }
    }
}
