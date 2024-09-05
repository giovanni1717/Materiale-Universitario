public class App {
    public static void main(String[] args) throws Exception {
        /*Semaphore can_produce = new Semaphore(4);
        Semaphore can_consume = new Semaphore(0);
        Semaphore producer_mutex = new Semaphore(1);
        Semaphore consumer_mutex = new Semaphore(0);
        Semaphore consumerA_mutex = new Semaphore(1);
        Semaphore consumerB_mutex = new Semaphore(0);
        Counter count = new Counter(0);

        Produttore produttore1 = new Produttore(can_produce, can_consume, consumer_mutex, producer_mutex, count, "Wiscas");
        Produttore produttore2 = new Produttore(can_produce, can_consume, consumer_mutex, producer_mutex, count, "Felix");
        Produttore produttore3 = new Produttore(can_produce, can_consume, consumer_mutex, producer_mutex, count, "Fedex");
        Consumatore consumatore1 = new Consumatore(can_produce, can_consume, consumer_mutex, producer_mutex, count, consumerA_mutex, consumerB_mutex, "Panko");
        Consumatore consumatore2 = new Consumatore(can_produce, can_consume, consumer_mutex, producer_mutex, count, consumerB_mutex, consumerA_mutex, "Milly");
        */
        Semaphore mutex_produttore = new Semaphore(1);
        Semaphore mutex_consumatore = new Semaphore(0);
        Semaphore empty = new Semaphore(4);
        Semaphore full = new Semaphore(0);
        Counter count = new Counter(0);

        NeoProduttore neoproduttore1 = new NeoProduttore(count, mutex_produttore, mutex_consumatore,empty, full, "Wiskas");
        NeoProduttore neoproduttore2 = new NeoProduttore(count, mutex_produttore, mutex_consumatore,empty, full, "Felix");
        NeoProduttore neoproduttore3 = new NeoProduttore(count, mutex_produttore, mutex_consumatore,empty, full, "Fedex");
        NeoConsumatore neoconsumatore1 = new NeoConsumatore(count, mutex_produttore, mutex_consumatore, empty, full, "Panko");
        NeoConsumatore neoconsumatore2 = new NeoConsumatore(count, mutex_produttore, mutex_consumatore, empty, full, "Milly");
        
    }
}
