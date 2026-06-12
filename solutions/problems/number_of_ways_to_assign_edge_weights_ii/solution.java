class Solution {
    static final long MOD = 1_000_000_007L;

    int LOG;
    int[] depth;
    int[][] up;
    long[] pow2;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        depth = new int[n + 1];
        up = new int[LOG][n + 1];

        java.util.List<Integer>[] graph = new java.util.ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new java.util.ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1, 0, graph);

        for (int k = 1; k < LOG; k++) {
            for (int v = 1; v <= n; v++) {
                int mid = up[k - 1][v];
                up[k][v] = (mid == 0) ? 0 : up[k - 1][mid];
            }
        }

        pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int w = lca(u, v);
            int len = depth[u] + depth[v] - 2 * depth[w];

            if (len == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow2[len - 1];
            }
        }

        return ans;
    }

    private void dfs(int root, int parent,
                     java.util.List<Integer>[] graph) {

        java.util.ArrayDeque<int[]> stack = new java.util.ArrayDeque<>();
        stack.push(new int[]{root, parent});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int node = cur[0];
            int par = cur[1];

            up[0][node] = par;

            for (int nei : graph[node]) {
                if (nei == par) continue;
                depth[nei] = depth[node] + 1;
                stack.push(new int[]{nei, node});
            }
        }
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];

        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) == 1) {
                a = up[k][a];
            }
        }

        if (a == b) return a;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return up[0][a];
    }
}