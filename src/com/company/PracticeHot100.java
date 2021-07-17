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

    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 1) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int begin = 0;
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
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
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
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
    public int myAtoi(String s) {
        return 0;
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

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i;
            int num_im1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int num_i = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int num_jm1 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int num_j = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (num_im1 <= num_j) {
                median1 = Math.max(num_im1, num_jm1);
                median2 = Math.min(num_i, num_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    // 674. 最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        // 贪心
        int len = nums.length;
        int start = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            res = Math.max(res, i - start + 1);
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
