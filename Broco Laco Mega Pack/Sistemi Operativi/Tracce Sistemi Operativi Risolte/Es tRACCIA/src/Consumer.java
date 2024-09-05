import java.util.Random;

public class Consumer extends Thread{
    private Semaphore personal_mutex;
    private Semaphore other_mutex;
    private Semaphore[] producer_semaphores;
    private Queue queue;
    private Random rand = new Random();
    private int next_producer;

    public Consumer(Semaphore personal_mutex, Semaphore other_mutex, Semaphore[] producer_semaphores, Queue queue){
        this.personal_mutex = personal_mutex;
        this.other_mutex = other_mutex;
        this.producer_semaphores = producer_semaphores;
        this.queue = queue;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            Integer i = queue.remove();
            System.out.println("Thread consumer " + getId() + " has consumed element " + i);
            if(queue.isEmpty()){
                next_producer = rand.nextInt(producer_semaphores.length);
                producer_semaphores[next_producer].up();
            }
            else{
                other_mutex.up();
            }
        }
    }

}
