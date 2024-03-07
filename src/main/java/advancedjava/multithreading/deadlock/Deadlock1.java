package advancedjava.multithreading.deadlock;

import java.util.Random;

/**
 * Программа переводит деньги с одного аккаунта на другой и обратно в нескольких потоах
 * Верное решение с использованием блока synchronize
 * */

public class Deadlock1 {
    public static void main(String[] args) throws InterruptedException {
        Runner1 runner = new Runner1();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}

class Runner1 {
    private Account account1 = new Account();
    private Account account2 = new Account();

    public void firstThread() {
        Random random = new Random();

        for(int i = 0; i < 10000; i++) {
            synchronized (account1) { //синхронезируемся сразу на двух объектах аккаунт для выпонения перевода
                synchronized (account2) {
                    Account.transfer(account1, account2, random.nextInt(100));
                }
            }
        }
    }

    public void secondThread() {
        Random random = new Random();

        for(int i = 0; i < 10000; i++) {
            synchronized (account1) { //синхронезируемся сразу на двух объектах аккаунт для выпонения перевода
                synchronized (account2) {
                    Account.transfer(account2, account1, random.nextInt(100));
                }
            }
        }
    }

    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
    }

}

class Account1 {

    private int balance = 10000;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}



