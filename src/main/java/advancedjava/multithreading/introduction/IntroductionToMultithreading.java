package advancedjava.multithreading.introduction;

public class IntroductionToMultithreading {
    public static void main(String[] args) throws InterruptedException {
        // Первый способ создания потка
        MyThread myThread = new MyThread();
        //myThread.run(); // Не создает новый поток
        myThread.start(); //Создает новый поток

        MyThread myThread2 = new MyThread();
        myThread2.start(); //Потоки не синхронезированы, получают процессорное время случайным образом

        Thread.sleep(3000); //Главный поток main спит 3 сек

        System.out.println("Hello from main thread");

        // Второй способ создания потка
        Thread thread =  new Thread(new Runner());
        thread.start();
    }
}

// Первый способ создания потка
class MyThread extends Thread {
    public void run() {
        for(int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from thread" + i);
        }
    }
}

// Второй способ создания потка
class Runner implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from MyThread" + i);
        }
    }
}
