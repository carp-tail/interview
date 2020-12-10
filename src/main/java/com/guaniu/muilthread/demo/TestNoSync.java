package com.guaniu.muilthread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: 测试同步方法的执行过程中对其他同步方法与非同步方法调用的影响
 * 结论：1、同步方法执行过程中，对用同一对象的同步方法执行是有影响的；
 *      2、同步方法的执行过程中，对同一对象的非同步方法执行是没有影响的
 * @Date: Create in 15:09 2020/12/10
 * @Modified by
 */
class MyTest {
    synchronized void test1(){
        System.out.println("test1 start...");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1 end...");
    }
    synchronized void test2(){
        System.out.println("test2 start...");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 end...");
    }
    void test3(){
        System.out.println("test3 start...");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test3 end...");
    }
}
public class TestNoSync {
    public static void main(String[] args){
        MyTest myTest = new MyTest();
        new Thread(myTest :: test1).start(); // java 8 提供了“::”用作方法引用
//        new Thread(() -> {myTest.test1();}).start(); // 等同于
        new Thread(myTest :: test2).start();
        new Thread(myTest :: test3).start();
    }
}
