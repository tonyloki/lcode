class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long[] dp = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';

            // New subsequences ending with ch
            long newCount = (total + 1) % MOD;

            // Remove the old subsequences ending with ch
            total = (total - dp[idx] + newCount) % MOD;

            if (total < 0) {
                total += MOD;
            }

            dp[idx] = newCount;
        }

        return (int) total;
    }
}
