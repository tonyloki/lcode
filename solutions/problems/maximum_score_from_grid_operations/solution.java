class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;

        // prefix sum per column
        long[][] S = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                S[j][i + 1] = S[j][i] + grid[i][j];
            }
        }

        long[][][] dp = new long[n][n + 1][n + 1];

        // initialize first column
        for (int h = 0; h <= n; h++) {
            dp[0][h][0] = 0;
        }

        for (int col = 1; col < n; col++) {

            long[][] prevSuffixMax = new long[n + 1][n + 2];
            long[][] prevMax = new long[n + 1][n + 1];

            for (int prev = 0; prev <= n; prev++) {

                // suffix max
                prevSuffixMax[prev][n] = dp[col - 1][prev][n];
                for (int k = n - 1; k >= 0; k--) {
                    prevSuffixMax[prev][k] = Math.max(
                        prevSuffixMax[prev][k + 1],
                        dp[col - 1][prev][k]
                    );
                }

                // prefix max
                long best = Long.MIN_VALUE;
                for (int k = 0; k <= n; k++) {
                    long val = dp[col - 1][prev][k]
                        - Math.max(0, S[col - 1][k] - S[col - 1][prev]);
                    best = Math.max(best, val);
                    prevMax[prev][k] = best;
                }
            }

            for (int curr = 0; curr <= n; curr++) {
                for (int prev = 0; prev <= n; prev++) {

                    if (curr <= prev) {
                        dp[col][curr][prev] =
                            prevSuffixMax[prev][0]
                            + (S[col][prev] - S[col][curr]);
                    } else {
                        dp[col][curr][prev] =
                            Math.max(
                                prevSuffixMax[prev][curr],
                                prevMax[prev][curr]
                                + (S[col - 1][curr] - S[col - 1][prev])
                            );
                    }
                }
            }
        }

        long ans = 0;
        int last = n - 1;

        for (int prev = 0; prev <= n; prev++) {
            ans = Math.max(ans, dp[last][0][prev]);
            ans = Math.max(ans, dp[last][n][prev]);
        }

        return ans;
    }
}