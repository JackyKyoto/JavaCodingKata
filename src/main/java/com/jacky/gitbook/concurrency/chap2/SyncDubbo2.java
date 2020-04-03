package com.jacky.gitbook.concurrency.chap2;

/**
 *可重入锁支持在父子类继承的环境中，示例代码如下：
 */
public class SyncDubbo2 {
    static class Main {
        public int i = 5;
        public synchronized void operationSub() {
            i--;
            System.out.println("Main print i =" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main {
        public synchronized void operationSub() {
            while (i > 0) {
                i--;
                System.out.println("Sub print i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                Sub sub = new Sub();
                sub.operationSub();
            }
        }).start();
    }
}
