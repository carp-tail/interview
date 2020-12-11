package com.guaniu.muilthread.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: guaniu
 * @Description: 测试效率
 * 1、long
 * 2、synchronized long
 * 3、AtomicLong
 * 4、LongAddr
 * @Date: Create in 17:23 2020/12/10
 * @Modified by
 */
public class AtomicAndLongAdderTest {

    static int THREAD_COUNT = 10000;
    static int TOTAL_COUNT = 10000;

    Long count1 = 0l;
    Long count2 = 0l;
    AtomicLong count3 = new AtomicLong(0);
    LongAdder count4 = new LongAdder(); /*@Attention类似分段锁（线程数量多的情况下比AtomicLong更有优势）*/
    Object o = new Object();

    public static void main(String[] args){
        System.out.println("理想情况下，值应该为：" + THREAD_COUNT + "*" + TOTAL_COUNT + "=" + (THREAD_COUNT * TOTAL_COUNT));
        AtomicAndLongAdderTest test = new AtomicAndLongAdderTest();
        test.test1();
        test.test2();
        test.test3();
        test.test4();
    }

    void test1(){
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < TOTAL_COUNT; j++){
                        count1++;
                    }
                    latch.countDown();
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("无锁Long花费时间：" + (end - start) + "，值：" + count1 + "。");
    }

    void test2(){
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < TOTAL_COUNT; j++){
                        synchronized (o){
                            count2++;
                        }
                    }
                    latch.countDown();
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("加锁（synchronized）Long花费时间：" + (end - start) + "，值：" + count2);
    }

    void test3(){
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < TOTAL_COUNT; j++){
                        count3.incrementAndGet();
                    }
                    latch.countDown();
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("AtomicLong花费时间：" + (end - start) + "，值：" + count3);
    }

    void test4(){
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < TOTAL_COUNT; j++){
                        count4.add(1l);
                    }
                    latch.countDown();
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("LongAdder花费时间：" + (end - start) + "，值：" + count4 + "。");
    }
}
