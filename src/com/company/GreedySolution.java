package com.company;


import jdk.internal.org.objectweb.asm.tree.FrameNode;

import java.util.*;

// 贪心算法
public class GreedySolution {
    public static void main(String[] args) {
        GreedySolution solution = new GreedySolution();

        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};

        int res = solution.findContentChildren(g, s);
        System.out.println("结果是 : " + res);

    }

    // 455. 分发饼干

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (j >= 0 && s[j] >= g[i]) { // 要判定j的下边界
                j--;
                result++;
            }
        }
        return result;
    }

    // 376. 摆动序列

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) return nums.length;

        int curDiff = 0;
        int preDiff = 0;
        int result = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) { // 判定峰值方法
                result++;
                preDiff = curDiff; // 只有在满足条件后, 才将 curDiff 赋值给 preDiff;
            }

        }
        return result;
    }

    // 53. 最大子序和

    // 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
    // 全局最优：选取最大“连续和”
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = Integer.MIN_VALUE;

        for (int n : nums) {
            sum += n;
            res = Math.max(res, sum);
            if (sum < 0) { // 如果和为负数, 则抛弃当前和
                sum = 0;
            }
        }
        return res;
    }

    // 122. 买卖股票的最佳时机 II

    public int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0); // 只取正利润的 , 并求和
        }

        return profit;
    }

    // 55. 跳跃游戏
    // 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;

        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);  // 去最大的cover值. 每个位置的cover值为 i+nums[i];
            if (cover >= nums.length - 1) return true;
        }
        return false;
    }

    // 45. 跳跃游戏 II

    public int jump(int[] nums) {
        int n = nums.length;
        int pos = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < n - 1; i++) { // 注意这里是小于nums.size() - 1，这是关键所在
            pos = Math.max(pos, i + nums[i]);
            if (i == end) {
                end = pos;
                step++;
            }
        }
        return step;
    }

    // 1005. K 次取反后最大化的数组和

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums); // 排序后负值在前面, 先将负值转为正值,

        int i = 0;
        while (k > 0 && nums[i] < 0) {
            nums[i] = -nums[i];
            k--;
            i++;
        }

        if (k > 0) { // 如果k>0 , 且为奇数, 则将nums其中最小值变为负数, 如果偶数则不用做什么
            if (k % 2 == 1) {
                int min = 0;
                int val = Integer.MAX_VALUE;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] < val) {
                        val = nums[j];
                        min = j;
                    }
                }

                nums[min] = -nums[min];
            }
        }

        int sum = 0;
        for (int n : nums) { // 求和
            sum += n;
        }

        return sum;

    }

    // 134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int curSum = 0;
        int totalSum = 0;
        int startPos = 0;

        for (int i = 0; i < len; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                startPos = i + 1;
                curSum = 0;
            }
        }
        if (totalSum >= 0) return startPos;
        else return -1;
    }

    // 135. 分发糖果
    public int candy(int[] ratings) {
        int[] candys = new int[ratings.length];

        Arrays.fill(candys, 1);

        for (int i = 1; i < ratings.length; i++) { // 从左往有看
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }

        for (int j = ratings.length - 2; j >= 0; j--) { // 从右往左看
            if (ratings[j] > ratings[j + 1]) {
                candys[j] = Math.max(candys[j], candys[j + 1] + 1); // 关键点, 判定上次值是否满足, 不满足使用右边值+1
            }
        }

        int sum = 0;
        for (int n : candys) {
            sum += n;
        }

        return sum;
    }

    // 860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int n : bills) {
            switch (n) {
                case 5:
                    five++;
                    break;
                case 10:
                    if (five <= 0) return false;
                    five--;
                    ten++;
                    break;
                case 20:
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    // 406. 根据身高重建队列
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) return -1;
                if (o1[0] == o2[0]) {
                    if (o1[1] < o2[1]) return -1;
                }
                return 0;
            }
        });

        List<int[]> res = new ArrayList<>();

        for (int[] k : people) {
            res.add(k[1], k);
        }

        return res.toArray(new int[][]{});

    }

    // 452. 用最少数量的箭引爆气球

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        int result = 1;

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                result++;
            } else {
                points[i][1] = Math.min(points[i - 1][1], points[i][1]);
            }
        }
        return result;
    }

    // 435. 无重叠区间

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int count = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    // 763. 划分字母区间
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();

        int[] map = new int[26];
        char[] sArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            map[sArray[i] - 'a'] = i;
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = map[sArray[i] - 'a'];
            if (i == right) {
                res.add(right - left + 1);
                left = i + 1;
            }
        }

        return res;
    }

    // 56. 合并区间

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = res.get(res.size()-1);
            if (intervals[i][1] <= last[1]) { // 先判定当前右值是否小于已加入的最后项的右值, 若小于,跳过该轮, 表示已覆盖当前区间
                continue;
            } else if (intervals[i][0] <= last[1]){
                last[1] = intervals[i][1];
            }
            else {
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[][]{});
    }
}
