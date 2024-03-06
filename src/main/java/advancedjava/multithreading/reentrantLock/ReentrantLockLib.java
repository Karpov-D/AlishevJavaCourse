package advancedjava.multithreading.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Аналог ключевого слова synchronized
 */
public class ReentrantLockLib {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        task.showCounter();
    }
}

class Task {
    private int counter;
    Lock lock = new ReentrantLock();

    private void increment() {
        for(int i = 0; i < 1000000; i++) {
            counter++;
        }
    }

    public void firstThread() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void secondThread() {
        lock.lock(); // Если вызван метод lock из одного потока, остальные потоки ждут
        increment();
        lock.unlock();
    }

    public void showCounter() {
        System.out.println(counter);
    }
}