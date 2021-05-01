package com.company;

import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) {

    }
    public int [] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) +1);
            }else {
                map.put(nums[i], 1);
            }
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[] {num, count});
                }
            } else {
                queue.offer(new int[] {num, count});
            }
        }
        int [] ret = new int[k];
        for(int i = 0; i< k; i++) {
            ret[i]=queue.poll()[0];
        }
        return ret;
    }
    // 桶排序
    public int [] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) +1);
            }else {
                map.put(nums[i], 1);
            }
        }

        List<Integer>[] buckets = new ArrayList[nums.length +1];
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(key);
        }

        List<Integer> topKey = new ArrayList<>();
        for(int i = buckets.length-1 ; i >=0 && topKey.size() < k; i--){
           if(buckets[i] == null) continue;
           if (buckets[i].size() <= (k - topKey.size())) {
               topKey.addAll(buckets[i]);
           } else {
               topKey.addAll(buckets[i].subList(0, k-topKey.size()));
           }

        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topKey.get(i);
        }
        return res;
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (char c : map.keySet()) {
            int f = map.get(c);
            if (buckets[f] == null) {
                buckets[f] = new ArrayList<>();
            }
            buckets[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = buckets.length-1; i>=0 ;i--) {
            if (buckets[i] == null) continue;
            for(char c: buckets[i]) {
                for(int j=0;j<i;j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }
}
