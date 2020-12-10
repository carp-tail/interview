package com.guaniu.muilthread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: 非同步方法的调用在同步方法写入的过程中可能会出现脏读，
 * 即同步方法的操作还没有执行完成，非同步方法已完成其写入数据的读出
 * @Date: Create in 15:21 2020/12/10
 * @Modified by
 */
public class DirtyReadTest {
    private String name;
    private double value;

    public DirtyReadTest(String name, double value) {
        this.name = name;
        this.value = value;
    }

    synchronized void syncWrite(String name, double value){
        this.name = name;
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    void noSyncRead(){
        System.out.println("NoSyncRead[name:" + this.name + ",value:" + this.value + "]");
    }

    synchronized void syncRead(){
        System.out.println("SyncRead[name:" + this.name + ",value:" + this.value + "]");
    }

    public static void main(String[] args){
        DirtyReadTest test = new DirtyReadTest("origin", 1.1);
        new Thread(() -> {
            test.syncWrite("current", 2.2);
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.noSyncRead(); // 读出来是name已改变，value还没改变（出现脏读）
        test.syncRead(); // 上面线程执行完之后才执行，读出来是name、value均已改变
    }
}
