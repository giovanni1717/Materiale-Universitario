public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Semaphore judge_mutex = new Semaphore(0);
        Semaphore match_mutex = new Semaphore(0);

        Runner runner1 = new Runner(judge_mutex,match_mutex);
        Runner runner2 = new Runner(judge_mutex,match_mutex);

        Judge judge = new Judge(judge_mutex, match_mutex);
    }
}
