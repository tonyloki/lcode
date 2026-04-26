class Solution {
    int[] parent;
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curr = i * n + j;
                
                // check up
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    int up = (i - 1) * n + j;
                    if (find(curr) == find(up)) return true;
                    union(curr, up);
                }
                
                // check left
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    int left = i * n + j - 1;
                    if (find(curr) == find(left)) return true;
                    union(curr, left);
                }
            }
        }
        return false;
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) parent[pa] = pb;
    }
}
