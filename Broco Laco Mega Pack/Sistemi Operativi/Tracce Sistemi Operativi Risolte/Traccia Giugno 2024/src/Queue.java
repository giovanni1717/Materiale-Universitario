import java.util.ArrayList;

public class Queue {
    public ArrayList<Integer> queue;
    public int size;
    public int n = 0;
    public int m = 0;

    public Queue(int size){
        queue = new ArrayList<>(size);
        this.size = size;
    }
    public boolean isFull(){return queue.size()==size;}
    public boolean isEmpty(){return queue.size()==0;}
    public int size(){return queue.size();}
    public int enqueue(int k){
        if(!isFull()){
            queue.add(k);
            m++;
            return k;
        }
        return -1;
    }
    public int dequeue(){
        if(!isEmpty()){
            n++;
            return queue.remove(0);
        }
        return -1;
    }
}
