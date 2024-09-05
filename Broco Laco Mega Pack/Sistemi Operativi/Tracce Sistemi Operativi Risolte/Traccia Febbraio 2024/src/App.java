import java.util.Random;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        int k = 5;
        Worker[] workers = new Worker[k];
        long max_id = -1;
        int max = -1;
        int starting_point = -1;
        Random random = new Random();
        Semaphore[] personalmutexes = new Semaphore[k];
        for(int i=0;i<k;i++){
            workers[i] = new Worker();
            int value = random.nextInt(101);
            if((value > max) || ((value == max) && (workers[i].getId() > max_id))){
                max = value;
                max_id = workers[i].getId();
                starting_point = i;
            }
            personalmutexes[i] = new Semaphore(0);
        }
        System.out.println("Starting point is Thread ID " + max_id);
        personalmutexes[starting_point] = new Semaphore(1);
        for(int i=0;i<k;i++){
            workers[i].initialize(personalmutexes[i],personalmutexes[(i + 1) % k],5);
        }
    }
}
