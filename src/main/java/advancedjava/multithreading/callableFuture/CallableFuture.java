package advancedjava.multithreading.callableFuture;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс Future решает проблему получения значения из потока и обработки исключений выброшенных в потоке
 */
public class CallableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Starting");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");

            Random random = new Random();
            int randomValue = random.nextInt();

            if(randomValue < 5) {
                throw new Exception("Something bad happened");
            }
            return random.nextInt(10);
        });

        executorService.shutdown();

        try {
            int result = future.get(); // метод get ждет окончания выполнения потока
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) { // ловим исключение выброшенное в потоке
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
