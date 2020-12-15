package com.guaniu.muilthread.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: guaniu
 * @Description: 淘宝面试题：实现一个容器，提供两个方法 add 和 size
 *               写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个是，线程2提示并结束
 * @Date: Create in 23:57 2020/12/14
 * @Modified
 */

class MyCollection<T>{
    List<T> list = new ArrayList<T>();

    void add(T t){
        list.add(t);
    }

    int size(){
        return list.size();
    }
}
public class FiveContentStop {
    public static void main(String[] args) {
        MyCollection collection = new MyCollection();
        /**
         * 方法1
         */
//        Thread thread1 = new Thread(()->{
//            for (int i = 1; i <= 10; i++){
//                synchronized (collection){
//                    collection.add(new Object());
//                    System.out.println(Thread.currentThread().getName() + "添加第" + i + "个元素...");
//                }
//                try {
//                    TimeUnit.SECONDS.sleep(1); // 1、需要睡眠；2、放在synchronized代码块外面才能释放锁
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "线程1");
//
//        Thread thread2 = new Thread(()->{
//
//            while (true){
//                synchronized (collection){
//                    if (collection.size() == 5){
//                        System.out.println(Thread.currentThread().getName() + "结束");
//                        break;
//                    }
//                }
//            }
//        }, "线程2");

        /**
         * 方法2
         */

//        Thread thread1 = new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int i = 1; i <= 10; i++){
//                synchronized (collection){
//                    collection.add(new Object());
//                    System.out.println(Thread.currentThread().getName() + "添加第" + i + "个元素...");
//                    if (collection.size() == 5){ // 这里其实不是线程2在监听
//                        collection.notify(); // 唤醒线程2，但还没有释放锁
//                        try {
//                            collection.wait(); // 释放锁
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        },"线程1");
//
//        Thread thread2 = new Thread(()->{
//            System.out.println(Thread.currentThread().getName() + "启动");
//            synchronized (collection){
//                try {
//                    collection.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "结束");
//                collection.notify();
//            }
//        }, "线程2");


        /**
         * 方法3
         */

        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "启动");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "线程2");

        Thread thread1 = new Thread(()->{
            for (int i = 1; i <= 10; i++){
                collection.add(new Object());
                System.out.println(Thread.currentThread().getName() + "添加第" + i + "个元素...");
                if (collection.size() == 5){
                    LockSupport.unpark(thread2);
                }
            }
        },"线程1");



        thread1.start();
        thread2.start();
    }
}
