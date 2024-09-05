public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Semaphore[] workers_mutexes = new Semaphore[10];
        int[] workers_ids = new int[10];
        for(int i=0;i<10;i++){
            workers_mutexes[i] = new Semaphore(0);
            workers_ids[i] = i + 1;
        }
        workers_mutexes[0] = new Semaphore(1);

        Worker.Setup(workers_mutexes,workers_ids);

        for(int i=0;i<10;i++){
            Worker worker = new Worker(workers_mutexes[i],workers_ids[i],10000);
        }
        

    }
}
