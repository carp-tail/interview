package com.guaniu.muilthread.solution;

/**
 * @Author: guaniu
 * @Description: 问题怎么实现一个线程安全的计数器
 * @Date: Create in 16:26 2020/12/22
 * @Modified
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用 CAS 实现线程安全的计数器
 */
public class Counter {

    private AtomicInteger safeNum = new AtomicInteger(0);
    private int unSafeNum = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++){
            threads.add(new Thread(()->{
                for (int j = 0; j < 10000; j++){
                    counter.count();
                    counter.safeCount();
                }
            }));
        }
        for (Thread t : threads){
            t.start();
        }

        // 等待所有线程执行完毕
        for (Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("期望数值（100*10000）：" + (100 * 10000));
        System.out.println("unSafeNum：" + counter.unSafeNum);
        System.out.println("safeNum" + counter.safeNum);
        System.out.println(System.currentTimeMillis() - start);
    }

    /*基于CAS实现的计数器*/
    /**
     * 整个方法体可以使用 "safeNum.incrementAndGet();" 代替
     */
    private void safeCount(){
        while (true){
            int num = safeNum.get();
            boolean suc = safeNum.compareAndSet(num, num + 1); // 比较是否等于期望值
            if (suc){
                break;
            }
        }
    }

    /*非线程安全的计数器*/
    private void count(){
        unSafeNum++;
    }
}
