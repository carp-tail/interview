package com.guaniu.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guaniu
 * @Description: 存在内存泄漏，可能溢出
 * @Date: Create in 14:44 2020/12/18
 * @Modified
 */

/**
 * @Attention
 * 1.Xms和Xmx设置的值相同，防止内存抖动
 */

// VM options: -Xms20m -Xmx20m -XX:+PrintGC
//[GC (Allocation Failure)  5632K->1186K(19968K), 0.0018563 secs]           【Allocation Failure】年轻代分配不下，进行回收
//[GC (Allocation Failure)  6818K->3943K(19968K), 0.0062296 secs]
//[GC (Allocation Failure)  9575K->8391K(19968K), 0.0044013 secs]
//[GC (Allocation Failure)  14023K->12276K(19968K), 0.0104551 secs]
//[Full GC (Ergonomics)  12276K->11766K(19968K), 0.1100232 secs]
//[Full GC (Ergonomics)  17398K->15075K(19968K), 0.0892889 secs]
//[Full GC (Ergonomics)  19387K->17552K(19968K), 0.0505918 secs]
//[Full GC (Ergonomics)  19387K->18511K(19968K), 0.0494906 secs]

public class FullGCTest {

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();

        public void m() {
        }
    }

    // 定时任务线程池，与Timer相比更稳定
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);

        while (true){
            modelFit();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(task -> {
            // 首次执行任务延迟时间 2 秒
            // 每次执行任务间隔 3 秒
            executor.scheduleWithFixedDelay(() -> {
                task.m();
            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    /**
     * 获取 100 个 CardInfo
     * @return
     */
    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> list = new ArrayList<CardInfo>();
        for (int i = 0; i < 100; i++){
            list.add(new CardInfo());
        }
        return list;
    }
}
