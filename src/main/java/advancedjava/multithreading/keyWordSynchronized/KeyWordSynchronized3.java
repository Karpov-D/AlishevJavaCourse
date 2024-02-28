package advancedjava.multithreading.keyWordSynchronized;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Верный подход. потоки синхронезированны
 * Решение задачи через AtomicInteger
 */
public class KeyWordSynchronized3 {
    private AtomicInteger counter = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        KeyWordSynchronized3 keyWordSynchronized1 = new KeyWordSynchronized3();
        keyWordSynchronized1.doWork();
    }

    private void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter.incrementAndGet();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); //метот join нужен что бы главный поток ждал окончания потока thread1
        thread2.join(); //главный поток ждет окончания потока thread2
        System.out.println(counter);
    }
}
