public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore consumerA_mutex = new Semaphore(0);
        Semaphore consumerB_mutex = new Semaphore(0);
        Semaphore[] consumer_semaphores = new Semaphore[2];
        consumer_semaphores[0] = consumerA_mutex;
        consumer_semaphores[1] = consumerB_mutex;
        Semaphore[] producer_semaphores = new Semaphore[10];
        Queue queue = new Queue(4);

        for(int i=1;i<10;i++){
            producer_semaphores[i] = new Semaphore(0);
        }
        producer_semaphores[0] = new Semaphore(1);
        
        for(int i=0;i<10;i++){
            Produttore producer = new Produttore(producer_semaphores[i],consumer_semaphores,queue,producer_semaphores);
        }
        Consumer consumerA = new Consumer(consumerA_mutex,consumerB_mutex,producer_semaphores,queue);
        Consumer consumerB = new Consumer(consumerB_mutex,consumerA_mutex,producer_semaphores,queue);
    }
}
