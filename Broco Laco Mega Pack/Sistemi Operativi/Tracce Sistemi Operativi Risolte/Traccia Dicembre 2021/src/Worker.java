public class Worker extends Thread{
    private static Semaphore[] workers_mutexes;
    private static int len;
    private static int count = 0;
    private Semaphore personal_mutex = new Semaphore(0);
    private static Semaphore mutex_sportello = new Semaphore(1);
    private int k;

    public static void Setup(int length){
        workers_mutexes = new Semaphore[length];
        len = length;
    }
    public Worker(int k){
        this.k = k;
        this.start();
    }

    public void run(){
        mutex_sportello.down();
        workers_mutexes[count] = personal_mutex;
        System.out.println("Thread " + getId() + " si mette al posto " + count);
        count = (count + 1) % len;
        if(count == 0) workers_mutexes[count].up();
        mutex_sportello.up();

        for(int i=0;i<k;i++){
            personal_mutex.down();
            System.out.println("Thread " + getId() + " sta cucinando...");
            count = (count + 1) % len;
            workers_mutexes[count].up();
        }
    }
}
