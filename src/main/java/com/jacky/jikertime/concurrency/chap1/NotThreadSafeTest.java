package com.jacky.jikertime.concurrency.chap1;


public class NotThreadSafeTest {
    private long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }
    public static long calc() throws InterruptedException {
        final NotThreadSafeTest test = new NotThreadSafeTest();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return test.count;
    }

    public static void main(String[] args) {
        try {
            long calc = NotThreadSafeTest.calc();
            System.out.printf("calc result is :"+calc);
            //实际结果在10000和2000之间的随机数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}