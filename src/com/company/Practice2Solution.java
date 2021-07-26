package com.company;

import java.util.*;

public class Practice2Solution {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        Practice2Solution solution = new Practice2Solution();
        String s = "mississippi";
        String p = "issippi";
        int res = solution.strStr2(s, p);
        System.out.println("Res " + res);
    }

    // 704. 二分查找
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        // [left, right)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 27. 移除元素
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++; // 已经加1了
            }
        }
        return i; // 返回数组长度
    }

    // 977. 有序数组的平方

    // 考虑负数, 因此平方后可能负数为最大, 使用双指针, 指向首尾, 判定大小
    public int[] sortedSquares(int[] nums) {
        // 双指针
        int i = 0;
        int j = nums.length - 1;

        int[] ans = new int[nums.length];
        int k = nums.length - 1; // k为ans索引

        // [i,j];
        while (i <= j) {
            int square_i = nums[i] * nums[i];
            int square_j = nums[j] * nums[j];
            if (square_i > square_j) {
                ans[k] = square_i;
                i++;
            } else {
                ans[k] = square_j;
                j--;
            }
            k--;
        }
        return ans;
    }

    // 209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                sum -= nums[i];
                int len = j - i + 1;
                res = Math.min(res, len);
                i++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res; // 要考虑没有值的情况
    }

    /**
     * 链表
     */
    // 203. 移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(0, head); // 使用虚拟节点头
        ListNode cur = dummyNode; // 指定需要迭代的节点
        while (cur.next != null) { // 移除节点要确定前一个节点, 因此直接用cur.next节点来判定是否继续循环
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }

    // 206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode cur = head; // 定义需要迭代的节点
        ListNode pre = null; // 定义前一个节点, 以便链表翻转
        while (cur != null) {
            ListNode tmp = cur.next; // 保存下一个节点, 方便迭代使用
            cur.next = pre;  // 翻转指针,指向前一个节点
            pre = cur;  // 更新 pre 和 cur 节点
            cur = tmp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        // 使用递归
        return reverse206(null, head);
    }

    private ListNode reverse206(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode tmp = cur.next;
        cur.next = pre;
        pre = cur;
        return reverse206(pre, tmp);
    }

    // 24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode cur = dummyNode; // cur指向要交换的两个节点的前一个节点
        while (cur.next != null && cur.next.next != null) {
            ListNode tmp = cur.next;  // tmp 指向要交换的第一个节点
            ListNode tmp1 = cur.next.next.next; // tmp1 指向下一组节点的开头

            cur.next = tmp.next;
            tmp.next.next = tmp;
            tmp.next = tmp1;
            cur = tmp; // cur指向要交换的两个节点的前一个节点
        }
        return dummyNode.next;
    }

    // 19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode cur = dummyNode;
        ListNode pre = dummyNode;
        while (n + 1 > 0) { // 先步行N+1步,
            if (cur != null) {
                cur = cur.next;
            }
            n--;
        }
        while (cur != null) { // 然后双指针同步迭代
            pre = pre.next;
            cur = cur.next;
        }
        if (pre != null && pre.next != null) { // 删除节点
            pre.next = pre.next.next;
        }
        return dummyNode.next;
    }

    // 链表相交
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        int lenA = 0;
        int lenB = 0;
        // 先求各个链表的长度
        while (curA != null) {
            curA = curA.next;
            lenA++;
        }

        while (curB != null) {
            curB = curB.next;
            lenB++;
        }
        // 确定长度差距
        int gap = 0;
        if (lenA > lenB) {
            curA = headA;
            curB = headB;
            gap = lenA - lenB;
        } else {
            curA = headB;
            curB = headA;
            gap = lenB - lenA;
        }

        // 让长的链表先步行gap步
        while (gap-- > 0) {
            curA = curA.next;
        }

        // 同步步行到尾部, 找到相交的节点
        while (curA != null && curB != null) {
            if (curA == curB) break;
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    // 142. 环形链表 II
    ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // 先用快慢指针.
            fast = fast.next.next;
            if (slow == fast) { // 当快慢指针相遇时
                ListNode index1 = fast;
                ListNode index2 = head; // 将一个指针指向开始, 然后同步想迭代, 当再次相遇时的节点, 即为所求的环入口节点
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    // 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        Arrays.fill(map, 0);
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
        }

        for (int n : map) {
            if (n != 0) return false;
        }
        return true;
    }

    // 349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }
        for (int n : nums2) {
            if (set.contains(n)) {
                res.add(n);
            }
        }

        int[] ans = new int[res.size()];
        int index = 0;
        for (int i : res) {
            ans[index++] = i;
        }

        return ans;

    }

    // 202. 快乐数
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            n = getSum(n);
            if (n == 1) return true;
            if (set.contains(n)) return false;
            set.add(n);
        }
    }

    private int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            int a = n % 10;
            sum += a * a;
            n /= 10;
        }
        return sum;
    }

    // 383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] record = new int[26];
        for (char c : magazine.toCharArray()) {
            record[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            record[c - 'a']--;
        }
        for (int n : record) {
            if (n < 0) return false;
        }
        return true;
    }

    // 541. 反转字符串 II
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i += (2 * k)) { // 间隔2k
            if (i + k <= str.length) { // 大于等于k , 小于2k
                reverse541(str, i, i + k - 1); //
                continue;
            }
            reverse541(str, i, s.length() - 1); //小于k的情况
        }
        return new String(str);
    }

    private void reverse541(char[] str, int start, int end) {
        while (start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            end--;
            start++;
        }
    }

    // 旋转字符串
    public String reverseLeftWords(String s, int n) {
        char[] str = s.toCharArray();
        reverse541(str, 0, n - 1);
        reverse541(str, n, str.length - 1);
        reverse541(str, 0, str.length - 1);
        return new String(str);
    }

    // 32. 最长有效括号
    public int longestValidParentheses(String s) {
        // 动态规划
        if (s == null || s.length() < 2) return 0;

        int[] dp = new int[s.length()]; // i位结尾的有效括号长度
        int res = 0;

        Arrays.fill(dp, 0);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2; // 从dp[i-2]转移, dp[i-1]因为是'('所以为0
                } else {
                    if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
                        // 从dp[i-1] 和 dp[i-dp[i-1]-2] 转移
                        // dp[i-1]表示前一个有效括号, dp[i-dp[i-1]-2] 前前一个有效括号长度
                        dp[i] = ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                    }
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    // 使用栈
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() < 2) return 0;

        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    int pre = stack.peek();
                    res = Math.max(res, i - pre);
                }
            }
        }
        return res;
    }

    // 实现strStr()函数
    // kmp
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int m = haystack.length();
        int n = needle.length();

        String s = " " + haystack;
        String p = " " + needle;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();

        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && pp[i] != pp[j + 1]) j = next[j];
            if (pp[i] == pp[j + 1]) j++;
            next[i] = j;
        }

        for (int i = 1, j = 0; i <= m; i++) {
            while (j > 0 && ss[i] != pp[j + 1]) j = next[j];
            if (ss[i] == pp[j + 1]) j++;
            if (j == n) return i - n;
        }
        return -1;
    }

    // sunday 算法
    public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;

        int m = haystack.length();
        int n = needle.length();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(needle.charAt(i), n - i);
        }
        int i = 0;
        while (i + n <= m) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            } else {
                if (i + n >= m) return -1;
                i += map.getOrDefault(haystack.charAt(i + n), n);
            }
        }
        return (i + n > m) ? -1 : i;
    }

    //
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int len = nums.length - k + 1;
        int[] res = new int[len];
        int num = 0;
        MyQueue myQueue = new MyQueue();

        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        res[num++] = myQueue.peek();
        for (int i = k; i < nums.length; i++) {
            myQueue.poll(nums[i - k]);
            myQueue.add(nums[i]);
            res[num++] = myQueue.peek();
        }
        return res;
    }

    // 347.前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1); // 统计出现的频率
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        // 利用最小堆来取最大的k个元素
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }

    // 1047. 删除字符串中的所有相邻重复项
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        int k = stack.size();
        if (k == 0) return "";
        StringBuilder res = new StringBuilder();
        while(stack.size() != 0) {
            res.append(stack.removeLast());
        }
        return res.toString();
    }

}
