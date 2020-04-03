package com.jacky.gitbook.concurrency.chap1;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("123");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable, "t1");
        thread.start();
    }
}