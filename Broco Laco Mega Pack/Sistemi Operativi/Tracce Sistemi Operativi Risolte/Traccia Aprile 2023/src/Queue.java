public class Queue {
    private int[] queue;
    private int size = 0;
    private int max_size;

    public Queue(int max_size){
        this.max_size = max_size;
        queue = new int[max_size];
    }
    public boolean isEmpty(){return size == 0;}
    public boolean isFull(){return size == max_size;}
    public void enqueue(int k){
        if(!isFull()){
            queue[size] = k;
            size++;
        }
    }
    public int dequeue(){
        if(!isEmpty()){
            size--;
            return queue[size];
        }
        else return -1;
    }
}
