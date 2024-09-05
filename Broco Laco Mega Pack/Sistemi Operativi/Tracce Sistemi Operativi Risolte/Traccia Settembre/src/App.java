public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] players_mutexes = new Semaphore[3];
        for(int i=0;i<3;i++){
            players_mutexes[i] = new Semaphore(0);
        }
        for(int i=0;i<3;i++){
            Player player = new Player(players_mutexes,i);
        }

    }
}
