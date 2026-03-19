class Solution {
    public int uniquePaths(int m, int n) {
        // Create a 2D array dp where dp[i][j] represents the number of ways to reach (i,j)
        int[][] dp = new int[m][n];

        // Initialize the first row and column to 1 because there's only one way to reach each cell in these rows/columns
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill in the rest of the dp array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // The number of ways to reach (i,j) is the sum of the number of ways to reach (i-1,j) and (i,j-1)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}

