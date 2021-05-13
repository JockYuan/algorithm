package com.company;


// 129. 求根节点到叶节点数字之和
public class SumNumbers {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        SumNumbers sumNumbers = new SumNumbers();
        int res = sumNumbers.sumNumbers(root);
        System.out.println(res);
    }

    int sum= 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return sum;
    }
    private void dfs(TreeNode node, int acc) {
        int temp = acc * 10 + node.val;
        if (node.left == null && node.right == null) { // 表示叶子节点
            sum += temp;
            return;
        }
        if (node.left != null) {
            dfs(node.left, temp);
        }
        if (node.right != null) {
            dfs(node.right, temp);
        }
    }
}
