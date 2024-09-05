import java.util.ArrayList;

public class Impiegato extends Thread{
    private static Semaphore faldone_mutex = new Semaphore(1);
    private static ArrayList<Integer> faldone;
    private Semaphore scrivania_capufficio_mutex;
    private Semaphore posti_su_scrivania;
    private Semaphore pratiche_su_scrivania;
    private static ArrayList<Integer> pratiche;

    public static void Setup(ArrayList<Integer> f, Semaphore scrivania, Semaphore posti, Semaphore prat, ArrayList<Integer> p){
        faldone = f;

        pratiche = p;
    }

    public Impiegato(Semaphore scrivania, Semaphore posti, Semaphore prat){
        scrivania_capufficio_mutex = scrivania;
        posti_su_scrivania = posti;
        pratiche_su_scrivania = prat;
        this.start();
    }

    public void run(){
        while(true){
            faldone_mutex.down();
            int pratica;
            if(!faldone.isEmpty()){
                pratica = faldone.remove(0);
                System.out.println("Impiegato " + getId() + " prende la pratica " + pratica);
                faldone_mutex.up();
            }
            else{
                faldone_mutex.up();
                System.out.println("Farewell " + getId());
                break;
            }
            // Lavorazione pratica...
            posti_su_scrivania.down();
            scrivania_capufficio_mutex.down();
            pratiche.add(pratica);
            //System.out.println("Impiegato " + getId() + " inserisce la pratica " + pratica);
            //System.out.println("Pratiche: " + pratiche);
            scrivania_capufficio_mutex.up();
            pratiche_su_scrivania.up();
            
        }
    }

}
