package com.guaniu.muilthread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: guaniu
 * @Description: LockSupport 类测试
 * @Attention: 1. LockSupport 可以任意使线程等待    park(),在线程内使用
 *             2. LockSupport 可以唤醒指定线程     unpark(Thread),在线程外使用
 *             3. unpark() 可以先于park() 调用
 * @Date: Create in 23:37 2020/12/14
 * @Modified
 */
public class LockSupportTest {
    public static void main(String[] args){
        Thread thread = new Thread(()->{
            for (int i = 1; i <= 10; i++){
                System.out.println(i);
//                if (i == 5){
//                    System.out.println("哦~在这停顿！");
//                    LockSupport.park();
//                }
            }
        });
        thread.start();
//        LockSupport.unpark(thread);

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("继续开始！");
//        try {
//            TimeUnit.MILLISECONDS.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        LockSupport.unpark(thread);
    }
}
