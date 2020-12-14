package com.guaniu.muilthread.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: Semaphore信号量测试
 * @Attention 可以用作限流
 * @Date: Create in 10:09 2020/12/14
 * @Modified
 */
public class SemaphoreTest {
    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(2); // 信号量，表示允许同时有两个线程占用
        Semaphore semaphore = new Semaphore(2, true);
        for (int i = 0; i < 10; i++){
            new Thread(()->{
                try {
                    semaphore.acquire(); // 获得许可
                    System.out.println(Thread.currentThread().getName() + "正在执行...");
                    TimeUnit.MILLISECONDS.sleep(200);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();  // 释放信号量
                    System.out.println(Thread.currentThread().getName() + "已释放");
                }
            },"线程" + i).start();
        }
    }
}
