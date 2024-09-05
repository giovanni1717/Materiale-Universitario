public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore[] worker_turns = new Semaphore[100];
        Semaphore worker_mutex = new Semaphore(1);
        Semaphore controller_mutex = new Semaphore(0);

        for(int i=0;i<100;i++){
            worker_turns[i] = new Semaphore(1);
        }

        Worker.Setup(worker_mutex,controller_mutex,worker_turns);
        Controller controller = new Controller(worker_turns, controller_mutex, 500);
        for(int i=0;i<100;i++){
            Worker worker = new Worker(500,i);
        }
    }
}
