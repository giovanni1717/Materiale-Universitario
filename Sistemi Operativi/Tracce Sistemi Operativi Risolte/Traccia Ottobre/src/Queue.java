public class Queue {
    private int capacity;
    private int size = 0;
    private int[] queue;
    private int nextCount;

    public Queue(int capacity){
        this.capacity = capacity;
        queue = new int[capacity];
    }
    public boolean isEmpty(){return size == 0;}
    public boolean isFull(){return size == capacity;}
    public int size(){return size;}

    public void enqueue(int k, int count){
        if(!isFull()){
            queue[size] = k;
            size++;
            nextCount = count;
        }
        else System.out.println("Queue full...");
    }
    public int dequeue() throws Exception{
        if(!isEmpty()){
            size--;
            return queue[size];
        }
        else System.out.println("Queue empty...");
        throw new Exception();
    }
    public int getNextCount(){return nextCount;}
}
