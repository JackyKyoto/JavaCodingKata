package com.jacky.gitbook.concurrency.chap1;

public class MyThreadUnSafe extends Thread{
    private int count = 5;

    @Override
    public void run() {
        count--;
        System.out.println(currentThread().getName() + " count:" + count);
    }

    public static void main(String[] args) {
        MyThreadUnSafe myThread = new MyThreadUnSafe();
        Thread thread1 = new Thread(myThread, "thread1");
        Thread thread2 = new Thread(myThread, "thread2");
        Thread thread3 = new Thread(myThread, "thread3");
        Thread thread4 = new Thread(myThread, "thread4");
        Thread thread5 = new Thread(myThread, "thread5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
