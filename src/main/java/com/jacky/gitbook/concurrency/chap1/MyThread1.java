package com.jacky.gitbook.concurrency.chap1;

public class MyThread1 extends Thread{
    @Override
    public void run() {
        while (true) {
            System.out.println(currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread1 thread = new MyThread1();
        thread.start(); //线程启动的正确方式
    }
}
