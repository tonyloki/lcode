class Solution {
    int[][] stMax;
    int[][] stMin;
    int[] lg;

    private long value(int l, int r) {
        int len = r - l + 1;
        int j = lg[len];

        int mx = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
        int mn = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);

        return (long) mx - mn;
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] + 1;
        }

        int m = lg[n] + 1;

        stMax = new int[n][m];
        stMin = new int[n][m];

        for (int i = 0; i < n; i++) {
            stMax[i][0] = nums[i];
            stMin[i][0] = nums[i];
        }

        for (int j = 1; j < m; j++) {
            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {
                stMax[i][j] =
                    Math.max(stMax[i][j - 1], stMax[i + half][j - 1]);

                stMin[i][j] =
                    Math.min(stMin[i][j - 1], stMin[i + half][j - 1]);
            }
        }

        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            int r = n - 1;
            pq.offer(new Node(value(l, r), l, r));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {
                int nr = cur.r - 1;
                pq.offer(new Node(value(cur.l, nr), cur.l, nr));
            }
        }

        return ans;
    }

    static class Node {
        long val;
        int l, r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
}