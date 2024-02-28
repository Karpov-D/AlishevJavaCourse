package advancedjava.multithreading.keyWordSynchronized;

/**
 * Верный подход. потоки синхронезированны
 */
public class KeyWordSynchronized2 {
    private int counter;
    public static void main(String[] args) throws InterruptedException {
        KeyWordSynchronized2 keyWordSynchronized1 = new KeyWordSynchronized2();
        keyWordSynchronized1.doWork();
    }

    /**
     * Добавили ключевое слово synchronized
     *
     * Ключевое слово synchronized может быть добавлено только к методам,
     * поэтому вынесли оперпцию инкремента в отдельный метод
     */
    public synchronized void increment() {
        counter++;
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
