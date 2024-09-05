import java.util.Random;

public class Produttore extends Thread{
    private Semaphore personal_mutex;
    private Semaphore[] producer_semaphores;
    private Semaphore[] consumer_semaphores;
    private Queue queue;
    private int next_semaphore;
    private Random rand = new Random();

    public Produttore(Semaphore personal_mutex, Semaphore[] consumer_semaphores, Queue queue, Semaphore[] producer_semaphores){
        this.personal_mutex = personal_mutex;
        this.consumer_semaphores = consumer_semaphores;
        this.queue = queue;
        this.producer_semaphores = producer_semaphores;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            Integer i = queue.insert(rand.nextInt(10));
            System.out.println("Thread producer " + getId() + " has produced element " + i);
            if(queue.isFull()){
                next_semaphore = rand.nextInt(consumer_semaphores.length);
                consumer_semaphores[next_semaphore].up();
            }
            else{
                next_semaphore = rand.nextInt(producer_semaphores.length);
                producer_semaphores[next_semaphore].up();
            }
        }
    }
    
}
