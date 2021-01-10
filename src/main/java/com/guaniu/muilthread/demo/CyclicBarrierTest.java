package com.guaniu.muilthread.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: guaniu
 * @Description: 测试CyclicBarrier
 * @Attention CyclicBarrier使用场景客户端请求限流（Guava RateLimiter）
 * @Date: Create in 0:26 2020/12/13
 * @Modified
 */
public class CyclicBarrierTest {
    static int TOTAL_THREAD_COUNT = 100;
    static int BARRIER_COUNT = 20;

    public static void main(String[] args) {
//        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable(){
//            @Override
//            public void run() {
//                System.out.println("满" + BARRIER_COUNT + "计数");
//            }
//        });

        CyclicBarrier barrier = new CyclicBarrier(BARRIER_COUNT, ()->{
            System.out.println("满" + BARRIER_COUNT + "计数");
        });

        for (int i = 0; i < TOTAL_THREAD_COUNT; i++){
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
