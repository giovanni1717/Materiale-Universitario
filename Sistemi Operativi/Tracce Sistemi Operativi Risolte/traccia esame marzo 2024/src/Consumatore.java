public class Consumatore extends Thread {
    public Counter count;
    public Semaphore can_produce;
    public Semaphore can_consume;
    public Semaphore producer_mutex;
    public Semaphore consumer_mutex;
    //private int consumed = 0;
    private Semaphore consumerA_mutex;
    private Semaphore consumerB_mutex;

    public Consumatore(Semaphore can_produce, Semaphore can_consume, Semaphore consumer_mutex, Semaphore producer_mutex,Counter count, Semaphore consumerA_mutex, Semaphore consumerB_mutex, String name){
        this.can_produce = can_produce;
        this.can_consume = can_consume;
        this.consumer_mutex = consumer_mutex;
        this.producer_mutex = producer_mutex;
        this.consumerA_mutex = consumerA_mutex;
        this.consumerB_mutex = consumerB_mutex;
        this.count = count;
        this.setName(name);
        this.start();
    }
    public void run(){
        while(true){
            //can_consume.down();
            consumerA_mutex.down();
            consumer_mutex.down();
            
            //can_produce.up();
            count.decrease();
            System.out.println("Consumed object by thread " + this.getName() + ", count = " + count.count);
            //consumed++;
            consumerB_mutex.up();
            //if(consumed == 2) consumerB_mutex.up();
            if(count.count == 0) {
                System.out.println("Upping producer mutex by " + this.getName());
                producer_mutex.up();
            }
            else{
                System.out.println("Upping consumer mutex by " + this.getName()  + ", Consumer Mutex sospesi: " + consumer_mutex.getsospesi());
                consumer_mutex.up();
            } 
        }
    }
}
