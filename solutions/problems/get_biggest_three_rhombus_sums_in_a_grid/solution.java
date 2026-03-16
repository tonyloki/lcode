import java.util.*;

class Solution {

    public int[] getBiggestThree(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // size 0 rhombus
                add(set, grid[r][c]);

                for (int k = 1; ; k++) {

                    if (r - k < 0 || r + k >= m || c - k < 0 || c + k >= n)
                        break;

                    int sum = 0;

                    int x = r - k;
                    int y = c;

                    // top → right
                    for (int i = 0; i < k; i++)
                        sum += grid[x + i][y + i];

                    // right → bottom
                    for (int i = 0; i < k; i++)
                        sum += grid[r + i][c + k - i];

                    // bottom → left
                    for (int i = 0; i < k; i++)
                        sum += grid[r + k - i][c - i];

                    // left → top
                    for (int i = 0; i < k; i++)
                        sum += grid[r - i][c - k + i];

                    add(set, sum);
                }
            }
        }

        int size = Math.min(3, set.size());
int[] res = new int[size];

for (int i = 0; i < size; i++)
    res[i] = set.pollLast();

        return res;
    }

    void add(TreeSet<Integer> set, int val) {
        set.add(val);
        if (set.size() > 3)
            set.pollFirst();
    }
}