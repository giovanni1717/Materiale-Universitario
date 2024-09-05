import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore p_mutex = new Semaphore(1);
        Semaphore[] consumers_mutexes = new Semaphore[5];

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<5;i++){
            consumers_mutexes[i] = new Semaphore(0);
            Consumer consumer = new Consumer(consumers_mutexes,consumers_mutexes[i],queue,p_mutex);
        }
        
        for(int i=0;i<10;i++){
            Producer producer = new Producer(p_mutex,queue,consumers_mutexes[0],5);
        }

    }
}
