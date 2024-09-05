public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Semaphore[] producer_mutexes = new Semaphore[10];
        Semaphore consumer_mutex = new Semaphore(0);
        Queue queue = new Queue(4);

        for(int i=1;i<10;i++){
            producer_mutexes[i] = new Semaphore(0);
        }
        producer_mutexes[0] = new Semaphore(1); //We fix the starting point

        Producer.Setup(producer_mutexes,consumer_mutex,0,queue);
        Consumer.Setup(producer_mutexes,consumer_mutex,queue);

        for(int i=0;i<10;i++){
            Producer producer = new Producer(producer_mutexes[i], i);
        }
        for(int i=0;i<3;i++){
            Consumer consumer = new Consumer(i + 10);
        }
    }
}
