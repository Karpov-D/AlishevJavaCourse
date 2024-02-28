package advancedjava.multithreading.keyWordSynchronized;

/**
 * Неверный подход. потоки не синхронезированны, значение counter всегда разное
 */
public class KeyWordSynchronized1 {
    private int counter;
    public static void main(String[] args) throws InterruptedException {
        KeyWordSynchronized1 keyWordSynchronized1 = new KeyWordSynchronized1();
        keyWordSynchronized1.doWork();
    }

    private void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter++;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter++;
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
