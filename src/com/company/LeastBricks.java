package com.company;

import java.util.*;

// 554. 砖墙

/**
 * 554. 砖墙
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 *
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 *
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/brick-wall
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeastBricks {
    public static void main(String[] args) {
        List<Integer> level1 = Arrays.asList(1,2,2,1);
        List<Integer> level2 = Arrays.asList(3,2,1);
        List<Integer> level3 = Arrays.asList(1,3,2);
        List<Integer> level4 = Arrays.asList(2,4);
        List<Integer> level5 = Arrays.asList(1,3,1,1);
        List<List<Integer>> input = new ArrayList<>();
        input.add(level1);
        input.add(level2);
        input.add(level3);
        input.add(level4);
        input.add(level5);

        LeastBricks leastBricks = new LeastBricks();
        int res = leastBricks.leastBricks2(input);

        System.out.println(res);

    }
    // 暴力解法 , 超出时间限制
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() < 1) {
            return 0;
        }
        int maxWidth = 0;

        for(int i : wall.get(0)) {
            maxWidth += i;
        }

        int minCrossLevel = wall.size();
        for (int i = 1; i < maxWidth; i++) {
            int crossLevel = 0;
            for(List<Integer> wallLevel : wall) {
                if (!isCrossBrick(wallLevel, i)) {
                    crossLevel ++;
                }
            }
            minCrossLevel = Math.min(minCrossLevel, crossLevel);
        }
        return minCrossLevel;
    }

    private boolean isCrossBrick(List<Integer> bricks, int line) {
        int sum = 0;
        for (int width : bricks) {
            sum += width; // 查找每个砖的边界
            if (sum == line) { // 线在边界上, 表示能穿过
                return true;
            } else if (sum > line){
                return false;
            }
        }
        return false;
    }
    // 计算每一层的缝隙位置, 使用hash表来存储, value为缝隙位置出现的次数, 结束后,缝隙位置最大的那个, 即为最小传过层的位置.
    public int leastBricks2(List<List<Integer>> wall) {
        if (wall == null || wall.size() < 1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for(List<Integer> level : wall) {
            int pos = 0;
            for(int i = 0;i<level.size()-1;i++) {
                pos += level.get(i);
                int value = map.getOrDefault(pos, 0);
                map.put(pos, value+1);
            }
        }
        int k = 0;
        int v = 0;
        int maxValue = 0;
        int theKey = 0;
        for( Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                theKey = entry.getKey();
            }
        }
        // 计算穿墙的层数  = 墙的层数 - 缝隙个数
//        int levelCount = 0;
//        for(List<Integer> level : wall) {
//            if (!isCrossBrick(level, theKey)) {
//                levelCount++;
//            }
//        }
        return wall.size() - maxValue;
    }
}
