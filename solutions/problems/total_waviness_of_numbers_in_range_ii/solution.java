class Solution {

    private char[] s;
    private Long[][][][][] cntMemo;
    private Long[][][][][] sumMemo;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n <= 0) return 0;

        s = Long.toString(n).toCharArray();
        int m = s.length;

        cntMemo = new Long[m + 1][11][11][17][2];
        sumMemo = new Long[m + 1][11][11][17][2];

        return dfs(0, 10, 10, 0, 1)[1];
    }

    // returns {count, totalWaviness}
    private long[] dfs(int pos, int prev2, int prev1, int len, int tight) {

        if (pos == s.length) {
            return new long[]{1L, 0L};
        }

        if (tight == 0 &&
            cntMemo[pos][prev2][prev1][len][0] != null) {

            return new long[]{
                cntMemo[pos][prev2][prev1][len][0],
                sumMemo[pos][prev2][prev1][len][0]
            };
        }

        int limit = tight == 1 ? s[pos] - '0' : 9;

        long cnt = 0;
        long sum = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (len == 0 && d == 0) {
                long[] nxt = dfs(pos + 1, 10, 10, 0, ntight);
                cnt += nxt[0];
                sum += nxt[1];
                continue;
            }

            int add = 0;

            if (len >= 2) {
                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }
            }

            int nlen = Math.min(16, len + 1);

            long[] nxt;

            if (len == 0) {
                nxt = dfs(pos + 1, 10, d, nlen, ntight);
            } else {
                nxt = dfs(pos + 1, prev1, d, nlen, ntight);
            }

            cnt += nxt[0];
            sum += nxt[1] + nxt[0] * add;
        }

        if (tight == 0) {
            cntMemo[pos][prev2][prev1][len][0] = cnt;
            sumMemo[pos][prev2][prev1][len][0] = sum;
        }

        return new long[]{cnt, sum};
    }
}