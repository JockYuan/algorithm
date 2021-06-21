package com.company;

import java.util.Random;

public class Shuffle {

    public static void main(String[] args) {

    }
    private int[] original;
    private int[] array;
    Random random = new Random();

    public Shuffle(int[] nums) {
        original = nums;
        array = new int[nums.length];
        for(int i = 0;i<nums.length;i++) {
            array[i]=nums[i];
        }
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for(int i = 0;i<original.length;i++) {
            array[i]=original[i];
        }
        return original;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = array.length;
        for(int i = 0; i<n ; i++) {
            swap(i, randRange(i, n));
        }
        return array;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void randomSwap(int start, int end) {
        if (end <= start) return;
        int j = random.nextInt(end-start) + start;
        swap(start, j);
    }

    private int randRange(int min, int max) {
        return random.nextInt(max - min) + min;
    }

}
