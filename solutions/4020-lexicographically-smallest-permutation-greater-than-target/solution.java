class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Frequency of characters in s
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // We try to match target from left to right.
        // If matching fails, we can immediately create
        // the smallest greater permutation at that position.
        for (int i = 0; i < n; i++) {
            int cur = target.charAt(i) - 'a';

            // Try to use target[i]
            if (count[cur] > 0) {
                count[cur]--;
            } else {
                // Cannot match target[i].
                // Find the smallest character greater than target[i].
                for (int c = cur + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        StringBuilder ans = new StringBuilder();

                        // Prefix matched target[0...i-1]
                        ans.append(target, 0, i);

                        // Make the first difference greater
                        ans.append((char) ('a' + c));
                        count[c]--;

                        // Append remaining characters in sorted order
                        appendSorted(ans, count);

                        return ans.toString();
                    }
                }

                // No larger character exists.
                // We need to backtrack and change an earlier position.
                break;
            }
        }

        /*
         * target's entire prefix was matched.
         * We now need to find the rightmost position that
         * can be increased.
         */
        count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Match target while keeping track of remaining characters.
        int matched = 0;

        for (int i = 0; i < n; i++) {
            int cur = target.charAt(i) - 'a';

            if (count[cur] == 0) {
                break;
            }

            count[cur]--;
            matched++;
        }

        // Backtrack from the matched position.
        for (int i = matched - 1; i >= 0; i--) {
            int cur = target.charAt(i) - 'a';

            // Return target[i] back to the available characters
            count[cur]++;

            // Find smallest character greater than target[i]
            for (int c = cur + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder ans = new StringBuilder();

                    // Prefix before i
                    ans.append(target, 0, i);

                    // First greater character
                    ans.append((char) ('a' + c));
                    count[c]--;

                    // Smallest possible suffix
                    appendSorted(ans, count);

                    return ans.toString();
                }
            }
        }

        return "";
    }

    private void appendSorted(StringBuilder sb, int[] count) {
        for (int c = 0; c < 26; c++) {
            while (count[c] > 0) {
                sb.append((char) ('a' + c));
                count[c]--;
            }
        }
    }
}
