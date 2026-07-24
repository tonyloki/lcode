class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        for (int x : nums) present[x] = true;

        boolean[] prev = new boolean[2048];
        prev[0] = true;

        for (int step = 0; step < 3; step++) {
            boolean[] next = new boolean[2048];
            for (int x = 0; x < 2048; x++) {
                if (!prev[x]) continue;
                for (int v = 0; v < 2048; v++) {
                    if (present[v]) {
                        next[x ^ v] = true;
                    }
                }
            }
            prev = next;
        }

        int ans = 0;
        for (boolean b : prev) {
            if (b) ans++;
        }
        return ans;
    }
}
