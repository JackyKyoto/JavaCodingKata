package com.jacky.gitbook.concurrency.chap2;

/**
 * 就是说，当一个线程执行的代码出现异常的时候，其所持有的锁会自动释放，示例如下：
 */
public class SyncException {
    private int i = 0;

    public synchronized void operation() {
        while (true) {
            i++;
            System.out.println(Thread.currentThread().getName() + " , i= " + i);
            if (i == 10) {
                Integer.parseInt("a");
            }
        }
    }

    public static void main(String[] args) {
        final SyncException se = new SyncException();
        new Thread(new Runnable() {
            public void run() {
                se.operation();
            }
        }, "t1").start();
    }
}
