import java.util.ArrayList;
import java.util.Random;

public class Lister {
    private int k;
    private ArrayList<Integer> list = new ArrayList<>();
    private boolean shuffle = false;
    private Random rand = new Random();

    public Lister(int k){
        this.k = k;
        for(int i=1;i<k;i++){
            list.add(i);
        }
    }
    public void unshuffle(){
        ArrayList<Integer> new_list = new ArrayList<>();
        for(int i=0;i<k;i++){
            new_list.add(i);
        }
        list = new_list;
    }
    public void shuffle(){
        Integer rand_int;
        ArrayList<Integer> new_list = new ArrayList<>();
        for(int i=0;i<k;i++){
            do{
                rand_int = rand.nextInt(k);
            }while(new_list.contains(rand_int));
            new_list.add(rand_int);
        }
        list = new_list;
    }

    public Integer getNext(){
        if(!list.isEmpty()){
            Integer i = list.remove(0);
            return i;
        }
        else{
            shuffle = !shuffle;
            if(shuffle){
                shuffle();
            }
            else{
                unshuffle();
            }
            Integer i = list.remove(0);
            return i;
        }
    }

}
