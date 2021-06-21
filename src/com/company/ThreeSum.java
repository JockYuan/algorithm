package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,1,2,-1,-4};
        ThreeSum r = new ThreeSum();
        List<List<Integer>> res =  r.threeSum(nums);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length <=2) return res;
        Arrays.sort(nums);
        for(int i = 0; i< nums.length -2;i++) {
            if (i>0 && nums[i-1]==nums[i]) continue;
            if (nums[i] > 0) return res;
            int L = i+1;
            int R = nums.length-1;
            while (L< R) {
                int sum = nums[i]+nums[L]+nums[R];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    res.add(list);
                    while (L<R && nums[L+1]== nums[L]) L++;
                    while (L<R && nums[R-1]== nums[R]) R--;
                    ++L;
                    --R;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return res;

    }
}
