public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore posti_liberi = new Semaphore(10);
        Semaphore posti_occupati = new Semaphore(0);
        Semaphore queue_mutex = new Semaphore(1);
        Semaphore consumerA = new Semaphore(0);
        Semaphore consumerB = new Semaphore(0);
        
        Queue queue = new Queue(10);
        Consumatore.Setup(posti_liberi,posti_occupati,queue_mutex,queue);
        Produttore.Setup(posti_liberi,posti_occupati,queue_mutex,consumerA,queue);
        Consumatore consumer1 = new Consumatore(consumerA,consumerB,400);
        Consumatore consumer2 = new Consumatore(consumerB,consumerA,400);
        for(int i=0;i<4;i++){
            Produttore producer = new Produttore(200);
        }
    }
}
