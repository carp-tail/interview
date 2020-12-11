package com.guaniu.muilthread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 17:23 2020/12/10
 * @Modified by
 */
public class VolitaleTest {
    /*volatile*/ boolean running = true;
    public void m(){
        System.out.print("正在执行");
        while (running){
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//                System.out.print(".");
//                TimeUnit.MILLISECONDS.sleep(100);
//                System.out.print(".");
//                TimeUnit.MILLISECONDS.sleep(100);
//                System.out.println(".");
//                TimeUnit.MILLISECONDS.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolitaleTest test = new VolitaleTest();
        new Thread(test :: m).start();
        TimeUnit.SECONDS.sleep(1);
        test.running = false;
    }
}
