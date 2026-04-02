class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        
        // dp[i][j][k] -> max coins at (i,j) using k neutralizations
        int[][][] dp = new int[m][n][3];
        
        // Initialize with very small value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2;
                }
            }
        }
        
        // Start cell
        for (int k = 0; k < 3; k++) {
            if (coins[0][0] >= 0) {
                dp[0][0][k] = coins[0][0];
            } else {
                if (k > 0) dp[0][0][k] = 0; // neutralize
                dp[0][0][k] = Math.max(dp[0][0][k], coins[0][0]); // or take loss
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    
                    if (i == 0 && j == 0) continue;
                    
                    int val = coins[i][j];
                    
                    // from top
                    if (i > 0) {
                        if (val >= 0) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                dp[i-1][j][k] + val);
                        } else {
                            // take loss
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                dp[i-1][j][k] + val);
                            
                            // neutralize
                            if (k > 0) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k-1]);
                            }
                        }
                    }
                    
                    // from left
                    if (j > 0) {
                        if (val >= 0) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                dp[i][j-1][k] + val);
                        } else {
                            // take loss
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                dp[i][j-1][k] + val);
                            
                            // neutralize
                            if (k > 0) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k-1]);
                            }
                        }
                    }
                }
            }
        }
        
        return Math.max(dp[m-1][n-1][0],
               Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}
