import java.util.ArrayList;
import java.util.Random;

public class Player extends Thread{
    private static ArrayList<Semaphore> mutex_queue;
    private Semaphore personal_mutex;
    private int cards = 1000;
    private static Random rand = new Random();

    public static void Setup(ArrayList<Semaphore> queue){
        mutex_queue = queue;
    }

    public Player(Semaphore personal_mutex){
        this.personal_mutex = personal_mutex;
        this.start();
    }

    public void run(){
        while(true){
            personal_mutex.down();
            if(mutex_queue.isEmpty()){
                System.out.println("Thread " + getId() + " vince!");
                break;
            }
            int cards_to_remove = rand.nextInt(2) + 1;
            cards = cards - cards_to_remove;
            Semaphore next = mutex_queue.remove(0);
            System.out.println("Thread " + getId() + " ha rimosso " + cards_to_remove + " carte e ha " + cards + " carte");
            if(cards > 0){
                mutex_queue.add(mutex_queue.size(),personal_mutex);
                next.up();
            }
            else{
                System.out.println("Thread " + getId() + " ha fatto il giallo");
                next.up();
                break;
            }
        }
    }

}
