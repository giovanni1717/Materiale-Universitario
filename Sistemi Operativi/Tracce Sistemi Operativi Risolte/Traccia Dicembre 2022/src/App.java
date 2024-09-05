public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] players_mutexes = new Semaphore[4];
        players_mutexes[0] = new Semaphore(1);
        for(int i=1;i<4;i++){
            players_mutexes[i] = new Semaphore(0);
        }
        Player.Setup(players_mutexes);
        for(int i=0;i<4;i++){
            Player player = new Player(players_mutexes[i],i);
        }
    }
}
