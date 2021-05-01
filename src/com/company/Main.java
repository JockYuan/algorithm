package com.company;


public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] sums = new int[] {3,2,1,5,6,4};
        Main main = new Main();
        int result = main.findKthLargest2(sums, 2);
        System.out.println(result);
    }

    public int findKthLargest(int[] a, int k) {
        int heapSize =  a.length;
        buildHeap(a, heapSize);
        for(int i = a.length-1; i > a.length -k ; i--) {
            swap(a, 0, i);
            --heapSize;
            heapify(a, 0, heapSize);
        }
        return a[0];
    }

    public void buildHeap(int[] a, int heapSize) {
        for(int i = heapSize/2; i>=0; i--) {
            heapify(a, i, heapSize);
        }
    }

    public void heapify(int[] a, int m, int heapSize){
        int left = m*2+1;
        int right = m*2+2;
        int largest = m;
        if (left < heapSize && a[left] > a[largest]) {
            largest = left;
        }
        if (right < heapSize && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != m) {
            swap(a, m, largest);
            heapify(a, largest, heapSize);
        }

    }

    private void swap(int[] a, int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }

    // 快速排序的分割算法
    public int partition(int[] a, int left, int right) {
        int i = left-1;
        int j = left;
        int x = a[right];
        for(;j<right;j++) {
            if (a[j]<=x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i+1, right);
        return i+1;
    }

    public int findKthLargest2(int[] a, int k) {
        int len = a.length;
        int left = 0;
        int right = len-1;

        int target = len - k;

        while(true) {
            int q = partition(a, left, right);
            if (q == target) return a[q];
            else if (q < target) {
                left = q+1;
            } else {
                right = q-1;
            }
        }
    }

}
