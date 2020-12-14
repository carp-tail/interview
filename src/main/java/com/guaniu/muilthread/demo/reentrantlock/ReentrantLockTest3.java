package com.guaniu.muilthread.demo.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: guaniu
 * @Description: ReentrantLock 有两种锁【公平锁】和【非公平锁】
 * @Attention: ReentrantLock 默认非公平锁
 * @Date: Create in 10:41 2020/12/12
 * @Modified
 */
public class ReentrantLockTest3 {
    ReentrantLock lock;

    void m1(){
        lock = new ReentrantLock(true); // 公平锁
        test();
    }

    void m2(){
        lock = new ReentrantLock(false); // 非公平锁
        test();
    }
    void m3(){
        lock = new ReentrantLock(); // 默认非公平锁
        test();
    }

    void test(){
        for (int i = 1; i <= 100; i++){
            new Thread(()->{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁...");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            },"线程" + i).start();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest3 test = new ReentrantLockTest3();
        test.m1();
//        test.m2();
//        test.m3();
    }
}
