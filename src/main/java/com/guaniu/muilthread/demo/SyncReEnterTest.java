package com.guaniu.muilthread.demo;

/**
 * @Author: guaniu
 * @Description: synchronized 是可重入锁
 * @Date: Create in 15:44 2020/12/10
 * @Modified by
 */

class Father{
    public synchronized void m1(){
        System.out.println("SuperClass m1()!");
    }
}
class Son extends Father{
    @Override
    public synchronized void m1() {
        super.m1();
        System.out.println("ReEnter SuperClass m1() ReEntered!");
        this.m2();
        System.out.println("ReEnter SonClass m2() ReEntered!");
    }

    public synchronized void m2(){
        System.out.println("SonClass m2()!");
    }
}
public class SyncReEnterTest {
    public static void main(String[] args){
        Son son = new Son();
        new Thread(son :: m1).start();
    }
}
