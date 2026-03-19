class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] sum = new int[n + 1][m + 1];     // X=+1, Y=-1
        int[][] xCount = new int[n + 1][m + 1];  // count of X

        int result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                int val = 0;
                if (grid[i - 1][j - 1] == 'X') val = 1;
                else if (grid[i - 1][j - 1] == 'Y') val = -1;

                // Prefix sum for balance
                sum[i][j] = val
                        + sum[i - 1][j]
                        + sum[i][j - 1]
                        - sum[i - 1][j - 1];

                // Prefix sum for X count
                int isX = (grid[i - 1][j - 1] == 'X') ? 1 : 0;
                xCount[i][j] = isX
                        + xCount[i - 1][j]
                        + xCount[i][j - 1]
                        - xCount[i - 1][j - 1];

                // Check condition
                if (sum[i][j] == 0 && xCount[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}
