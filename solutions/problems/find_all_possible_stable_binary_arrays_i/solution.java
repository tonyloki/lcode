class Solution {

    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        long[][][] dp0 = new long[zero + 1][one + 1][limit + 1];
        long[][][] dp1 = new long[zero + 1][one + 1][limit + 1];

        if (zero > 0) dp0[1][0][1] = 1;
        if (one > 0) dp1[0][1][1] = 1;

        for (int z = 0; z <= zero; z++) {
            for (int o = 0; o <= one; o++) {

                for (int r = 1; r <= limit; r++) {

                    if (dp0[z][o][r] > 0) {

                        // add another 0
                        if (z + 1 <= zero && r + 1 <= limit)
                            dp0[z + 1][o][r + 1] =
                                (dp0[z + 1][o][r + 1] + dp0[z][o][r]) % MOD;

                        // switch to 1
                        if (o + 1 <= one)
                            dp1[z][o + 1][1] =
                                (dp1[z][o + 1][1] + dp0[z][o][r]) % MOD;
                    }

                    if (dp1[z][o][r] > 0) {

                        // add another 1
                        if (o + 1 <= one && r + 1 <= limit)
                            dp1[z][o + 1][r + 1] =
                                (dp1[z][o + 1][r + 1] + dp1[z][o][r]) % MOD;

                        // switch to 0
                        if (z + 1 <= zero)
                            dp0[z + 1][o][1] =
                                (dp0[z + 1][o][1] + dp1[z][o][r]) % MOD;
                    }
                }
            }
        }

        long ans = 0;

        for (int r = 1; r <= limit; r++) {
            ans = (ans + dp0[zero][one][r]) % MOD;
            ans = (ans + dp1[zero][one][r]) % MOD;
        }

        return (int) ans;
    }
}