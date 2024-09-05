public class MaxHelper{
    private int thread_num;
    private int max_id = -1;
    private int max = -1;
    private int count = 0;

    public MaxHelper(int thread_num){
        this.thread_num = thread_num;
    }
    public int getMaxId(){return max_id;}
    public int getMax(){return max;}
    public boolean isEvaluationOver(){return count == thread_num;}
    
    public void evaluate(int value, int thread_id){
        if((value > max) || (value == max && thread_id > max_id)){
            max = value;
            max_id = thread_id;
        }
        count++;
    }
}