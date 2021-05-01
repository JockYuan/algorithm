package com.company;

import java.util.Arrays;

public class FindContentChildren {
    public static void main(String[] args) {

    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int numsOfChildren = g.length;
        int numsOfCookies = s.length;
        int i = 0;
        int j = 0;
        while (i < numsOfChildren && j<numsOfCookies) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
