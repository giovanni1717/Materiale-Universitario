import java.util.Random;

public class Worker extends Thread{
    private static Semaphore[] workers_mutexes;
    private static int[] workers_ids;
    private Semaphore personal_mutex;
    private int personal_id;
    private static int count = 0;
    private static int next = 0;
    private int k;
    private static Random rand = new Random();
    
    public Worker(Semaphore personal_mutex,int personal_id, int k){
        this.personal_mutex = personal_mutex;
        this.personal_id = personal_id;
        this.k = k;
        this.start();
    }

    public static void Setup(Semaphore[] workers, int[] ids){
        workers_mutexes = workers;
        workers_ids = ids;
    }

    public void run(){
        for(int i=0;i<k;i++){
            personal_mutex.down();
            //System.out.println("Thread with id " + personal_id + " is performing operation...");
            count++;
            
            if(count == workers_ids.length){
                //System.out.println("\nThread " + personal_id + " is choosing next...");
                count = 0;
                do{
                    next = rand.nextInt(workers_ids.length);
                }while(workers_ids[next] == personal_id);
                //System.out.println("Thread " + (next+1) + " has been chosen to start next...\n");
            }
            else next = (next + 1) % workers_ids.length;

            workers_mutexes[next].up();
        }
    }
}
