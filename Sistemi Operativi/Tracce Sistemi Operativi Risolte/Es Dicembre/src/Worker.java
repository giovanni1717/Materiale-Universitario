public class Worker extends Thread{
    private Semaphore personal_mutex;
    private static Semaphore[] worker_mutexes;
    private static MaxHelper helper;
    private static int[] thread_ids;
    private int k;
    private int id;
    private static int count;

    public Worker(int k,int id,Semaphore personal_mutex){
        this.personal_mutex = personal_mutex;
        this.k = k;
        this.id = id;
        System.out.println("Worker " + id + " has k = " + k);
        this.start();
    }
    public static void setup(int thread_num,int starting_point, int[] ids, Semaphore[] mutexes){
        helper = new MaxHelper(thread_num);
        thread_ids = ids;
        worker_mutexes = mutexes;
        count = starting_point;
    }

    public void run(){
        while(true){
            personal_mutex.down();
            if(!helper.isEvaluationOver()){
                helper.evaluate(k,id);
                System.out.println("[EVAL] Thread " + id + " ha inviato i suoi dati...");
                if(helper.isEvaluationOver()){
                    int maxId = helper.getMaxId();
                    for(int i=0;i<thread_ids.length;i++){
                        if(maxId == thread_ids[i]){
                            count = i;
                            break;
                        }
                    }
                    System.out.println("[ELAB] Thread " + id + " ha ottenuto i risultati: inizia " + count);
                }else{
                    count = (count + 1) % thread_ids.length;
                }
            }
            else{
                System.out.println("[WORK] Thread " + id + " sta girando in Round Robin...");
                count = (count + 1) % worker_mutexes.length;
            }
            worker_mutexes[count].up();
        }
    }
}
