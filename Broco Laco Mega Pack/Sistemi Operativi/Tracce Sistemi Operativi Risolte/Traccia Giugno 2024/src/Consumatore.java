public class Consumatore extends Thread{
    private Semaphore personal_mutex;
    private Semaphore next_mutex;
    private static Semaphore posti_liberi;
    private static Semaphore posti_occupati;
    private static Semaphore queue_mutex;
    private static Queue queue;
    private int q;


    public static void Setup(Semaphore p_l, Semaphore p_o, Semaphore q_m, Queue q){
        posti_liberi = p_l;
        posti_occupati = p_o;
        queue_mutex = q_m;
        queue = q;
    }
    public Consumatore(Semaphore personal_mutex, Semaphore next_mutex, int q){
        this.personal_mutex = personal_mutex;
        this.next_mutex = next_mutex;
        this.q = q;
        this.start();
    }
    public void run(){
        for(int i=0;i<q;i++){
            posti_occupati.down();
            personal_mutex.down();
            queue_mutex.down();
            //queue.dequeue();
            System.out.println("Thread consumatore " + getId() + " ha consumato " + queue.dequeue() + "; n: " + queue.n);
            queue_mutex.up();
            next_mutex.up();
            posti_liberi.up();
        }
    }
}
