class Solution {
    
    private int dist(int a, int b) {
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
    
    public int minimumDistance(String word) {
        int n = word.length();
        
        // dp[j] = max distance saved when second finger is at letter j
        int[] dp = new int[26];
        
        int total = 0; // total distance if using one finger
        
        for (int i = 1; i < n; i++) {
            int prev = word.charAt(i - 1) - 'A';
            int curr = word.charAt(i) - 'A';
            
            int d = dist(prev, curr);
            total += d;
            
            int[] newDp = dp.clone();
            
            for (int j = 0; j < 26; j++) {
                // Move second finger from j → curr
                newDp[prev] = Math.max(newDp[prev],
                        dp[j] + d - dist(j, curr));
            }
            
            dp = newDp;
        }
        
        int maxSaved = 0;
        for (int val : dp) {
            maxSaved = Math.max(maxSaved, val);
        }
        
        return total - maxSaved;
    }
}
