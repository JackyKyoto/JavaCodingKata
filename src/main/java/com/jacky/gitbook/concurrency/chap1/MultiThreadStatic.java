package com.jacky.gitbook.concurrency.chap1;

public class MultiThreadStatic {
    private static int num = 200;

    public static synchronized void printNum(String threadName, String tag) {
        if (tag.equals("a")) {
            num = num - 100;
            System.out.println(threadName + " tag a,set num over!");
        } else {
            num = num - 200;
            System.out.println(threadName + " tag b,set num over!");
        }
        System.out.println(threadName + " tag " + tag + ", num = " + num);
    }

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadStatic multiThread1 = new MultiThreadStatic();
        final MultiThreadStatic multiThread2 = new MultiThreadStatic();

        new Thread(() -> multiThread1.printNum("thread1", "a")).start();
        new Thread(() -> multiThread2.printNum("thread2", "b")).start();
    }
}
