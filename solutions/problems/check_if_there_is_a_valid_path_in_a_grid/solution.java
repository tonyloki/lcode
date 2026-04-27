import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // directions: up, right, down, left
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        Map<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{3,1});
        map.put(2, new int[]{0,2});
        map.put(3, new int[]{3,2});
        map.put(4, new int[]{1,2});
        map.put(5, new int[]{3,0});
        map.put(6, new int[]{1,0});

        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        vis[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if(x == m-1 && y == n-1) return true;

            for(int d : map.get(grid[x][y])) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];

                if(nx < 0 || ny < 0 || nx >= m || ny >= n || vis[nx][ny]) continue;

                // check reverse direction
                int opposite = (d + 2) % 4;

                for(int nd : map.get(grid[nx][ny])) {
                    if(nd == opposite) {
                        vis[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        break;
                    }
                }
            }
        }

        return false;
    }
}