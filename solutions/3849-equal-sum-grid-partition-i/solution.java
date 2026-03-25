class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        int[] rowSum = new int[m];
        int[] colSum = new int[n];

        // Calculate sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
                totalSum += grid[i][j];
            }
        }

        // If total is odd → can't split
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

        // Check horizontal cuts
        long prefix = 0;
        for (int i = 0; i < m - 1; i++) { // ensure both parts non-empty
            prefix += rowSum[i];
            if (prefix == target) return true;
        }

        // Check vertical cuts
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            prefix += colSum[j];
            if (prefix == target) return true;
        }

        return false;
    }
}
