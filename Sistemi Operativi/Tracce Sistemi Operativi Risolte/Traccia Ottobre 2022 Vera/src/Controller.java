public class Controller extends Thread{
    private Semaphore[] worker_turns;
    private Semaphore controller_mutex;
    private int k;

    public Controller(Semaphore[] worker_turns, Semaphore controller_mutex, int k){
        this.worker_turns = worker_turns;
        this.controller_mutex = controller_mutex;
        this.k = k;
        this.start();
    }
    public void run(){
        for(int i=0;i<k;i++){
            controller_mutex.down();
            Worker.n_work = worker_turns.length;
            System.out.println("Controller reset n_work to " + worker_turns.length);
            for(int j=0;j<worker_turns.length;j++){
                worker_turns[j].up();
            }
        }
    }

}
