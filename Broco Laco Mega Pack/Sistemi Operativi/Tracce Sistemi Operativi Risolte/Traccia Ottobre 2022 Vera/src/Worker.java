public class Worker extends Thread{
    private static Semaphore worker_mutex;
    private static Semaphore controller_mutex;
    public static int n_work;
    private int k;
    private static Semaphore[] worker_turns;
    private int id;

    public static void Setup(Semaphore worker, Semaphore controller, Semaphore[] turns){
        worker_mutex = worker;
        controller_mutex = controller;
        worker_turns = turns;
        n_work = worker_turns.length;
    }
    public Worker(int k, int id){
        this.k = k;
        this.id = id;
        this.start();
    }
    public void run(){
        for(int i=0;i<k;i++){
            worker_turns[id].down();
            //System.out.println("Thread " +  getId() + " sta lavorando...");
            //System.out.println("Iterazione " + i);
            worker_mutex.down();
            n_work--;
            System.out.println("N_work: " + n_work);
            if(n_work == 0){
                worker_mutex.up();
                controller_mutex.up();
            }
            else worker_mutex.up();
        }
    }
}
