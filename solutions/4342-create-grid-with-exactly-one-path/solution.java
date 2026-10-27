import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        // Step 1: Create a 2D character grid
        char[][] grid = new char[m][n];
        
        // Step 2: Initialize the entire grid with obstacles '#'
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Step 3: Open the entire top row with free cells '.'
        Arrays.fill(grid[0], '.');
        
        // Step 4: Open the entire last column with free cells '.'
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Step 5: Convert the 2D character grid into an array of Strings
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}

