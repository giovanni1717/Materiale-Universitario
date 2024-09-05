import java.util.Random;

public class Consumer extends Thread{
    private Semaphore personal_mutex;
    private Semaphore first_producer;
    private Queue queue;
    private Random rand = new Random();

    public Consumer(Semaphore personal_mutex, Semaphore first_producer, Queue queue){
        this.personal_mutex = personal_mutex;
        this.first_producer = first_producer;
        this.queue = queue;
        this.start();
    }

    public void run(){
        while(true){
            personal_mutex.down();
            int k = rand.nextInt(4) + 1;
            for(int i=0;i<k;i++){
                System.out.println("Consumer has consumed object " + queue.dequeue());
            }
            first_producer.up();
        }
    }
}
