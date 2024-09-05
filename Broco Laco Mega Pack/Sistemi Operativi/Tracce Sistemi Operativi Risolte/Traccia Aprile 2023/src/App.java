public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] producers_mutex = new Semaphore[10];
        Semaphore consumerA_mutex = new Semaphore(0);
        Semaphore consumerB_mutex = new Semaphore(0);
        Semaphore consumerC_mutex = new Semaphore(0);
        Queue queue = new Queue(3);

        for(int i=1;i<10;i++){
            producers_mutex[i] = new Semaphore(0);
        }
        producers_mutex[0] = new Semaphore(1);

        Consumer.Setup(queue,producers_mutex);
        Consumer consumerA = new Consumer(consumerA_mutex,consumerB_mutex);
        Consumer consumerB = new Consumer(consumerB_mutex, consumerC_mutex);
        Consumer consumerC = new Consumer(consumerC_mutex, consumerA_mutex);

        Producer.Setup(producers_mutex,consumerA_mutex,queue);
        for(int i=0;i<10;i++){
            Producer producer = new Producer(producers_mutex[i]);
        }
     }
}
