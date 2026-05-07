class Solution {

    static class Node {
        int l, r, mx;

        Node(int l, int r, int mx) {
            this.l = l;
            this.r = r;
            this.mx = mx;
        }
    }

    public int[] maxValue(int[] nums) {

        int n = nums.length;

        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            Node cur = new Node(i, i, nums[i]);

            while (!stack.isEmpty() && nums[i] < stack.peek().mx) {

                Node top = stack.pop();

                cur.l = top.l;
                cur.mx = Math.max(cur.mx, top.mx);
            }

            stack.push(cur);
        }

        int[] ans = new int[n];

        for (Node node : stack) {

            for (int i = node.l; i <= node.r; i++) {
                ans[i] = node.mx;
            }
        }

        return ans;
    }
}
