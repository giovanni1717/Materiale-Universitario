import java.util.ArrayList;

public class Queue{
    private ArrayList<Integer> queue = new ArrayList<>();
    private int size_limit;

    public Queue(int size_limit){this.size_limit = size_limit;}
    public int size(){return queue.size();}
    public boolean isEmpty(){return queue.isEmpty();}
    public boolean isFull(){return queue.size() == size_limit;}
    public Integer insert(Integer i){
        if(!isFull()){
            queue.add(i);
            return i;
        }
        else System.out.println("INSERTION ERROR");
        return -1;
    }
    public Integer remove(){
        Integer i = queue.remove(0);
        return i;
    }
}
