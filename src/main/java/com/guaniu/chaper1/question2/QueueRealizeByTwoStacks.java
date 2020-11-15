package com.guaniu.chaper1.question2;

import java.util.Stack;

/**
 * 使用两个栈完成一个队列的实现
 * @param <T>
 */
public class QueueRealizeByTwoStacks<T> {
    private Stack<T> stack1 = new Stack<T>();
    private Stack<T> stack2 = new Stack<T>();

    public boolean add(T item){
        putDataToAnotherStack(stack2, stack1); // 将栈2的数据放回栈1
        stack1.push(item);
        return true;
    }

    public T poll(){
        peek();
        return stack2.pop();
    }

    public T peek(){
        if (stack1.isEmpty() && stack2.isEmpty()){
            new RuntimeException("队列为空！");
        }
        putDataToAnotherStack(stack1, stack2); // 将栈1的数据放到栈2
        return stack2.peek();
    }

    /**
     * 将栈s1中的数据弹入s2
     * @param s1
     * @param s2
     */
    private void putDataToAnotherStack(Stack<T> s1, Stack<T> s2){
        if (!s1.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
    }
}
