package com.company;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

//        int res = solution.search(nums, target);
        int[] res = solution.twoSum(nums, target);
        System.out.println("结果为: " + res.toString());

    }

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int k = target - nums[i];
            if (map.containsKey(k)) {
                return new int[]{map.get(k), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 2. 两数相加

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        int sum = 0;
        ListNode d = new ListNode(0);
        ListNode l3 = d;
        while(l1 != null || l2 != null) {
            int v1 = (l1==null)?0:l1.val;
            int v2 = (l2==null)?0:l2.val;
            sum = v1+v2+carry; // 求和要加进位
            l3.next = new ListNode(sum%10); // 值为余数
            carry = sum/10; // 进位为商

            if (l1 != null) l1 = l1.next;

            if (l2 != null) l2 = l2.next;

            l3 = l3.next;

        }

        if (carry > 0) {
            l3.next = new ListNode(carry);
        }

        return d.next;

    }


    // 33 搜索旋转排序数组
    public int search(int[] nums, int target) {
        if (nums.length < 1) return -1;
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {
                // 位于正常区间
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {
                // 位于变化区间
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
    }

    // 20. 有效的括号

    boolean isValid(String s) {
        if (s.length() < 2) return false;

        Deque<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default: {
                    if (stack.isEmpty() || c != stack.pop()) { // 要判断栈是否为空
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
