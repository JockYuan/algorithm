package com.company;

import static com.company.Utils.swap;

public class FndKthLargest {
    public static void main(String[] args) {
        FndKthLargest kthLargest = new FndKthLargest();
//        int[] nums = new int[] {3,3,3,3,4,3,3,3,3};
        int[] nums = new int[] {3,2,1,5,6,4};
        int res = kthLargest.findKthLargest(nums, 2);
        System.out.println("Res " + res);
    }

    public int findKthLargest(int[] nums, int k) {
        int heapSize = k;
        buildHeap(nums, heapSize);
        for(int i = heapSize; i < nums.length; i++) {
            if(nums[0] < nums[i]) {
                swap(nums, 0, i);
                heapifyDown(nums,0 ,heapSize);
            }

        }
        return nums[0];
    }

    private void heapifyDown(int[] nums, int m, int heapSize) {
        int left = m*2+1;
        int right = m*2+2;
        int largest = m;
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != m) {
            swap(nums, m, largest);
            heapifyDown(nums, largest, heapSize);
        }
    }

    private void buildHeap(int[] nums, int heapSize) {
        int n = (heapSize-1) / 2;
        for(int i = n ;i>=0;i--) {
            heapifyDown(nums, i, heapSize);
        }
    }

    private int quickSelect(int[] nums, int left, int right) {
        int x = nums[right];
        int i = left-1;
        for (int j = left ; j < right;j++) {
            if (nums[j] < x) {
                swap(nums, i+1, j);
                i++;
            }
        }
        swap(nums, i+1, right);
        return i+1;

    }
}
