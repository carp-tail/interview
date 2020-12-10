package com.guaniu.muilthread;

/**
 * @Author: guaniu
 * @Description: 三个线程循环打印 A B C 固定次数
 * @Date: Create in 10:21 2020/12/10
 * @Modified by
 */
public class PrintABC1 {
    static int TOTAL_COUNT = 20; // 总共打印 20 次
    static String flag = "A";
    static int count = 0; // 已打印的数量
    public static void main(String[] args){
        Thread threadA = new Thread(){
            @Override
            public void run() {
                while (count < 20){ // 检验已打印是否小于20次
                    synchronized (flag){
                        // 需要对 flag 进行加锁 同时对 count 进行双重检查
                        if (count < 20 && "A".equals(flag)){
                            System.out.println(flag);
                            flag = "B";
                        }
                    }
                }
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                while (count < 20){ // 检验已打印是否小于20次
                    synchronized (flag){
                        // 需要对 flag 进行加锁 同时对 count 进行双重检查
                        if (count < 20 && "B".equals(flag)){
                            System.out.println(flag);
                            flag = "C";
                        }
                    }
                }
            }
        };
        Thread threadC = new Thread(){
            @Override
            public void run() {
                while (count < 20){ // 检验已打印是否小于20次
                    synchronized (flag){
                        // 需要对 flag 进行加锁 同时对 count 进行双重检查
                        if (count < 20 && "C".equals(flag)){
                            System.out.println(flag);
                            flag = "A";
                            count ++;
                        }
                    }
                }
            }
        };
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
