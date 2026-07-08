class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        ArrayList<Integer> digits = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();

        // Store all non-zero digits and their positions
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digits.add(d);
                positions.add(i);
            }
        }

        int m = digits.size();

        long[] prefVal = new long[m];
        long[] prefSum = new long[m];

        for (int i = 0; i < m; i++) {
            prefSum[i] = digits.get(i);
            if (i > 0) prefSum[i] += prefSum[i - 1];

            if (i == 0)
                prefVal[i] = digits.get(i);
            else
                prefVal[i] = (prefVal[i - 1] * 10 + digits.get(i)) % MOD;
        }

        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        int[] pos = new int[m];
        for (int i = 0; i < m; i++) pos[i] = positions.get(i);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int L = lowerBound(pos, l);
            int R = upperBound(pos, r) - 1;

            if (L >= m || R < L) {
                ans[i] = 0;
                continue;
            }

            int len = R - L + 1;

            long sum = prefSum[R];
            if (L > 0) sum -= prefSum[L - 1];

            long x = prefVal[R];
            if (L > 0) {
                x = (x - (prefVal[L - 1] * pow10[len]) % MOD + MOD) % MOD;
            }

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
