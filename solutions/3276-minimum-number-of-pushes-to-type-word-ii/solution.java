class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray())
            freq[c - 'a']++;

        Arrays.sort(freq);

        int ans = 0, push = 1, cnt = 0;

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            ans += freq[i] * push;
            cnt++;

            if (cnt == 8) {
                push++;
                cnt = 0;
            }
        }

        return ans;
    }
}
