class Solution {
    public int minSwaps(int[][] grid) {

        int n = grid.length;
        int[] trailing = new int[n];

        // Step 1: count trailing zeros
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                count++;
            }
            trailing[i] = count;
        }

        int swaps = 0;

        // Step 2: arrange rows greedily
        for (int i = 0; i < n; i++) {

            int required = n - i - 1;
            int j = i;

            // Find suitable row
            while (j < n && trailing[j] < required) {
                j++;
            }

            if (j == n) return -1; // impossible

            // Bring row j to position i using swaps
            while (j > i) {
                int temp = trailing[j];
                trailing[j] = trailing[j - 1];
                trailing[j - 1] = temp;

                swaps++;
                j--;
            }
        }

        return swaps;
    }
}
