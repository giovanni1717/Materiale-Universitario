import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore deposito_mutex = new Semaphore(1);
        Semaphore posti_liberi = new Semaphore(5);
        Semaphore posti_pieni = new Semaphore(0);
        ArrayList<Integer> deposito = new ArrayList<>();

        Producer.Setup(deposito,deposito_mutex,posti_liberi,posti_pieni);
        Consumer cons = new Consumer(posti_liberi, posti_pieni, deposito_mutex,deposito);
        Producer p1 = new Producer();
        Producer p2 = new Producer();
    }
}
