import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        // Edge case: k = 1 → only one element → answer = 0
        if (k == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans[i][j] = 0;
                }
            }
            return ans;
        }

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                int[] arr = new int[k * k];
                int idx = 0;

                // Collect elements of k x k submatrix
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        arr[idx++] = grid[x][y];
                    }
                }

                // Sort the elements
                Arrays.sort(arr);

                int minDiff = Integer.MAX_VALUE;

                // Find minimum difference between DISTINCT elements
                for (int t = 1; t < arr.length; t++) {
                    if (arr[t] == arr[t - 1]) continue; // skip duplicates

                    minDiff = Math.min(minDiff, arr[t] - arr[t - 1]);
                }

                // Safety (though logically won't happen unless all equal)
                ans[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }

        return ans;
    }
}