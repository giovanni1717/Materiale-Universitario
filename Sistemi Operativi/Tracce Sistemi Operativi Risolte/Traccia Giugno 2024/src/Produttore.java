public class Produttore extends Thread{
    private static Semaphore posti_liberi;
    private static Semaphore posti_occupati;
    private static Semaphore queue_mutex;
    private static Semaphore first_consumer;
    private static Queue queue;
    private static boolean have_consumers_started = false;
    private int k;

    public static void Setup(Semaphore p_l, Semaphore p_o, Semaphore q_m, Semaphore f_c, Queue q){
        posti_liberi = p_l;
        posti_occupati = p_o;
        queue_mutex = q_m;
        first_consumer = f_c;
        queue = q;
    }
    public Produttore(int k){
        this.k = k;
        this.start();
    }
    public void run(){
        for(int i=0;i<k;i++){
            posti_liberi.down();
            queue_mutex.down();
            //System.out.println("Thread " + getId() + " sta incodando;");
            queue.enqueue((int) getId());
            System.out.println("Thread " + getId() + " sta incodando; n: " + queue.m);
            if(!have_consumers_started && queue.size() == 2){
                have_consumers_started = true;
                first_consumer.up();
            } 
            queue_mutex.up();
            posti_occupati.up();
        }
    }
}
