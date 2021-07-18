package com.company;

import netscape.security.UserTarget;

import java.sql.Array;
import java.util.*;
import java.util.logging.Handler;

public class Practice1Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,1,55};
        Practice1Solution solution = new Practice1Solution();
        List<String> res = solution.letterCombinations("23");
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
        if (x<0 || x % 10 == 0) return false; // 负数和大于10的数切尾数为0的情况, 返回false

        int n = 0;
        while (n < x) {
            int b = x % 10;
            n = n*10+b; // 计算值用余数
            x = x / 10; // 迭代条件变化 用除法
        }

        return x == n || n / 10 == x; // 分偶数和奇数的情况

    }

    // 10. 正则表达式匹配

    public boolean isMatch(String s, String p) {
        boolean dp[][] = new boolean[p.length()+1][s.length()+1];

        for(int j = 0;j<s.length();j++) {
            dp[0][j]=false;
        }
        dp[0][0] = true;
        for(int i = 1;i<p.length();i++) {
            if (p.charAt(i-1) == '*') {
                dp[i][0] = dp[i-2][0];
            }
        }

        for(int j = 1;j<=s.length();j++) {
            for(int i =1;i<= p.length();i++) {
                if (p.charAt(i-1) == '*') {
                    dp[i][j] = dp[i-2][j] || (dp[i][j-1] && (s.charAt(j-1) == p.charAt(i-2) || p.charAt(i-2)=='.'));
                } else if (s.charAt(j-1)==p.charAt(i-1) || p.charAt(i-1)=='.') {
                    dp[i][j]=dp[i-1][j-1];
                } else {
                    dp[i][j]=false;
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    // 11. 盛最多水的容器

    public int maxArea(int[] height) {
        if (height == null || height.length <2) return 0;
        int left = 0;
        int right = height.length-1;
        int res = 0;

        while (left <= right) {
            int h = Math.min(height[left], height[right]);
            int area = (right - left) * h;
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left ++;
            } else {
                right--;
            }
        }
        return res;
    }

    // 12. 整数转罗马数字
    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        int[] base = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder stringBuilder = new StringBuilder();

        int index = 0;

        while (num > 0 && index < base.length) {
            if (num >= base[index]) {
                num -=base[index];
                stringBuilder.append(map.get(base[index]));
            } else {
                index++;
            }
        }
        return stringBuilder.toString();
    }

    public String inToRoman2(int num) {
        int[] values={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] rom={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; // 更快一些

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<values.length;i++){
            while(num>=values[i]){
                sb.append(rom[i]);
                num-=values[i];
            }
        }

        return sb.toString();
    }

    // 13. 罗马数字转整数
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for(int i = 0;i<s.length();i++) {
            int a = map.get(s.charAt(i));
            if (i < (s.length()-1) &&  a < map.get(s.charAt(i+1))) { // 前面值小于后面的话, 将当前值减去当前值
                res -= a;
            } else {
                res += a;
            }
        }

        return res;

    }

    // 14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String cur = strs[0]; // 取第一个字符串做为开始
        int index = cur.length()-1;
        for(String s : strs) { // 分别判定其他字符串, 依次判定相同的字符,
            int i =0;
            while(i < s.length() && i < cur.length() && cur.charAt(i) == s.charAt(i)) {
                i++;
            }
            index = Math.min(index, i-1); // 找到最后相同字符位置
        }
        return cur.substring(0, index+1); // 截取公共前缀
    }

    // 15.三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <3) return res;

        Arrays.sort(nums);

        for(int i =0;i<nums.length-2 && nums[i]<=0;i++) {

            if (i>0 && nums[i]==nums[i-1]) continue;

            int left = i+1;
            int right = nums.length-1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    res.add(item);
                    while (left < right && nums[left] == nums[left+1]) {
                        left ++;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right --;
                    }

                    left ++;
                    right --;  // 这里不能忘记, 上面的是去重, 这里才是取值后移动索引
                } else if (sum > 0) {
                    right--;
                } else {
                    left ++;
                }
            }
        }
        return res;

    }

    // 16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int closet = Integer.MAX_VALUE;
        int res =Integer.MAX_VALUE;

        for (int i = 0; i < nums.length-2; i++) {
//            if (i>0 && nums[i]==nums[i-1]) continue;  //不要去重

            int left = i+1;
            int right = nums.length-1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) return target;
                int a = Math.abs(sum-target);
                if (a < closet) {
                    closet = a;
                    res = sum;
                }
                // 不要去重
//                while (left < right && nums[left] == nums[left+1]) left ++;
//                while (left < right && nums[right] == nums[right-1]) right --;

                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    // 17. 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        String[] map = new String[] {"", ",", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        backtrack17(digits, path, 0, map, res);
        return res;

    }

    private void backtrack17(String digits, StringBuilder path, int start, String[] map, List<String> res) {
        if (digits.length() == path.length()) { // 这里也可以用start的值来判定
            res.add(path.toString());
            return;
        }

        String str = map[digits.charAt(start) - '0']; // 使用start来表示当前处理的位置
        for (char c : str.toCharArray()) {
            path.append(c);
            backtrack17(digits, path, start + 1, map, res); // 这里表示处理完当前, 处理下一个字符
            path.deleteCharAt(path.length() - 1);
        }

    }

    // 19. 删除链表的倒数第 N 个结点

    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummpNode = new ListNode(-1, head);
//
//        int count = 1; // 加上dummpNode 节点
//        while(head != null) {
//            count ++;
//            head = head.next;
//        }
//
//        int preIndex = count - n-1;
//        ListNode L1= dummpNode;
//        while(preIndex > 0) {
//            L1 = L1.next;
//            preIndex--;
//        }
//
//        ListNode next = L1.next.next;
//        L1.next = next;
//        return dummpNode.next;

        ListNode dummpNode = new ListNode(0, head);

        ListNode h1 = dummpNode;
        ListNode h2 = dummpNode;

        while(n+1 > 0) {
            h1 = h1.next;
            n--;
        }

        while(h1 !=null) {
            h1 = h1.next;
            h2 = h2.next;
        }

        if (h2.next != null) {
            h2.next = h2.next.next;
        }
        return dummpNode.next;
    }
}
