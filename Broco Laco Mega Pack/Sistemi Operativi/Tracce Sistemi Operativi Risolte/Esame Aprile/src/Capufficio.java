public class Capufficio extends Thread{
    public Archivio archive;
    public Semaphore full;
    public Semaphore empty;
    public Semaphore mutex;
    public int n_pratiche;

    public Capufficio(Semaphore full, Semaphore empty, Semaphore mutex, Archivio archive, int n_pratiche){
        this.full = full;
        this.empty = empty;
        this.mutex = mutex;
        this.archive = archive;
        this.n_pratiche = n_pratiche;
        this.start();
    }
    public void run(){
        for(int i = 0; i < n_pratiche; i++){
            full.down();
            mutex.down();
            archive.manage();
            mutex.up();
            empty.up();
        }
    }
}
