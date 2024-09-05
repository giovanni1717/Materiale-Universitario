public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Worker.Setup(10000);
        for(int i=0;i<10000;i++){
            Worker worker = new Worker(5);
        }
    }
}
