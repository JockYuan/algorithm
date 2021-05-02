package com.company;

// 225. 用队列实现栈

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MyStack {
    /** Initialize your data structure here. */
    private Queue<Integer> queue;
    private Queue<Integer> temp;
    public MyStack() {
        queue = new ArrayBlockingQueue<Integer>(110);
        temp = new ArrayBlockingQueue<Integer>(110);
    }

    /** Push element x onto stack. */
    /**
     *   使用一个临时队列, 当新的元素压栈, 进入temp队列, 让后数据队列中所有数据压入临时队列,
     *   再把临时队列里的数据压入到数据队列,  这样来保证后进的数据能排在第一个出队列, 实现了栈的后进先出功能.
     * @param x
     */
    public void push(int x) {
        temp.offer(x);
        while(!queue.isEmpty()) {
            temp.offer(queue.poll());
        }
        while (!temp.isEmpty()) {
            queue.offer(temp.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        return -1;
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
