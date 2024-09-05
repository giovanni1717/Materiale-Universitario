import java.util.Queue;

public class Consumer extends Thread{
    private Semaphore[] consumers_mutexes;
    private Semaphore personal_mutex;
    private Queue<Integer> queue;
    private Semaphore p_mutex;
    private static int count = 0;

    public Consumer(Semaphore[] consumers_mutexes, Semaphore personal_mutex, Queue<Integer> queue, Semaphore p_mutex){
        this.consumers_mutexes = consumers_mutexes;
        this.personal_mutex = personal_mutex;
        this.queue = queue;
        this.p_mutex = p_mutex;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            System.out.println("Thread " + getId() + " has dequeued " + queue.remove());
            count = (count + 1) % consumers_mutexes.length;
            if(count == 0) p_mutex.up();
            else consumers_mutexes[count].up();
        }
    }
}
