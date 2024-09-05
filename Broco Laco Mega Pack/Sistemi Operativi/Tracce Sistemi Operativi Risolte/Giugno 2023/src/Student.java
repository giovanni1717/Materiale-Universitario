import java.util.Random;

public class Student extends Thread{
    private static Semaphore waitingroom = new Semaphore(2);
    private static Semaphore provaA_mutex = new Semaphore(1);
    private static Semaphore provaB_mutex = new Semaphore(1);
    private static Semaphore provaC_mutex = new Semaphore(1);
    private Random rand = new Random();
    private boolean prima_prova_A;

    public Student(){
        if(rand.nextInt(2) == 0) prima_prova_A = false;
        else prima_prova_A = true;
        start();
    }
    public void run(){
        if(prima_prova_A){
            waitingroom.down();
            provaA_mutex.down();
            System.out.println("Studente " + this.getId() + " sta effettuando la prova A...");
            provaA_mutex.up();
            provaB_mutex.down();
            System.out.println("Studente " + this.getId() + " sta effettuando la prova B...");
            provaB_mutex.up();
            System.out.println("Studente " + this.getId() + " ha terminato le prove A e B...\n");
            waitingroom.up();
        }
        else{
            waitingroom.down();
            provaB_mutex.down();
            System.out.println("Studente " + this.getId() + " sta effettuando la prova B...");
            provaB_mutex.up();
            provaA_mutex.down();
            System.out.println("Studente " + this.getId() + " sta effettuando la prova A...");
            provaA_mutex.up();
            System.out.println("Studente " + this.getId() + " ha terminato le prove B e A...\n");
            waitingroom.up();
        }
        provaC_mutex.down();
        System.out.println("Studente " + this.getId() + " sta effettuando la prova finale...");
        provaC_mutex.up();
    }
}
