import java.util.ArrayList;

public class Queue {
    private ArrayList<Integer> queue;
    public int size;

    
    public Queue(int size){
        this.size = size;
        queue = new ArrayList<>(size);
    }
    public int size(){return queue.size();}
    public boolean isEmtpy(){return queue.isEmpty();}
    public boolean isFull(){return queue.size()==size;}
    public void enqueue(int k){
        if(!isFull()){
            queue.add(k);
        }
    }
    public int dequeue(){
        if(!isEmtpy()){
            return queue.remove(0);
        }
        return -1;
    }

}
