import java.util.Random;

public class Player extends Thread{
    private static Semaphore mutex = new Semaphore(1);
    private static Deck deck = new Deck();
    private Semaphore[] players_mutexes;
    private int personal_mutex;
    private int cards = 0;
    private static Random rand = new Random();

    public Player(Semaphore[] players_mutexes, int personal_mutex){
        this.players_mutexes = players_mutexes;
        this.personal_mutex = personal_mutex;
        this.start();
    }
    
    public static void Shuffle(){deck = new Deck();}

    public void run(){
        for(int i=0;i<10;i++){
            mutex.down();
            System.out.println("Player " + this.getId() + " got card " + deck.takecard() + " and has now " + (cards + 1) + " card(s)");
            cards++;
            if(deck.cardsLeft() == 12){
                System.out.println("\n\nLET THE DANCE BEGIN!\n\n");
                players_mutexes[0].up();
            }
            mutex.up();
        }
        while(deck.hasCards()){
            players_mutexes[personal_mutex].down();
            if(deck.hasCards()){
                int val = rand.nextInt(1, 3);
                if(val > deck.cardsLeft()){
                    System.out.println("Correcting " + val + " cards to " + deck.cardsLeft() + " cards for player " + this.getId());
                    val = deck.cardsLeft();
                }
                System.out.println("Player " + this.getId() + " tries to draw " + val + " card(s)");
                for(int i=0;i<val;i++){
                    System.out.println("Player " + this.getId() + " got card " + deck.takecard() + " and has now " + (cards + 1) + " card(s)");
                    cards++;
                }
            }
            else System.out.println("\nPlayer " + this.getId() + " has terminated with " + cards + " cards\n");
            players_mutexes[((personal_mutex + 1) % players_mutexes.length)].up();
        }


    }
}
