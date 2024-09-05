public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore prodA_mutex = new Semaphore(1);
        Semaphore prodB_mutex = new Semaphore(0);
        Semaphore prodC_mutex = new Semaphore(0);
        Semaphore cons_mutex = new Semaphore(0);
        Queue queue = new Queue(4);
        queue.enqueue(0); queue.enqueue(0);

        Producer.Setup(queue, cons_mutex);
        Producer prodA = new Producer(prodA_mutex, prodB_mutex);
        Producer prodB = new Producer(prodB_mutex, prodC_mutex);
        Producer prodC = new Producer(prodC_mutex, prodA_mutex);
        Consumer cons = new Consumer(cons_mutex,prodA_mutex,queue);

    }
}
