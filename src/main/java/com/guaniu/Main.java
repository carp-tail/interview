package com.guaniu;

import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 11:05 2020/12/10
 * @Modified by
 */
public class Main {
    static Object o = new Object();
    public static void main(String[] args){
        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("t1");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    System.out.println("t2");
                }
            }
        }.start();
    }
}
