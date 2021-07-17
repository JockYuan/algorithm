package com.company;

import netscape.security.UserTarget;

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

    // 213. 打家劫舍 II
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        } else {
            return Math.max(robRange(nums, 0, len-2), robRange(nums, 1, len-1)); //环形的话,分成0,len-2, 和 1, len-1;
        }
    }

    private int robRange(int[] nums, int start, int end) {
        int pre2 = nums[start], pre1 = Math.max(nums[start], nums[start+1]);
        for(int i = start+2 ; i<=end;i++) {
            int tmp = Math.max(pre2+nums[i], pre1);
            pre2 = pre1;
            pre1 = tmp;
        }
        return pre1;
    }

    // 64. 最小路径和
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new  int[m][n];
        int sum = 0;
        for(int i =0;i<n;i++) {
            sum += grid[0][i];
            dp[0][i]=sum;
        }
        sum = 0;
        for(int j = 0;j<m;j++) {
            sum += grid[j][0];
            dp[j][0] = sum;
        }

        for(int i=1;i<m;i++) {
            for(int j =1;j<n;j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];

    }
    // 303. 区域和检索 - 数组不可变 , 即前缀和

    int[] sums;
    public void NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n+1];
        for(int i =0;i<n;i++) {
            sums[i+1] = sums[i]+nums[i];
        }

    }

    private int sumRange(int left, int right) {
        return sums[right+1]-sums[left];
    }

    // 413. 等差数列划分
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length<3) {
            return 0;
        }
        int len = nums.length;

        int[] dp = new int[len];

        for(int i =2;i<len;i++) {
            if(nums[i]-nums[i-1] == nums[i-1] - nums[i-2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int total = 0;
        for(int c : dp) {
            total += c;
        }

        return total;
    }

    // 279. 完全平方数
    public int numSquares(int n) {
//        List<Integer> squareList = generateSquareList(n);
        int[] dp = new int[n+1];
        for(int i =1;i<=n;i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1;j*j<=i;j++) {
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i] = min;
        }
        return dp[n];
    }

//    private List<Integer> generateSquareList(int n) {
//        List<Integer> squareList = new ArrayList<>();
//        int diff = 3;
//        int square = 1;
//        while (square <= n) {
//            squareList.add(square);
//            square += diff;
//            diff+=2;
//        }
//        return squareList;
//    }

    // 91. 解码方法
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        for (int i = 1;i<=len;i++) {
            if (s.charAt(i-1) != '0') {
                dp[i] += dp[i-1];
            }
            if (i>1 && s.charAt(i-2) != '0' && ((s.charAt(i-2)-'0') * 10 + s.charAt(i-1)-'0') <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[len];
    }

    // 300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;

        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for(int i=1;i<len;i++) {
            for(int j = 0;j<i;j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int res = 0;
        for(int n:dp) {
            res = Math.max(res, n);
        }

        return res;
    }

    // 1049. 最后一块石头的重量 II

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int n : stones) {
            sum += n;
        }

        int t = sum /2;

        int[] dp = new int[t+1];
        for(int i = 0;i<stones.length;i++) {
            for(int j = t; j>= stones[i];j--) {
                dp[j] = Math.max(dp[j], dp[j-stones[i]] + stones[i]);
            }
        }
        return sum - dp[t] - dp[t];
    }

    // 279. 完全平方数
    public int numSquares2(int n) {
        int[] dp = new int[n+1];
        dp[0]=0;
        for(int i =0;i<n;i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 1; j*j <=i;j++) {
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i] = min+1;
        }
        return dp[n];
    }

    // 8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        Map<String, String[]> map = new HashMap<>();

        map.put("start", new String[] {"start", "signed", "in_number", "end"});
        map.put("signed", new String[]{"end", "end", "in_number", "end"});
        map.put("in_number", new String[]{"end", "end", "in_number", "end"});
        map.put("end", new String[]{"end", "end", "end", "end"});

        String state = "start";

        long res = 0;
        int signed = 1; // 0 为负数, 1 为正数
        for(char c: s.toCharArray()) {
            int st = getState(c);
            state = map.get(state)[st];
            if (state.equals("in_number")) {
                res = res*10 + (c-'0');
                if (signed == 1 && res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (signed == 0 && res -1 > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }

            } else if (state.equals("signed")){
                if (c == '-') signed = 0;
            } else if (state.equals("end")) {
                return (int) ((signed == 1) ? res:-res);
            }
        }
        return (int) ((signed == 1) ? res:-res);
    }

    private int getState(char c) {
        if (c== ' ') return 0;
        else if (c == '+' || c == '-') return 1;
        else if (c>='0' && c <='9') return 2;
        else return 3;
    }

    // 9. 回文数
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x<0 || x % 10 == 0) return false;

        int n = 0;
        while (n < x) {
            int b = x % 10;
            n = n*10+b; // 计算值用余数
            x = x / 10; // 迭代条件变化 用除法
        }

        return x == n || n / 10 == x; // 分偶数和奇数的情况

    }

    // 10. 正则表达式匹配


}
