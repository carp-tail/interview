package com.guaniu.muilthread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: guaniu
 * @Description: 测试原子操作类 AtomicInteger
 * 原子操作类的实现原理 就是 CAS(自旋锁)
 * 原子操作类通过 AtomicStampReference 解决ABA问题
 * @Attention:ABA问题对于基本类型数据不会有问题，但是对于引用类型数据内部的改变可能会影响后续的业务操作
 * @Date: Create in 17:23 2020/12/10
 * @Modified by
 */
public class AtomicIntegerTest {

    static int TOTAL_COUNT = 10000;
    int count1 = 0;
    AtomicInteger count2 = new AtomicInteger(0);
    int count3 = 0;
    public void m(){
        int n = TOTAL_COUNT;
        while (n > 0){
            count1++;
            count2.incrementAndGet(); // 乐观锁
            synchronized (this){ // 可能会申请重量级锁（悲观锁）
                count3++;
            }
            n--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest test = new AtomicIntegerTest();
        Thread thread1 = new Thread(test :: m);
        Thread thread2 = new Thread(test :: m);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count1(int): " + test.count1);
        System.out.println("count2(AtomicInteger): " + test.count2);
        System.out.println("count3(synchronized int): " + test.count3);
    }

}
