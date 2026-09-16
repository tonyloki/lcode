class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007L;

        long[] dp = new long[n];
        long[] prefixSums = new long[n + 1];

        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
        }

        for (int i = 0; i < k; i++) {
            dp[0] = 0;

            for (int j = 1; j < n; j++) {
                dp[j] = (dp[j - 1] + prefixSums[j]) % MOD;
            }

            prefixSums[0] = 0;

            for (int j = 0; j < n; j++) {
                prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
            }
        }

        return (int) dp[n - 1];
    }
}
