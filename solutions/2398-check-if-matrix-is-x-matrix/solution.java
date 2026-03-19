class Solution {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i==j || i+j+1 == n ? grid[i][j] == 0 : grid[i][j] != 0)
                    return false;
            
        return true;
    }
}
