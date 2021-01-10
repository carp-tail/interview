package com.guaniu.design.template;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: guaniu
 * @Description: 发送消息抽象模板
 *
 * @Attention：一个抽象类公开定义了它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方法进行
 *
 * @Date: Create in 21:40 2020/12/27
 * @Modified
 */
public abstract class MyMessage {

    public abstract void getMsg();

    public String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    public abstract void openMsg();

    public void showMsg(){
        getMsg();
        System.out.println("当前时间：" + getDate());
        openMsg();
    }
}
