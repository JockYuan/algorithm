package com.company;

public class SortColor {
    public static void main(String[] args) {

    }

    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, p0) == 0
        // all in [p0, i) == 1
        // all in (p2, len-1] == 2
        int p0 = 0;
        int i = 0;
        int p2 = len -1;
        while(i<=p2) {
            if (nums[i] == 0) {
                Utils.swap(nums, i, p0);
                p0++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                Utils.swap(nums, i, p2);
                p2--;
            }
        }
    }
}
