public class Semaphore {

    public int value, sospesi;
  
    public Semaphore (int v) {
      value=v; sospesi=0;}
  
    public synchronized void down () {
       if (value==0) {
           sospesi++;
           //System.out.println("sospesi:"+sospesi);
           try { wait();}
           catch (InterruptedException e) 
           {System.out.println(e.getMessage());}
           sospesi--;}
           else {
            value--;}
    }
  
    public synchronized void up () {
      if (sospesi>0)
           { notify(); }
       else{
       value++;
       }
    }
    
    }