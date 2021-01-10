package com.guaniu.design.template;

import java.util.Random;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 21:46 2020/12/27
 * @Modified
 */
public class MyMessageWithQQ extends MyMessage{

    @Override
    public void getMsg() {
        System.out.println("打开QQ...");
    }

    @Override
    public void openMsg() {
        System.out.println("QQ有" + new Random(100).nextInt() + "消息！");
    }
}
