package com.jacky.gitbook.concurrency.chap2;

public class DoubleLockSingleton {
    private static volatile DoubleLockSingleton instance;

    public static DoubleLockSingleton getInstance(){
        if(instance == null){
            try {
                //模拟初始化对象的准备时间...
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //类上加锁，表示当前对象不可以在其他线程的时候创建
            synchronized (DoubleLockSingleton.class) {
                //如果不加这一层判断的话，这样的话每一个线程会得到一个实例
                //而不是所有的线程的到的是一个实例
                if(instance == null){
                    instance = new DoubleLockSingleton();
                }
            }
        }
        return instance;
    }
}
