class Solution {

    class Fenwick {
        int n;
        long[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 2];
        }

        void add(int idx, int val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long ans = 0;
            while (idx > 0) {
                ans += bit[idx];
                idx -= idx & -idx;
            }
            return ans;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] pref = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        int[] vals = pref.clone();
        Arrays.sort(vals);

        Map<Integer, Integer> map = new HashMap<>();
        int idx = 1;
        for (int x : vals) {
            if (!map.containsKey(x))
                map.put(x, idx++);
        }

        Fenwick bit = new Fenwick(idx + 2);

        long ans = 0;

        for (int x : pref) {
            int pos = map.get(x);

            ans += bit.query(pos - 1);

            bit.add(pos, 1);
        }

        return ans;
    }
}