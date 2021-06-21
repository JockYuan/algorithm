package com.company;

public class Jump {
    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] input = new int[]{2,3,1,1,4};
        jump.jump(input);
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int i =0;
        int j = 0;
        int res = 0;
        int step = 0;
        int pos = 0;
        while (i<n) {
            j=i+1;
            while (j<nums[i]) {
                if (nums[j]>step) {
                    pos = j;
                    step = nums[j];
                }
                j++;
            }
            i=pos;
            res++;
        }
        return res;

    }
}
