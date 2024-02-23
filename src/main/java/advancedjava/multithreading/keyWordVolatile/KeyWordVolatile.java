package advancedjava.multithreading.keyWordVolatile;

import java.util.Scanner;

//Синхронизация потоков
public class KeyWordVolatile {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.shutdown();

    }
}

class MyThread extends Thread {
    private volatile boolean running = true; // Ключевое слово volatile решает проблему когерентности кэшей
    // необходимо использовать когда несколько потоков обращаются к одной переменной при запуске на многоядерном процесоре

    public void run() {
        while(running) {
            System.out.println("hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void shutdown() {
        this.running = false;
    }
}
