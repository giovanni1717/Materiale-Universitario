public class Deck {
    private int cards_left = 42;

    public int takecard(){
        if(cards_left > 0){
            cards_left--;
            return 42 - cards_left;
            
        }
        else return -1;
    }
    public boolean hasCards(){return cards_left > 0;}
    public int cardsLeft(){return cards_left;}
}

