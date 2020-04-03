package com.jacky.gitbook.concurrency.chap2;

/**
 * Synchronized 锁重入
 * 1、关键字 Synchronized 拥有锁重入的功能，也就是在使用 Synchronized 的时候，当一个线程得到一个对象的锁后，在该锁里执行代码的时候可以再次请求该对象的锁时可以再次得到该对象的锁。
 *
 * 2、也就是说，当线程请求一个由其它线程持有的对象锁时，该线程会阻塞，而当线程请求由自己持有的对象锁时，如果该锁是重入锁，请求就会成功，否则阻塞。
 * 代码 A 体现的那样，线程 T 在执行到method1（）内部的时候，由于该线程已经获取了该对象 syncDubbo 的对象锁，当执行到调用method2（） 的时候，会再次请求该对象的对象锁，如果没有可重入锁机制的话，由于该线程 T 还未释放在刚进入method1（） 时获取的对象锁，当执行到调用method2（） 的时候，就会出现死锁
 */
public class SyncDubbo {
    public synchronized void method1() {
        System.out.println("method1-----");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2-----");
        method3();
    }

    public synchronized void method3() {
        System.out.println("method3-----");
    }

    public static void main(String[] args) {
        final SyncDubbo syncDubbo = new SyncDubbo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDubbo.method1();
            }
        }).start();
    }
}
