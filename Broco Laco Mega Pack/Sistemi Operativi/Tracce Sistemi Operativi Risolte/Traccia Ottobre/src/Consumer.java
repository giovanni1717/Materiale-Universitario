public class Consumer extends Thread{
    private static Semaphore[] producer_mutexes;
    private static Semaphore consumers_mutex;
    private static Queue queue;
    private int id;

    public Consumer(int id){
        this.id = id;
        this.start();
    }
    public static void Setup(Semaphore[] p_mutexes, Semaphore mutex, Queue q){
        producer_mutexes = p_mutexes;
        consumers_mutex = mutex;
        queue = q;
    }
    public void run(){
        while(true){
            consumers_mutex.down();
            try {
                System.out.println("Thread consumer with id " + id + " consumed element " + queue.dequeue());
            } catch (Exception e) {
                System.out.println("an error occurred");
            }
            if(queue.isEmpty()){
                int count = queue.getNextCount();
                producer_mutexes[count].up();
            }
            else consumers_mutex.up();
        }
    }
}
