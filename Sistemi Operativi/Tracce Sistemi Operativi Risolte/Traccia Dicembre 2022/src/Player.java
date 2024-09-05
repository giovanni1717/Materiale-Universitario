import java.util.Random;

public class Player extends Thread{
    public static Semaphore[] players_mutexes;
    private Semaphore personal_mutex;
    private int my_turns = 0;
    private static int done = 0;
    private static Random rand = new Random();
    private int id;

    public static void Setup(Semaphore[] players){
        players_mutexes = players;
    }
    public Player(Semaphore personal_mutex, int id){
        this.personal_mutex = personal_mutex;
        this.id = id;
        this.start();
    }
    public void run(){
        while(true){
            personal_mutex.down();
            if(done != 4){
                my_turns++;
                //System.out.println("Thread " + id + " is at " + my_turns + " turns");
                if(my_turns == 1000){
                    done++;
                    //System.out.println("Incremented done. Now at " + done);
                } 
                int next = (rand.nextInt(10)) % 4;
                //System.out.println("Next: " + next);
                players_mutexes[next].up();
            }
            else{
                //System.out.println("Thread " + id + " has detected game stop.");
                players_mutexes[(id + 1) % players_mutexes.length].up();
                break;
            }
        }
        //System.out.println("END " + getId());
    }
}
