package com.guaniu.muilthread.demo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 10:31 2020/12/14
 * @Modified
 */
public class ExchangerTest {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(()->{
            String s = "A";
            try {
                s = exchanger.exchange(s); // 线程在这里阻塞等待交换
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            System.out.println(Thread.currentThread().getName() + "：" + s);
        },"A线程").start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(()->{
            String s = "B";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            System.out.println(Thread.currentThread().getName() + "：" + s);
        },"B线程").start();
    }
}
