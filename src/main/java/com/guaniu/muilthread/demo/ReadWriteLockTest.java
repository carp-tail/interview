package com.guaniu.muilthread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 9:38 2020/12/13
 * @Modified
 */
public class ReadWriteLockTest {

    static void read(Lock lock, String name){
        lock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(name + "read over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    static void write(Lock lock, String name){
        lock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(name + "write over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

//        Lock lock = new ReentrantLock();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        for (int i = 0; i < 5; i++){
            new Thread(()->{
//                read(lock, Thread.currentThread().getName());
                read(readWriteLock.readLock(), Thread.currentThread().getName());
            },"线程1").start();
        }

        for (int i = 0; i < 5; i++){
            new Thread(()->{
//                write(lock, Thread.currentThread().getName());
                write(readWriteLock.writeLock(), Thread.currentThread().getName());
            },"线程1").start();
        }
    }
}
