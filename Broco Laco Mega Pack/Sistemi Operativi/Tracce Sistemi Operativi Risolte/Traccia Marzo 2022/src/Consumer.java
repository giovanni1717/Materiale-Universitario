import java.util.ArrayList;

public class Consumer extends Thread{
    private Semaphore posti_liberi;
    private Semaphore posti_pieni;
    private Semaphore deposito_mutex;
    private ArrayList<Integer> deposito;

    public Consumer(Semaphore posti_liberi, Semaphore posti_pieni, Semaphore deposito_mutex, ArrayList<Integer> deposito){
        this.posti_liberi = posti_liberi;
        this.posti_pieni = posti_pieni;
        this.deposito_mutex = deposito_mutex;
        this.deposito = deposito;
        this.start();
    }
    public void run(){
        while(true){
            posti_pieni.down();
            deposito_mutex.down();
            int val = deposito.remove(0);
            System.out.println("Thread Consumer consuma " + val);
            deposito_mutex.up();
            posti_liberi.up();
        }
    }
}
