public class Worker extends Thread{
    private static Semaphore[] workers_mutexes;
    private Semaphore personal_mutex;
    private static int max_k = -1;
    private static int max_id = -1;
    private int k;
    private int id;
    private static int count = 0;

    public static void Setup(Semaphore[] workers){
        workers_mutexes = workers;
    }

    public Worker(Semaphore personal_mutex, int k, int id){
        this.personal_mutex = personal_mutex;
        this.k = k;
        this.id = id;
        this.start();
    }

    public void run(){
        personal_mutex.down();
        count++;
        if(k > max_k){
            System.out.println("Updated max by " + id + "; Old k/id: " + max_k + "/" + max_id + "; New k/id: " + k + "/" + id);
            max_k = k;
            max_id = id;
        }
        System.out.println("Not updated; k/id: " + k + "/" + id);
        if(count < workers_mutexes.length) workers_mutexes[count].up();
        else workers_mutexes[max_id].up();

        personal_mutex.down();
        System.out.println("Ultimo giro di " + id + "...");
        max_id = (max_id + 1) % workers_mutexes.length;
        workers_mutexes[max_id].up();
    }
}
