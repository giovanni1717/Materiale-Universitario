import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<Semaphore> mutex_queues = new ArrayList<>();
        Semaphore player2_mutex = new Semaphore(0);
        Semaphore player3_mutex = new Semaphore(0);
        mutex_queues.add(player2_mutex);
        mutex_queues.add(player3_mutex);
        Semaphore player1_mutex = new Semaphore(1);

        Player.Setup(mutex_queues);
        Player player3 = new Player(player3_mutex);
        Player player2 = new Player(player2_mutex);
        Player player1 = new Player(player1_mutex);
    }
}
