package com.guaniu.muilthread.demo.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: guaniu
 * @Description: ReentrantLock测试
 * @Date: Create in 10:17 2020/12/12
 * @Modified
 */
public class ReentrantLockTest2 {

    ReentrantLock lock = new ReentrantLock();

    void m1(){
        try {
            System.out.println("线程【" + Thread.currentThread().getName() + "】调用 lock() 方法...");
            lock.lock();
            System.out.println("线程【" + Thread.currentThread().getName() + "】获取到锁，将长时间睡眠...");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("线程【" + Thread.currentThread().getName() + "】调用 lock() 方法...");
            lock.unlock();
            System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁。");
        }
    }

    void m2(){
        boolean locked = false;
        try {
            System.out.println("线程【" + Thread.currentThread().getName() + "】调用 tryLock() 方法...");
            locked = lock.tryLock();
            System.out.println("线程【" + Thread.currentThread().getName() + "】是否获取到锁：" + (locked ? "是" : "否"));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (locked){
                System.out.println("线程【" + Thread.currentThread().getName() + "】调用 lock() 方法...");
                lock.unlock();
                System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁。");
            }
        }
    }

    void m3(){
        try {
            System.out.println("线程【" + Thread.currentThread().getName() + "】调用 lockInterruptibly() 方法...");
            lock.lockInterruptibly();
            System.out.println("线程【" + Thread.currentThread().getName() + "】获取到锁。");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁。");
        }
    }



    void m4(){
        try {
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public static void main(String[] args) {
        ReentrantLockTest2 test = new ReentrantLockTest2();
        new Thread(test :: m1,"线程1").start();
        new Thread(test :: m2,"线程2").start();
        new Thread(test :: m3,"线程3").start();
    }
}
