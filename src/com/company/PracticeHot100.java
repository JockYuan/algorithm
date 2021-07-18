package com.company;

import java.util.*;

public class PracticeHot100 {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        PracticeHot100 practice = new PracticeHot100();
//        int res = practice.lengthOfLongestSubstring("abcabcbb");
        int res = practice.reserve(Integer.MIN_VALUE);
        System.out.println("res : " + res);
    }
    // 337. 打家劫舍 III

    private Map<TreeNode, Integer> f = new HashMap<>();
    private Map<TreeNode, Integer> g = new HashMap<>();

    public int rob(TreeNode root) {
        robDFS(root);
        return Math.max(f.get(root), g.get(root));
    }

    private void robDFS(TreeNode node) {
        if (node == null) {
            return;
        }

        robDFS(node.left);
        robDFS(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + f.getOrDefault(node.right, 0));
    }

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int k = target - nums[i];
            if (map.containsKey(k)) {
                return new int[]{i, map.get(k)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 2. 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode d = new ListNode(-1);
        ListNode l3 = d;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = (l1 == null) ? 0 : l1.val;
            int v2 = (l2 == null) ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            l3.next = new ListNode(sum);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            l3 = l3.next;
        }

        if (carry > 0) {
            l3.next = new ListNode(carry);
        }
        return d.next;
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        Map<Character, Integer> map = new HashMap<>();
        int pre = -1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                pre = Math.max(pre, map.get(c));
            }
            map.put(c, i);
            res = Math.max(res, i - pre); // i-pre 即为字符串长度, 因为map.get(c) 返回的是相同字符的下标, 下一个字符才是不同的字符
        }
        return res;
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) return s;

        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                begin = i - ((maxLen + 1) / 2) + 1;
            }
        }
        return s.substring(begin, begin + maxLen);

    }

    private int expandAroundCenter(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return r - l - 1; // // 返回字符串长度, 这时 left ,right,字母不等, 所以是right-left -1;
    }
    // 动态规划
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 1) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len]; // dp[i][j] 表示字符串[i,j] 是否是回文字符串

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;  // 单个字符初始化为true
        }
        int maxLen = 1;
        int begin = 0;
        for (int L = 2; L <= len; L++) { // 从长度遍历
            for (int i = 0; i < len; i++) { // 左边界
                int j = i + L - 1; // 计算出右边界
                if (j >= len) {
                    break;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1; // 保存最大值, 和起始点的值
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen); // 获取回文字符串
    }

    // 6. Z 字形变换
    public String convert(String s, int numRow) {
        List<StringBuilder> lst = new ArrayList<>();
        boolean upToDown = false;

        int row = 0;

        for (char c : s.toCharArray()) {
            if (row == 0 || row == numRow - 1) {
                upToDown = !upToDown;
            }
            if (lst.size() < row + 1) {
                StringBuilder sb = new StringBuilder();
                lst.add(row, sb);
            }
            StringBuilder sb = lst.get(row);
            sb.append(c);
            int off = upToDown ? 1 : -1;
            row += off;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : lst) {
            res.append(stringBuilder);
        }
        return res.toString();
    }
    // 7. 整数反转

    public int reserve(int x) {
        if (x > -10 && x < 10) return x; // 使用这个判定
        int n = Math.abs(x); // Integer.MIN_VALUE 的abs 仍然是负值 Integer.MIN_VALUE
        int r = 0;
        while (n != 0) {
            int p = n % 10;
            n = n / 10;
            if (r > (Integer.MAX_VALUE - p) / 10) {
                return 0;
            }
            r = 10 * r + p;
        }
        return (x >= 0) ? r : -r;
    }

    //  8. 字符串转换整数 (atoi)
    private int START = 0;
    private int SIGNED = 1;
    private int IN_NUMBER = 2;
    private int END = 3;
    public int myAtoi(String s) {
        // 只用状态机
        Map<Integer, int[]> map = new HashMap<>();
        map.put(START, new int[]{START, SIGNED, IN_NUMBER, END});
        map.put(SIGNED, new int[]{END, END, IN_NUMBER, END});
        map.put(IN_NUMBER, new int[]{END, END, IN_NUMBER, END});
        map.put(END, new int[]{END, END, END, END});

        int curState = START;
        long res = 0;
        int signed = 0; // 符号, 0表示正值, -1表示负值
        for(char c : s.toCharArray()) {
            int state = getState(c);
            curState = map.get(curState)[state];
            if (curState == SIGNED) {
                if (c=='-') signed = -1;
            }
            else if (curState == IN_NUMBER) {
                res = res*10 + (c-'0');
                if (signed == 0 && res > Integer.MAX_VALUE) { // 加入符号判定
                    return Integer.MAX_VALUE;
                }
                if (-res < Integer.MIN_VALUE && signed == -1 ) { // 加入符合判定
                    return Integer.MIN_VALUE;
                }
            } else if (curState == END) {
                return (int) ((signed < 0)? -res : res);
            }
        }

        return (int) ((signed < 0)? -res : res);
    }

    private int getState(char c) {
        if (c == ' ') return START;
        else if (c == '+' || c== '-') return SIGNED;
        else if (c >= '0' && c <= '9') return IN_NUMBER;
        else return END;
    }

    // 4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1); // 必须保证前一个长度小于后一个
        }
        int left = 0, right = m;
        int median1 = 0;
        int median2 = 0;

        while (left <= right) { // 二分法
            int i = left + (right - left) / 2; // 第一个数组中的中值
            int j = (m + n + 1) / 2 - i; // 求出在第二个数组中的中值 , 使用m+n+1来保证 前一个分组在奇数的情况下多一个, 这样中值就是前一个分组的最后一个
            int num_im1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1]; // 在i=0时, 前一个用最小值表示
            int num_i = (i == m) ? Integer.MAX_VALUE : nums1[i];  // 在i=m时, 用最大值表示最后一个
            int num_jm1 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int num_j = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (num_im1 <= num_j) { // 判定条件
                median1 = Math.max(num_im1, num_jm1); // 中值选择, 前一个分组的最大值, 后一个分组的最小值
                median2 = Math.min(num_i, num_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1; // 偶数和奇数的情况
    }

    // 674. 最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        // 贪心
        int len = nums.length;
        int start = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) { // 如果当前值小于前一个,重置开始点start为当前值索引
                start = i;
            }
            res = Math.max(res, i - start + 1); // 遍历过程中取最大值, 范围为[start, i];
        }
        return res;
    }

    public int findLengthOfLCIS2(int[] nums) {
        // 动态规划
        int len = nums.length;
        int res = 0;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // 718. 最长重复子数组
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        int res = 0;
        for(int i=1;i<m;i++) {
            for(int j = 1;j<n;j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
