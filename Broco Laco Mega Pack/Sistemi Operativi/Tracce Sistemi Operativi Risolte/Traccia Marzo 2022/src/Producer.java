import java.util.ArrayList;

public class Producer extends Thread{
    private static Semaphore production_mutex = new Semaphore(1);
    private static ArrayList<Integer> deposito;
    private static Semaphore deposito_mutex;
    private static Semaphore posti_liberi;
    private static Semaphore posti_pieni;

    public static void Setup(ArrayList<Integer> d, Semaphore d_mutex, Semaphore p_l, Semaphore p_p){
        deposito = d;
        deposito_mutex = d_mutex;
        posti_liberi = p_l;
        posti_pieni = p_p;
        }
    
    public Producer(){
        this.start();
    }
    public void run(){
        while(true){
            production_mutex.down();
            int element = (int) getId();
            production_mutex.up();
            posti_liberi.down();
            deposito_mutex.down();
            System.out.println("Thread " + element + " ha aggiunto... Size: " + deposito.size());
            deposito.add(element);
            deposito_mutex.up();
            posti_pieni.up();
        }
    }
}
