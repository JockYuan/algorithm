package com.company;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abba";
        int res = new LengthOfLongestSubstring().lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    public int lengthOfLongestSubstring(String s) {
        int res= 0;
        if (s == null) return res;
        if (s.length() == 1) return 1;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int i =0;
        for(int j=0;j<chars.length; j++) {
            if (map.containsKey(chars[j])) {

                int t = map.get(chars[j]) +1;
                if (t > i) i = t;
                map.remove(chars[j]);
            }
            map.put(chars[j],j);
            res = Math.max(res, j-i+1);
        }
        return res;
    }
}
