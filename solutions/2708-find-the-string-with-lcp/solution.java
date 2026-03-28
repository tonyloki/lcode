import java.util.*;

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Validate diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // Step 2: DSU (Union-Find)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Find
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x)
                    parent[x] = applyAsInt(parent[x]);
                return parent[x];
            }
        };

        // Union
        java.util.function.BiConsumer<Integer, Integer> union = (a, b) -> {
            int pa = find.applyAsInt(a);
            int pb = find.applyAsInt(b);
            if (pa != pb) parent[pa] = pb;
        };

        // Merge indices
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union.accept(i, j);
                }
            }
        }

        // Step 3: Assign characters
        char[] res = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char current = 'a';

        for (int i = 0; i < n; i++) {
            int root = find.applyAsInt(i);
            if (!map.containsKey(root)) {
                if (current > 'z') return "";
                map.put(root, current++);
            }
            res[i] = map.get(root);
        }

        // Step 4: Validate LCP
        int[][] check = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    if (i == n - 1 || j == n - 1)
                        check[i][j] = 1;
                    else
                        check[i][j] = 1 + check[i + 1][j + 1];
                } else {
                    check[i][j] = 0;
                }
            }
        }

        // Compare
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] != lcp[i][j]) return "";
            }
        }

        return new String(res);
    }
}
