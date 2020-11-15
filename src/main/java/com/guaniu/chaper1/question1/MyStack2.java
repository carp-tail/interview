package com.guaniu.chaper1.question1;

import java.util.Stack;

public class MyStack2 {
    private Stack<Integer> stackData = new Stack<Integer>();
    private Stack<Integer> stackMin = new Stack<Integer>();

    public int pop(){
        if (stackData.isEmpty()){
            throw new RuntimeException("栈已空！！！");
        }
        stackMin.pop();
        return stackData.pop();
    }

    public boolean push(Integer val){
        stackData.push(val);
        if (stackMin.isEmpty()){
            stackMin.push(val);
        }else {
            int lastMin = stackMin.peek();
            if (val <= lastMin){
                stackMin.push(val);
            }else {
                stackMin.push(lastMin);
            }
        }
        return true;
    }

    public int getMin(){
        if (stackMin.isEmpty()){
            throw new RuntimeException("栈已空！！！");
        }
        return stackMin.peek();
    }
}
