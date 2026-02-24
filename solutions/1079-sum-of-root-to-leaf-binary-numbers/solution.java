class Solution {

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int current) {

        if (node == null) return 0;

        // shift left and add current bit
        current = (current << 1) | node.val;

        // if leaf → return number
        if (node.left == null && node.right == null) {
            return current;
        }

        // sum left + right
        return dfs(node.left, current) + dfs(node.right, current);
    }
}
