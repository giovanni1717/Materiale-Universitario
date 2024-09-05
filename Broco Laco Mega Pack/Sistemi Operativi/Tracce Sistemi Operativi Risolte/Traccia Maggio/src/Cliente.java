public class Cliente extends Thread{
    private static Semaphore mutexSportello = new Semaphore(1);
    private static Semaphore mutexSportelloA = new Semaphore(1);
    private static Semaphore mutexSportelloB = new Semaphore(2);
    private static Macchinetta macchinetta = new Macchinetta();
    private int biglietto;

    public Cliente(){
        this.start();
    }

    public void run(){
        mutexSportello.down();
        biglietto = macchinetta.GetSportello();
        System.out.println("Il thread " + getId() + " verrà servito allo sportello " + (biglietto + 1));
        mutexSportello.up();

        if(biglietto == 0){
            mutexSportelloA.down();
            System.out.println("Thread " + getId() + " servito" + (biglietto + 1));
            mutexSportelloA.up();
        }
        else{
            mutexSportelloB.down();
            System.out.println("Thread " + getId() + " servito " + (biglietto + 1));
            mutexSportelloB.up();
        }

    }
}
