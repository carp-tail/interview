package com.guaniu.muilthread.solution;

import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: 唤醒线程
 *
 * @Attention: wait、sleep、join均可以相应中断，线程被唤醒且抛出InterruptedException
 *             正常运行的线程不会相应中断
 *
 * @Date: Create in 21:21 2020/12/31
 * @Modified
 */
public class WakeThreads {

    static Thread testThread;

    static Object object = new Object();

    // interrupt 唤醒 sleep
    static void test1(){
        testThread = new Thread(){
            @Override
            public void run() {
                System.out.println("test1(): sleep响应中断测试");
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("test1(): 捕获到中断异常");
                }
            }
        };

        testThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            testThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("test1(): 当前线程状态" + testThread.getState());
        }

    }

    // 唤醒 wait()
    static void test2(){
        testThread = new Thread(){
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("test2(): wait响应中断测试");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        System.out.println("test2(): 捕获到中断异常");
                    }
                }
            }
        };

        testThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            testThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("test2(): 当前线程状态" + testThread.getState());
        }
    }

    // test3 运行中相应中断测试
    static void test3(){
        testThread = new Thread(){
            @Override
            public void run() {
                System.out.println("test3(): 运行中响应中断测试");
                try {
                    while (true){

                    }
                } catch (Exception e) {
                    System.out.println("test3(): 捕获到异常" + e.getMessage());
                }
            }
        };

        testThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            testThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("test3(): 当前线程状态" + testThread.getState());
        }

    }

    // join 相应中断测试
    static void test4(){
        // 长期睡眠
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        testThread = new Thread(){
            @Override
            public void run() {
                System.out.println("test4(): join响应中断测试");
                thread.start();

                try {
                    thread.join();
                } catch (Exception e) {
                    System.out.println("test4(): 捕获到异常" );
                    e.printStackTrace();
                }

                System.out.println(45454);
            }
        };

        testThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            testThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("test4(): 当前线程状态" + testThread.getState());
        }

    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
