package com.company;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {
    Deque<Integer> deque = new LinkedList<>();

    // peek 队列头部
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) { // 移除元素,判定出口是否是要移除的元素.
            deque.poll(); // 如果是, 则移除头部元素 相当于 pollFirst
        }
    }

    void add(int val) {
        while(!deque.isEmpty()&& val>deque.getLast()) deque.removeLast(); // 尾部添加元素
        deque.add(val); // add 尾部添加元素, 相当于addLast;
    }

    int peek() {
        return deque.peek();
    }

}
