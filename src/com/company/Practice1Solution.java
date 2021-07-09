package com.company;

import java.util.*;
import java.util.logging.Handler;

public class Practice1Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Practice1Solution solution = new Practice1Solution();
        int res = solution.maxSubArray(nums);
        System.out.printf("res " + res);
    }
    // 53. 最大子序和

    int maxSubArray(int[] nums) {
//        int res = Integer.MIN_VALUE; // 使用最小初始值,
//        int sum =0;
//        for(int n : nums) {
//            sum += n;
//            res = Math.max(sum, res);
//            sum = Math.max(sum, 0);
//        }
//        return sum; // 返回值写错了
//
        int sum = 0;
        int res = Integer.MIN_VALUE;

        for (int n : nums) {
            sum += n;
            res = Math.max(res, sum); // 记录过程中的最大值
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }

    // 88. 合并两个有序数组

    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int index = m+n-1;
//        while(m > 0 && n>0) {
//            int a = nums1[m-1];
//            int b = nums2[n-1];
//            if (a > b) {
//                nums1[index] = a;
//                m--;
//            } else {
//                nums1[index] = b;
//                n--;
//            }
//            index--;
//        }
//        while (n>=0) {
//            nums1[index--] = nums2[n--];
//        }

        int index1 = m - 1;
        int index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    // 121. 买卖股票的最佳时机
    int maxProfit(int[] prices) {
//        if (prices.length <= 1) return 0;
//        int maxProfit = 0;
//
//        for(int i = 1;i<prices.length;i++) {
//            int profit = prices[i]-prices[i-1];
//            profit = Math.max(0, profit);
//            maxProfit += profit;
//        }
//        return maxProfit;

        int maxProfit = 0;
        if (prices.length <= 1) return 0;

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    // 125. 验证回文串
    boolean isPalindrome(String s) {
        // 方法一 , 先处理字符串, 后判定
        /**if (s.isEmpty()) return true;
         StringBuilder stringBuilder = new StringBuilder();
         String s1 = s.toLowerCase();
         for(char c:s1.toCharArray()) {
         if (isValidChar(c)) {
         stringBuilder.append(c);
         }
         }

         String handled = stringBuilder.toString();

         if (handled.length() <=1) return true;
         int left = 0;
         int right = handled.length()-1;
         while(left <= right) {
         if (handled.charAt(left++) != handled.charAt(right--)) return false;
         }
         return true; */
        // 方法二 直接原字符串上判定
        if (s.isEmpty()) return true;
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            char a = s.charAt(left);
            // 别遗漏大小写处理
            a = toLowerCaseChar(a);
            if (!isValidChar(a)) {
                left++;
                continue;
            }
            char b = s.charAt(right);
            b = toLowerCaseChar(b);
            if (!isValidChar(b)) {
                right--;
                continue;
            }

            if (a != b) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    private boolean isValidChar(char c) {
        return ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
    }

    private char toLowerCaseChar(char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char) (c - 'A' + 'a');
        }
        return c;
    }

    // 102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(); //每层新建一个列表
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list); // 处理完加入
        }
        return res;
    }
    // 138. 复制带随机指针的链表
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();

        Node p1 = head;
        while(p1 != null) {
            map.put(p1, new Node(p1.val));
            p1 = p1.next;
        }

        p1 = head;
        Node dummp2 = new Node(-1);
        Node p2;
        while(p1 != null) {
            p2 = map.get(p1);
            if (dummp2.next == null) {
                dummp2.next = p2;
            }
            p2.next = map.get(p1.next);
            p2.random = map.get(p1.random);
            p1 = p1.next;
        }

        return dummp2.next;
    }
}
