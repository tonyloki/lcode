class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] max = new long[m][n];
        long[][] min = new long[m][n];
        
        max[0][0] = grid[0][0];
        min[0][0] = grid[0][0];
        
        // First column
        for (int i = 1; i < m; i++) {
            max[i][0] = max[i-1][0] * grid[i][0];
            min[i][0] = min[i-1][0] * grid[i][0];
        }
        
        // First row
        for (int j = 1; j < n; j++) {
            max[0][j] = max[0][j-1] * grid[0][j];
            min[0][j] = min[0][j-1] * grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                if (val >= 0) {
                    max[i][j] = Math.max(max[i-1][j], max[i][j-1]) * val;
                    min[i][j] = Math.min(min[i-1][j], min[i][j-1]) * val;
                } else {
                    max[i][j] = Math.min(min[i-1][j], min[i][j-1]) * val;
                    min[i][j] = Math.max(max[i-1][j], max[i][j-1]) * val;
                }
            }
        }
        
        long result = max[m-1][n-1];
        if (result < 0) return -1;
        
        return (int)(result % 1_000_000_007);
    }
}