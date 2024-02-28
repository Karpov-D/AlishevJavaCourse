package advancedjava.multithreading.keyWordSynchronized;

/**
 * Верный подход. потоки синхронезирован
 * Решение задачи с помощью synchronized блока
 */
public class KeyWordSynchronized4 {
    private int counter;
    public static void main(String[] args) throws InterruptedException {
        KeyWordSynchronized4 keyWordSynchronized1 = new KeyWordSynchronized4();
        keyWordSynchronized1.doWork();
    }

    /**
     * Добавили synchronized блок
     */
    public void increment() {
        synchronized (this) { //в synchronized блок передаем объект на котором не обходимо синхронезироваться
            counter++;
        }
    }

    private void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
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
