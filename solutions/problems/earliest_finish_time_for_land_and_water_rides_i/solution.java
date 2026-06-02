class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;
        int n = landStartTime.length;
        int m = waterStartTime.length;
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                int startWater = Math.max(landFinish, waterStartTime[j]);
                ans = Math.min(ans, startWater + waterDuration[j]);
                int waterFinish = waterStartTime[j] + waterDuration[j];
                int startLand = Math.max(waterFinish, landStartTime[i]);
                ans = Math.min(ans, startLand + landDuration[i]);
            }
        }
        return ans;
    }
}