package com.guaniu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 11:05 2020/12/10
 * @Modified by
 */

public class Main {
    public static void main(String[] args){
        Lock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();

        }).start();
    }
}
