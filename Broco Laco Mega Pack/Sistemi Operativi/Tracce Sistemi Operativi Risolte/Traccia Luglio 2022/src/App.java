import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        Semaphore scrivania_capufficio_mutex = new Semaphore(1);
        Semaphore posti_scrivania = new Semaphore(5);
        Semaphore pratiche_su_scrivania = new Semaphore(0);
        ArrayList<Integer> faldone = new ArrayList<>();
        ArrayList<Integer> pratiche = new ArrayList<>();
        for(int i=0;i<1000;i++) faldone.add(i);

        Impiegato.Setup(faldone,scrivania_capufficio_mutex,posti_scrivania,pratiche_su_scrivania,pratiche);
        Capufficio c = new Capufficio(posti_scrivania,scrivania_capufficio_mutex,pratiche_su_scrivania,pratiche,1000);

        Impiegato i1 = new Impiegato(scrivania_capufficio_mutex,posti_scrivania,pratiche_su_scrivania);
        Impiegato i2 = new Impiegato(scrivania_capufficio_mutex,posti_scrivania,pratiche_su_scrivania);

    }
}
