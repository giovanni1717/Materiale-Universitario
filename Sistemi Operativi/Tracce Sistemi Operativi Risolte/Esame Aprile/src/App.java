public class App {
    public static void main(String[] args) throws Exception {
        Archivio archive = new Archivio(10, 7);
        Semaphore mutex = new Semaphore(1);
        Semaphore empty = new Semaphore(10);
        Semaphore full = new Semaphore(0);
        Impiegato impiegato1 = new Impiegato(full,empty,mutex,archive,7);
        Impiegato impiegato2 = new Impiegato(full,empty,mutex,archive,7);
        Capufficio capufficio = new Capufficio(full,empty,mutex,archive,14);
    }
}
