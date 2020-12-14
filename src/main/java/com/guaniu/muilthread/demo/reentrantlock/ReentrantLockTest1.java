package com.guaniu.muilthread.demo.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: guaniu
 * @Description: ReentrantLock 测试
 * @Date: Create in 0:24 2020/12/12
 * @Modified
 */
public class ReentrantLockTest1 {

    ReentrantLock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            System.out.println("m1获取锁...");
            m2();
            for (int i = 1; i <= 3; i++){
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("m1 ---> " + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("m1释放锁...");
        }
    }

    /**
     * @Attention 1、ReentrantLock是可重入锁；2、lock()写在try代码块中，unlock()写在finally代码块中
     */
    void m2(){
        try {
            lock.lock();
            System.out.println("m2重新获取锁...");
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("m2释放锁...");
        }
    }

    void m3(){
        try {
            lock.lock();
            System.out.println("m3获取锁...");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("m3释放锁...");
        }
    }

    /**
     * @Attention tryLock() 尝试获取锁，不论是否获取锁后面的代码都会执行
     * 获取到锁的情况下需要释放锁
     */
    void m4(){
        boolean locked = false;
        try {
            locked = lock.tryLock(); // 尝试获取锁
            if (locked){
                System.out.println("m4尝试获取锁成功...");
            }else {
                System.out.println("m4尝试获取锁失败...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (locked){
                lock.unlock();
                System.out.println("m4释放锁...");
            }
        }
    }

    /**
     * @Attention TryLock(Time) 尝试获取锁可以指定时间，会抛出异常
     */
    void m5(){
        boolean locked = false;
        try {
            locked = lock.tryLock(2, TimeUnit.SECONDS); // 尝试获取锁
            if (locked){
                System.out.println("m5尝试获取锁成功...");
            }else {
                System.out.println("m5尝试获取锁失败...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (locked){
                lock.unlock();
                System.out.println("m5释放锁...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest1 test = new ReentrantLockTest1();
        new Thread(test :: m1).start();
        new Thread(test :: m3).start();
        new Thread(test :: m4).start(); // 此时锁还被第一个线程占用，获取失败
        new Thread(test :: m5).start(); // 此时锁还被第一个线程占用，获取失败

        TimeUnit.SECONDS.sleep(5);
        new Thread(test :: m5).start();
        new Thread(test :: m4).start(); // 此时锁被上一线程占用2秒，获取失败

        TimeUnit.SECONDS.sleep(3);
        new Thread(test :: m4).start(); // 获取成功

    }
}
