public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] semaphores = new Semaphore[10];
        Lister lister = new Lister(10);
        for(int i=1;i<10;i++){
            semaphores[i] = new Semaphore(0);
        }
        semaphores[0] = new Semaphore(1);

        for(int i=0;i<10;i++){
            Worker worker = new Worker(lister, semaphores[i], semaphores, 5000);
        }
    }
}
