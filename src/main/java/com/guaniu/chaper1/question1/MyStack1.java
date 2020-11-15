package com.guaniu.chaper1.question1;

import java.util.Stack;

public class MyStack1<T extends Integer> {
    private Stack<Integer> stackData = new Stack<Integer>();
    private Stack<Integer> stackMin = new Stack<Integer>();

    public int pop(){
        if (stackData.isEmpty()){
            throw new RuntimeException("栈已空！！！");
        }
        int val = stackData.pop();
        if (val == stackMin.peek()){
            stackMin.pop();
        }
        return val;
    }

    public boolean push(Integer val){
        stackData.push(val);
        if (stackMin.isEmpty()){
            stackMin.push(val);
        }else {
            if (val <= stackMin.peek()){
                stackMin.push(val);
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
