import java.util.ArrayList;

public class Capufficio extends Thread{
    private Semaphore posti_su_scrivania;
    private Semaphore capufficio_mutex;
    private Semaphore pratiche_su_scrivania;
    private ArrayList<Integer> pratiche;
    private int k;

    public Capufficio(Semaphore posti, Semaphore c_mutex, Semaphore prat, ArrayList<Integer> p, int k){
        posti_su_scrivania = posti;
        capufficio_mutex = c_mutex;
        pratiche_su_scrivania = prat;
        pratiche = p;
        this.k = k;
        this.start();
    }

    public void run(){
        for(int i=0;i<k;i++){
            pratiche_su_scrivania.down();
            capufficio_mutex.down();
            int pratica = pratiche.remove(0);
            System.out.println("Capufficio gestisce pratica " + pratica);
            System.out.println("Pratiche: " + pratiche);
            capufficio_mutex.up();
            posti_su_scrivania.up();
        }
    }
}
