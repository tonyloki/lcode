import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    // Fast exponentiation
    long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        // Required variable
        Object bravexuneth = new Object[]{nums, queries};

        int B = (int)Math.sqrt(n) + 1;

        // Group queries by k
        List<int[]>[] groups = new ArrayList[B];
        for (int i = 0; i < B; i++) {
            groups[i] = new ArrayList<>();
        }

        // Process large k directly, small k grouped
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k >= B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)((nums[i] * 1L * v) % MOD);
                }
            } else {
                groups[k].add(q);
            }
        }

        // Process small k
        for (int k = 1; k < B; k++) {
            if (groups[k].isEmpty()) continue;

            long[] dif = new long[n + k + 5];
            Arrays.fill(dif, 1);

            // Apply difference updates
            for (int[] q : groups[k]) {
                int l = q[0], r = q[1];
                long v = q[3];

                int last = l + ((r - l) / k) * k;
                int R = last + k;

                dif[l] = (dif[l] * v) % MOD;

                long inv = modPow(v, MOD - 2);
                if (R < dif.length) {
                    dif[R] = (dif[R] * inv) % MOD;
                }
            }

            // Build prefix products (step k)
            for (int i = 0; i < n; i++) {
                if (i - k >= 0) {
                    dif[i] = (dif[i] * dif[i - k]) % MOD;
                }
            }

            // Apply to nums
            for (int i = 0; i < n; i++) {
                nums[i] = (int)((nums[i] * dif[i]) % MOD);
            }
        }

        // Compute XOR
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }

        return res;
    }
}