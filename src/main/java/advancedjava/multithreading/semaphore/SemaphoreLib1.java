package advancedjava.multithreading.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore ограничивает доступ к ресурсу
 */
public class SemaphoreLib1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        try {
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();

            System.out.println("All permits have been acquired");

            semaphore.acquire(); // не будет вызван, так как уже израсходован лимит semaphore

            System.out.println("Can not reach here...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release(); // освобождает ресурс (не будет вызван)

        System.out.println(semaphore.availablePermits());
    }
}
