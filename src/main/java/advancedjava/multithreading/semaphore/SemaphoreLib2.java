package advancedjava.multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore ограничивает доступ к ресурсу
 */
public class SemaphoreLib2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        Connection connection =  Connection.getConnection();
        for(int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

/**
 * Реализация паттерна Singleton
 */
class Connection {
    private static Connection connection = new Connection();
    private int connectionCount;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println(connectionCount);
        }
        Thread.sleep(1000);

        synchronized (this) {
            connectionCount--;
        }
    }
}
