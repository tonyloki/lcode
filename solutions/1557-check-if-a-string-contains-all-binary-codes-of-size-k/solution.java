class Solution {
    public boolean hasAllCodes(String s, int k) {
        int needed = 1 << k;          // total binary codes = 2^k
        boolean[] seen = new boolean[needed];

        int count = 0;
        int hash = 0;
        int mask = needed - 1;        // to keep only k bits

        for (int i = 0; i < s.length(); i++) {

            // shift left and add current bit
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');

            // start checking only after first k-1 chars
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    if (count == needed) return true;  // early stop
                }
            }
        }

        return false;
    }
}

