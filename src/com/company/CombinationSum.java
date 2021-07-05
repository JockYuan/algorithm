package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("Hello, world");
        int[] candidates = new int[]{2,7,6,3,5,1};
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.combinationSum(candidates, 9);
        System.out.println("Res " +combinationSum.res.toString());
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return res;
    }

    void backtracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backtracking(candidates, target, sum + candidates[i], i);
            path.removeLast();
        }
    }

}
