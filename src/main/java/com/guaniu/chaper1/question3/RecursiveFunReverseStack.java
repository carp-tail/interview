package com.guaniu.chaper1.question3;

import java.util.Stack;

/**
 * 使用递归函数逆序栈
 */
public class RecursiveFunReverseStack {
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = getAndRemoveLast(stack);
        reverse(stack);
        stack.push(i);
    }
    public static Integer getAndRemoveLast(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = getAndRemoveLast(stack);
            stack.push(result);
            return last;
        }
    }
}
