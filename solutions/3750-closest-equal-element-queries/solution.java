import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        // Store indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);
            
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }
            
            int idx = Collections.binarySearch(list, q);
            
            int prevIdx = (idx > 0) ? list.get(idx - 1) : list.get(list.size() - 1);
            int nextIdx = (idx < list.size() - 1) ? list.get(idx + 1) : list.get(0);
            
            int d1 = Math.min(Math.abs(q - prevIdx), n - Math.abs(q - prevIdx));
            int d2 = Math.min(Math.abs(q - nextIdx), n - Math.abs(q - nextIdx));
            
            result.add(Math.min(d1, d2));
        }
        
        return result;
    }
}
