import java.util.Random;

public class User extends Thread{
    private static Semaphore sala_attesa = new Semaphore(5);
    private static Semaphore sportelloA = new Semaphore(1);
    private static Semaphore sportelloB = new Semaphore(2);
    private Random rand = new Random();
    private int operation;
    public static int Aops = 0;
    public static int serviti = 0;
    private Semaphore aops_m = new Semaphore(1);
    public User(){
        operation = rand.nextInt(2);
        if(operation == 0){
            aops_m.down();
            Aops++;
            aops_m.up();
        }
        this.start();
    }

    public void run(){
        sala_attesa.down();
        if(operation == 0){
            sportelloA.down();
            //System.out.println("Thread " + getId() + " servito dallo sportello A");
            //System.out.println(Aops);
            serviti++;
            //System.out.println(serviti);
            sportelloA.up();
        }
        else{
            sportelloB.down();
            //System.out.println("Thread " + getId() + " servito allo sportello B");
            sportelloB.up();
        }
        sala_attesa.up();
    }
}
