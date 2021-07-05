package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        LetterCombinations letter = new LetterCombinations();
        letter.letterCombinations("23");
        System.out.println("Res + " + letter.res.toString());
    }

    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        Map<Integer, String> digitsMap = new HashMap<>();
        digitsMap.put(2, "abc");
        digitsMap.put(3, "def");
        digitsMap.put(4, "ghi");
        digitsMap.put(5, "jkl");
        digitsMap.put(6, "mno");
        digitsMap.put(7, "pqrs");
        digitsMap.put(8, "tuv");
        digitsMap.put(9, "wxyz");

        char[] c = digits.toCharArray();
        int[] digitsNums = new int[c.length];
        for(int i = 0;i<c.length;++i) {
            digitsNums[i] = c[i]-'0';
        }
        StringBuilder sb = new StringBuilder();
        backtracking(digitsNums, digitsMap, sb, 0);
        return res;

    }

    void backtracking(int[] digits, Map<Integer, String> map, StringBuilder sb, int index) {
        if(index == digits.length) {
            res.add(sb.toString());
            return;
        }

        String val = map.get(digits[index]);
        if (val == null) return;
        for(int i = 0;i<val.length();i++) {
            sb.append(val.charAt(i));
            backtracking(digits, map, sb, index+1);
            sb.deleteCharAt(sb.length()-1);
        }

    }

}
