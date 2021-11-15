import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Server {
    private final List<Integer> integers = new ArrayList<>();
    public final Thread monitor = new Thread();
    public static void main(String[] args) {
        Server server = new Server();



        Thread thread1 = new Thread(new MyTask(0,server));
        Thread thread2 = new Thread(new MyTask(1,server));

        thread1.start();
        thread2.start();

        try {
           thread1.join();
           thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.show();
    }

    private void show() {
        String array = integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println(array);
    }


    public void addNumber(int i) {

        integers.add(i);

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }
}