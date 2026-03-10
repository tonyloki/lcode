class Solution {

    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        long[][][] dp = new long[zero + 1][one + 1][2];

        for (int z = 0; z <= zero; z++) {
            for (int o = 0; o <= one; o++) {

                if (z == 0 && o == 0) continue;

                // Base cases
                if (z == 0) {
                    if (o <= limit) dp[z][o][1] = 1;
                    continue;
                }

                if (o == 0) {
                    if (z <= limit) dp[z][o][0] = 1;
                    continue;
                }

                // End with 0
                long val0 = (dp[z - 1][o][0] + dp[z - 1][o][1]) % MOD;
                if (z - limit - 1 >= 0)
                    val0 = (val0 - dp[z - limit - 1][o][1] + MOD) % MOD;

                dp[z][o][0] = val0;

                // End with 1
                long val1 = (dp[z][o - 1][0] + dp[z][o - 1][1]) % MOD;
                if (o - limit - 1 >= 0)
                    val1 = (val1 - dp[z][o - limit - 1][0] + MOD) % MOD;

                dp[z][o][1] = val1;
            }
        }

        return (int)((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}