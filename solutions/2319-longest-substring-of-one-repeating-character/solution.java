class Solution {

    static class Node {
        char leftChar, rightChar;
        int prefix, suffix, best, len;

        Node() {}

        Node(char c) {
            leftChar = rightChar = c;
            prefix = suffix = best = len = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {
        int n = str.length();
        int k = queryIndices.length;

        s = str.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            s[index] = c;
            update(1, 0, n - 1, index);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index) {
        if (l == r) {
            tree[node] = new Node(s[index]);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node left, Node right) {
        Node res = new Node();

        res.len = left.len + right.len;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        res.prefix = left.prefix;
        res.suffix = right.suffix;
        res.best = Math.max(left.best, right.best);

        if (left.rightChar == right.leftChar) {

            // Join suffix of left + prefix of right
            res.best = Math.max(
                res.best,
                left.suffix + right.prefix
            );

            // Entire left segment is one character
            if (left.prefix == left.len) {
                res.prefix = left.len + right.prefix;
            }

            // Entire right segment is one character
            if (right.suffix == right.len) {
                res.suffix = right.len + left.suffix;
            }
        }

        return res;
    }
}
