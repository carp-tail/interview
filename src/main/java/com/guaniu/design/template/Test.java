package com.guaniu.design.template;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 21:48 2020/12/27
 * @Modified
 */
public class Test {
    public static void main(String[] args) {
        MyMessage message1 = new MyMessageWithQQ();
        message1.showMsg();

        MyMessage message2 = new MyMessageWithWeixin();
        message2.showMsg();
    }
}
