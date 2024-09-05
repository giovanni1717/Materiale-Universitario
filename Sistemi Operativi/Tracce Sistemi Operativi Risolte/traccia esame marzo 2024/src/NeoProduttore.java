public class NeoProduttore extends Thread{
    public Counter count;
    public Semaphore mutex_produttore;
    public Semaphore mutex_consumatore;
    public Semaphore empty;
    public Semaphore full;
    public NeoProduttore(Counter count, Semaphore mutex_produttore, Semaphore mutex_consumatore, Semaphore empty,
            Semaphore full, String name) {
        this.count = count;
        this.mutex_produttore = mutex_produttore;
        this.mutex_consumatore = mutex_consumatore;
        this.empty = empty;
        this.full = full;
        this.setName(name);
        this.start();
    }
    public void run(){
        while(true){
            empty.down();
            mutex_produttore.down();
            
            
            count.increase();
            System.out.println("Producer " + getName() + " has produced an object");
            full.up();
            if(count.count != 4) mutex_produttore.up();
            else mutex_consumatore.up();
        }
    }
    
}

class NeoConsumatore extends Thread{
    public Counter count;
    public Semaphore mutex_produttore;
    public Semaphore mutex_consumatore;
    public Semaphore empty;
    public Semaphore full;
    public NeoConsumatore(Counter count, Semaphore mutex_produttore, Semaphore mutex_consumatore, Semaphore empty,
            Semaphore full, String name) {
        this.count = count;
        this.mutex_produttore = mutex_produttore;
        this.mutex_consumatore = mutex_consumatore;
        this.empty = empty;
        this.full = full;
        this.setName(name);
        this.start();
    }
    public void run(){
        while(true){
            full.down();
            mutex_consumatore.down();
            
            
            count.decrease();
            System.out.println("Consumer " + getName() + " has consumed an object");
            empty.up();
            if(count.count != 0) mutex_consumatore.up();
            else mutex_produttore.up();
        }
    }
    
}