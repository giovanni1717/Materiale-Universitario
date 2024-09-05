import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Semaphore[] workers_mutexes = new Semaphore[1000];
        Random rand = new Random();

        workers_mutexes[0] = new Semaphore(1);
        for(int i=1;i<1000;i++){
            workers_mutexes[i] = new Semaphore(0);
        }
        Worker.Setup(workers_mutexes);
        for(int i=0;i<1000;i++){
            Worker worker = new Worker(workers_mutexes[i],rand.nextInt(100),i);
        }
    }
}
