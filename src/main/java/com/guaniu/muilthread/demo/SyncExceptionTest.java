package com.guaniu.muilthread.demo;

/**
 * @Author: guaniu
 * @Description: 同步方法、代码块执行过程中出现异常的时候会释放锁
 * @Date: Create in 16:20 2020/12/10
 * @Modified by
 */
public class SyncExceptionTest {
    int count = 0;
    synchronized void m1(){
        while (count < 10){
            System.out.println("count:" + count);
            count++;
        }
        try {
            throw new NullPointerException();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("m1抛出异常，锁释放！");
        }

    }
    synchronized void m2(){
        System.out.println("m2执行！");
    }
    public static void main(String[] args){
        SyncExceptionTest test = new SyncExceptionTest();
        new Thread(test :: m1).start();
        new Thread(test :: m2).start();
    }
}
