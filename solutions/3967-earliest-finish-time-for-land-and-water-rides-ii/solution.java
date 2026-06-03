class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        RideInfo waterInfo = build(waterStartTime, waterDuration);
        RideInfo landInfo = build(landStartTime, landDuration);

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            long landFinish = (long) landStartTime[i] + landDuration[i];
            ans = Math.min(ans, query(waterInfo, landFinish));
        }

        for (int i = 0; i < waterStartTime.length; i++) {
            long waterFinish = (long) waterStartTime[i] + waterDuration[i];
            ans = Math.min(ans, query(landInfo, waterFinish));
        }

        return (int) ans;
    }

    static class RideInfo {
        int[] start;
        long[] prefixMinDur;
        long[] suffixMinStartPlusDur;

        RideInfo(int[] start, long[] prefixMinDur, long[] suffixMinStartPlusDur) {
            this.start = start;
            this.prefixMinDur = prefixMinDur;
            this.suffixMinStartPlusDur = suffixMinStartPlusDur;
        }
    }

    private RideInfo build(int[] startTime, int[] duration) {
        int n = startTime.length;

        int[][] rides = new int[n][2];
        for (int i = 0; i < n; i++) {
            rides[i][0] = startTime[i];
            rides[i][1] = duration[i];
        }

        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));

        int[] start = new int[n];
        long[] prefixMinDur = new long[n];
        long[] suffixMinStartPlusDur = new long[n + 1];

        for (int i = 0; i < n; i++) {
            start[i] = rides[i][0];
        }

        prefixMinDur[0] = rides[0][1];
        for (int i = 1; i < n; i++) {
            prefixMinDur[i] = Math.min(prefixMinDur[i - 1], rides[i][1]);
        }

        suffixMinStartPlusDur[n] = Long.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            long value = (long) rides[i][0] + rides[i][1];
            suffixMinStartPlusDur[i] =
                    Math.min(suffixMinStartPlusDur[i + 1], value);
        }

        return new RideInfo(start, prefixMinDur, suffixMinStartPlusDur);
    }

    private long query(RideInfo info, long finishTime) {
        int idx = upperBound(info.start, (int) finishTime) - 1;

        long result = Long.MAX_VALUE;
        if (idx >= 0) {
            result = Math.min(result, finishTime + info.prefixMinDur[idx]);
        }

        result = Math.min(result, info.suffixMinStartPlusDur[idx + 1]);

        return result;
    }

    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
