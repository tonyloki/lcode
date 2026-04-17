import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> prev = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            // Check if current value forms a mirror pair
            if (prev.containsKey(x)) {
                int j = prev.get(x);
                minDist = Math.min(minDist, i - j);
            }

            // Store reverse for future matches
            int rev = reverse(x);
            prev.put(rev, i);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    // Function to reverse digits
    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}
