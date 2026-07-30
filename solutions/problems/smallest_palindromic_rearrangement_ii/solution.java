class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int[] half = new int[26];
        int len = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            len += half[i];
            if ((cnt[i] & 1) == 1) mid = (char) ('a' + i);
        }

        if (countPermutations(half, len, k) < k) return "";

        StringBuilder left = new StringBuilder();

        while (len > 0) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;

                long ways = countPermutations(half, len - 1, k);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    len--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder(left);
        if (mid != 0) ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countPermutations(int[] cnt, int total, long limit) {
        long ways = 1;
        int rem = total;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;

            ways *= combination(rem, cnt[i], limit);
            if (ways >= limit) return limit;

            rem -= cnt[i];
        }

        return ways;
    }

    private long combination(int n, int r, long limit) {
        if (r > n - r) r = n - r;

        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res >= limit) return limit;
        }

        return res;
    }
}