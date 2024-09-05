public class Produttore extends Thread {
    public Counter count;
    public Semaphore can_produce;
    public Semaphore can_consume;
    public Semaphore producer_mutex;
    public Semaphore consumer_mutex;

    public Produttore(Semaphore can_produce, Semaphore can_consume, Semaphore consumer_mutex, Semaphore producer_mutex,Counter count, String name){
        this.can_produce = can_produce;
        this.can_consume = can_consume;
        this.consumer_mutex = consumer_mutex;
        this.producer_mutex = producer_mutex;
        this.count = count;
        this.setName(name);
        this.start();
    }
    public void run(){
        while(true){
            //can_produce.down();
            producer_mutex.down();
            
            //can_consume.up();
            count.increase();
            System.out.println("Produced object by thread " + this.getName() + ", count = " + count.count);
            if(count.count == 4) {
                System.out.println("Upping consumer mutex by " + this.getName());
                consumer_mutex.up();
            }
            else{
                System.out.println("Upping producer mutex by " + this.getName() + ", Producer Mutex sospesi: " + producer_mutex.getsospesi());
                producer_mutex.up();
            } 
        }
    }
}
