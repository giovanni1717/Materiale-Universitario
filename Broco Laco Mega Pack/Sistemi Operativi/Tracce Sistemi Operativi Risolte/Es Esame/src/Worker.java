public class Worker extends Thread{
    private Lister lister;
    private Semaphore personal_mutex;
    private Semaphore[] semaphores;
    private int k;

    public Worker(Lister lister, Semaphore personal_mutex, Semaphore[] semaphores, int k){
        this.lister = lister;
        this.personal_mutex = personal_mutex;
        this.semaphores = semaphores;
        this.k = k;
        this.start();
    }
    public void run(){
        for(int i=0;i<k;i++){
            personal_mutex.down();
            System.out.println("Thread " + getId() + " sta cucinando...");
            Integer next = lister.getNext();
            semaphores[next].up();
        }
    }

}