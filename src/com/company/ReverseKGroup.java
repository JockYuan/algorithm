package com.company;

public class ReverseKGroup {
    public static void main(String[] args) {
        System.out.println("Hello, world");

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        for(int i = 0; i < k; i++) {
           if (tail == null) {
               return head;
           }
           tail = tail.next;
        }

        ListNode newHead = reverse(head, tail);
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode h = new ListNode(-1);
        h.next = head;
        while (head != tail) {
            ListNode t = head.next;
            head.next = head.next.next;
            t.next = h.next;

        }
        return head;
    }
}
