package com.guaniu.sourcecode.string;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: StringBuilder 和 StringBuffer 测试
 * @Date: Create in 12:00 2020/12/19
 * @Modified
 */
public class StringBuilderAndBuffer {
    static int TOTAL_COUNT = 10000;

    static CountDownLatch latch;

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        // StringBuilder测试
        latch = new CountDownLatch(TOTAL_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < TOTAL_COUNT; i++){
            int finalI = i;
            new Thread(()->{
                stringBuilder.append("[ " + finalI + " ]");
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("StringBuilder：" + stringBuilder.toString());
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder耗时：" + (end - start) + "微秒！");


        // StringBuffer测试
        latch = new CountDownLatch(TOTAL_COUNT);
        start = System.currentTimeMillis();
        for (int i = 0; i < TOTAL_COUNT; i++){
            int finalI = i;
            new Thread(()->{
                stringBuffer.append("[ " + finalI + " ]");
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("StringBuffer：" + stringBuffer.toString());
        end = System.currentTimeMillis();
        System.out.println("StringBuffer耗时：" + (end - start) + "微秒！");

        // 在 TOTAL_COUNT 数值较小的情况下两者长度相同,数值较多的情况下,StringBuilder的长度不固定，而StringBuffer的长度则是固定长度
        // StringBuilder 长度会偏少与StringBuffer
        System.out.println("StringBuilder(" + stringBuilder.length() + ")和 StringBuffer(" + stringBuffer.length() + ") 长度是否相等：" + (stringBuilder.length() == stringBuffer.length()));

    }
}
