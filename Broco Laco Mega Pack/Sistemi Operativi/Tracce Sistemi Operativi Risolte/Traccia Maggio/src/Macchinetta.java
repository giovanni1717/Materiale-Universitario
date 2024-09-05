public class Macchinetta{
    private int i = 0;

    public Macchinetta(){}
    public int GetSportello(){
        int result = -1;
        if(i % 3 == 0){
            result = 0;
        }
        else result = 1;
        i++;
        return result;
    }
}