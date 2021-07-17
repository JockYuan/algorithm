package com.company;

import java.util.ArrayList;
import java.util.List;

public class FindMode {
    TreeNode pre = null;
    int maxCount= 0;
    int count =0;
    List<Integer> listRes = new ArrayList<>();
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        node1.right = new TreeNode(2);

        int[] res = new FindMode().findMode(node1);
        System.out.println("结果是: " + res.toString());
    }

    public int[] findMode(TreeNode root) {
        inOrder(root);
        int[] res = new int[listRes.size()];
        for(int i = 0;i<listRes.size();i++) {
            res[i]=listRes.get(i);
        }
        return res;
    }

    private void inOrder(TreeNode root) {
        if(root == null ) return;

        inOrder(root.left);
        if (pre == null) {
            count =1;
        } else if (root.val == pre.val) {
            count++;
        } else {
            count =1;
        }

        if (count == maxCount) {
            listRes.add(root.val);
        }
        if (count > maxCount) {
            listRes.clear();
            listRes.add(root.val);
            maxCount = count;
        }

        pre = root;
        inOrder(root.right);
    }
}
