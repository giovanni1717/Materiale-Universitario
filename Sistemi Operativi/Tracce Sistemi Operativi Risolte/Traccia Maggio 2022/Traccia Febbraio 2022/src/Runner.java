public class Runner extends Thread{
    private static Semaphore match_mutex;
    private Semaphore judge_mutex;
    private static int is_race_finished = 0;
    public static long winner_id;
    
    public Runner(Semaphore judge_mutex, Semaphore match){
        this.judge_mutex = judge_mutex;
        match_mutex = match;
        this.start();
    }
    public void run(){
        match_mutex.down();
        match_mutex.up();
        for(int i=0;i<10000;i++){
            i = i + 2;
            i = i - 2;
        }
        match_mutex.down();
        if(is_race_finished == 0){
            is_race_finished = 1;
            winner_id = getId();
            judge_mutex.up();
        }
        match_mutex.up();
    }
}
