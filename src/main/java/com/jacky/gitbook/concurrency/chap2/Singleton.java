package com.jacky.gitbook.concurrency.chap2;

public class Singleton {
    private static Singleton instance = null; //懒汉模式
    //private static Singleton instance = new Singleton(); //饿汉模式

    private Singleton() {

    }

    public static synchronized Singleton newInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }
}
