public class Producer extends Thread{
    private Semaphore personal_mutex;
    private Semaphore next_producer;
    private static Queue queue;
    private static Semaphore consumer_mutex;

    public static void Setup(Queue q, Semaphore consumer){
        queue = q;
        consumer_mutex = consumer;
    }
    public Producer(Semaphore personal_mutex, Semaphore next_producer){
        this.personal_mutex = personal_mutex;
        this.next_producer = next_producer;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            queue.enqueue((int) getId());
            System.out.println("Thread " + getId() + " inserted in the queue, size: " + queue.size());
            if(queue.isFull()){
                consumer_mutex.up();
            }
            else next_producer.up();
        }
    }

}
