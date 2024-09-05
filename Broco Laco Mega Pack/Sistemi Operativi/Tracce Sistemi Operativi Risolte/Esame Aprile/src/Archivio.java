public class Archivio {
    public int[] archive;
    public static int count = 0;

    public Archivio(int n,int k){
        if(n < 2*k) archive = new int[n];
        else{
            archive = new int[2*k - 1];
        }
    }
    public void manage(){
        archive[count - 1] = -1;
        count--;
        System.out.println("Managed with count: " + count);
    }
    public void insert(){
        archive[count] = 1;
        count++;
        System.out.println("Inserted with count: " + count);
    }

}
