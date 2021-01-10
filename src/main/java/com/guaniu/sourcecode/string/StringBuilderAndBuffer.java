package com.guaniu.sourcecode.string;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: guaniu
 * @Description: StringBuilder 和 StringBuffer 测试
 *
 * @Attention： StringBuffer 内部所有的普通方法（出了构造方法及部分序列化相关的方法外）都直接或者间接地通过 synchronized 修饰了方法体
 *              直接地形式：直接在方法体外侧 使用 synchronized 进行修饰
 *              间接形式：1、通过重载的形式调用相应不同参数的【重载】方法；2、通过父类 AbstractStringBuilder 运行时的实际子类调用相应参数的【重写】；  这是java【多态性】的体现之一
 *
 *              synchronized 修饰的 StringBuffer 普通方法对当前实例对象进行了加锁，同一时刻只有一条线程对该实例【有操作实例对象加锁方法】的执行权限，这也是StringBuilder示例中每次执行长度不一致的原因
 *
 * @Question: 按道理说 StringBuffer 对实例对象进行了加锁，StringBuilder的运行速度应该比 StringBuffer更快才对，然而实际情况确是在数量非常多的情况下 StringBuilder耗时才较短，
 *            这应该跟 synchronized 的锁升级机制有关
 *
 * @Summary: 可以看到 String，StringBuilder 在多线程的环境下是不安全的，容易导致数据的丢失， StringBuffer是线程安全的
 *
 * @Date: Create in 12:00 2020/12/19
 * @Modified
 */

abstract class MyAbstractStringBuilder implements Appendable, CharSequence {
    char[] value; // 可以看到和String不同的是没有使用final修饰
}
public class StringBuilderAndBuffer {
    static int TOTAL_COUNT = 10000;

    static CountDownLatch latch;

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        final String[] string = {new String()};

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


        // String测试
        latch = new CountDownLatch(TOTAL_COUNT);
        start = System.currentTimeMillis();
        for (int i = 0; i < TOTAL_COUNT; i++){
            int finalI = i;
            new Thread(()->{
                // String 的话只能写成这种形式才能编译通过
                string[0] = string[0] + "[ " + finalI + " ]";
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("String：" + string[0]);
        end = System.currentTimeMillis();
        System.out.println("String(" + string[0].length() + ")耗时：" + (end - start) + "微秒！");
    }
}
