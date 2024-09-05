public class Producer extends Thread{
    private static Semaphore[] producer_mutexes;
    private Semaphore personal_mutex;
    private static Semaphore consumer_mutex;
    private static Queue queue;
    private static int count;
    private int id;

    public Producer(Semaphore personal_mutex, int id){
        this.personal_mutex = personal_mutex;
        this.id = id;
        this.start();
    }
    public static void Setup(Semaphore[] mutexes,Semaphore c_mutex, int startingpoint, Queue q){
        producer_mutexes = mutexes;
        count = startingpoint;
        consumer_mutex = c_mutex;
        queue = q;
    }

    public void run(){
        while(true){
            personal_mutex.down();
            count = (count + 1) % producer_mutexes.length;
            queue.enqueue(id * 10, count);
            System.out.println("Thread Producer with id " + id + " has produced " + id * 10);
            if(queue.size() == 3){
                //Start consumers
                consumer_mutex.up();
            }
            else{
                producer_mutexes[count].up();
            }
        }
    }


}
