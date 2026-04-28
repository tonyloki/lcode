import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Step 1: flatten grid
        for (int[] row : grid) {
            for (int val : row) {
                list.add(val);
            }
        }
        
        // Step 2: check feasibility (same remainder)
        int rem = list.get(0) % x;
        for (int val : list) {
            if (val % x != rem) return -1;
        }
        
        // Step 3: sort and find median
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        
        // Step 4: calculate operations
        int ops = 0;
        for (int val : list) {
            ops += Math.abs(val - median) / x;
        }
        
        return ops;
    }
}