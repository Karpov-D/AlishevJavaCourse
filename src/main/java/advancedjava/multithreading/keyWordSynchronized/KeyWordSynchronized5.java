package advancedjava.multithreading.keyWordSynchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задача показывает как можно ускорить выполнеие програмы с помощью synchronized блока
 * в классе KeyWordSynchronized5 показано медленное выполнение программы с использованием
 * ключевого слова synchronized
 */
public class KeyWordSynchronized5 {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.main();
    }

}

class Worker {
    Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public synchronized void addToList1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public synchronized void addToList2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void work()  {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void main() throws InterruptedException {
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after  - before) + " ms to run");
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }

}
