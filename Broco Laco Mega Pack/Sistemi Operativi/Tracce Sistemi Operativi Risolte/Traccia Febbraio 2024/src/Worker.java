public class Worker extends Thread {
    public Semaphore mutex;
    public Semaphore next_mutex;
    private int k;
    public static Piece piece = new Piece();

    public Worker(){}
    public void initialize(Semaphore mutex, Semaphore next_mutex, int k){
        this.mutex = mutex;
        this.next_mutex = next_mutex;
        this.k = k;
        this.start();
    }
    public void run(){
        for(int i=0;i<k;i++){
            mutex.down();
            piece.work(this.getId());
            next_mutex.up();
        }
    }
}
