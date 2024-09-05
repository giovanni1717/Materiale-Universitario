import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] worker_mutexes = new Semaphore[10];
        int[] ids = new int[10];
        Random rand = new Random();

        for(int i=0;i<10;i++){
            worker_mutexes[i] = new Semaphore(0);
            ids[i] = i + 1;
        } 
        worker_mutexes[2] = new Semaphore(1);
        //ids[0] = 1;

        Worker.setup(10,2,ids,worker_mutexes);

        for(int i=0;i<10;i++){
            Worker worker = new Worker(rand.nextInt(100),ids[i],worker_mutexes[i]);
        }
    }
}
