package com.guaniu;

import com.guaniu.chaper1.question3.RecursiveFunReverseStack;

import java.util.Stack;

public class MyTest {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        RecursiveFunReverseStack qq = new RecursiveFunReverseStack();
        qq.reverse(stack);
    }
}
