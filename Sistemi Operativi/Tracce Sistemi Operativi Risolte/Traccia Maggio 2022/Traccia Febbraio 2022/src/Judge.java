public class Judge extends Thread{
    private Semaphore judge_mutex;
    private Semaphore match_mutex;

    public Judge(Semaphore judge_mutex,Semaphore match_mutex){
        this.judge_mutex = judge_mutex;
        this.match_mutex = match_mutex;

        this.start();
    }

    public void run(){
        match_mutex.up();
        judge_mutex.down();
        match_mutex.down();
        System.out.println("Winner is " + Runner.winner_id);
        match_mutex.up();
    }
}
