import java.util.Random;

public class Consumer extends Thread{
    private Semaphore personal_mutex;
    private Semaphore next_consumer_mutex;
    private static Queue queue;
    private static Semaphore[] producers_mutexes;
    private static Random rand = new Random();

    public static void Setup(Queue q, Semaphore[] producers){
        producers_mutexes = producers;
        queue = q;
    }
    public Consumer(Semaphore personal_mutex, Semaphore next_consumer_mutex){
        this.personal_mutex = personal_mutex;
        this.next_consumer_mutex = next_consumer_mutex;
        this.start();
    }

    public void run(){
        while(true){
            personal_mutex.down();
            System.out.println("Thread " + getId() + " has dequeued " + queue.dequeue());
            if(queue.isEmpty()){
                int next = rand.nextInt(producers_mutexes.length);
                producers_mutexes[next].up();
            }
            else next_consumer_mutex.up();
        }
    }
}
