package advancedjava.multithreading.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // Указываем необходимое колличество потоков

        for(int i = 0; i < 5; i++) {
            executorService.submit(new Work(i));
        }
        executorService.shutdown(); // метод завершает прием заданий в  executorService
        // после этого метода потоки начинают выполнение
        System.out.println("All tasks submitted");

        executorService.awaitTermination(1, TimeUnit.DAYS); //Метод  ждет 1 день или пока не закончатся все потоки
        // затем передает выполнение главному потоку
    }

}

class Work implements Runnable {
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was completed");
    }
}
