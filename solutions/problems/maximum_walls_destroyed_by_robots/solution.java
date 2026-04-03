import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        int[][] r = new int[n][2];
        for (int i = 0; i < n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }

        Arrays.sort(r, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(walls);

        int[] left = new int[n];
        int[] right = new int[n];
        int[] num = new int[n];

        for (int i = 0; i < n; i++) {
            int pos = r[i][0];
            int dist = r[i][1];

            int leftBound = (i > 0) ? r[i - 1][0] + 1 : Integer.MIN_VALUE;
            int rightBound = (i < n - 1) ? r[i + 1][0] - 1 : Integer.MAX_VALUE;

            // left range
            int L1 = Math.max(pos - dist, leftBound);
            int R1 = pos;
            left[i] = count(walls, L1, R1);

            // right range
            int L2 = pos;
            int R2 = Math.min(pos + dist, rightBound);
            right[i] = count(walls, L2, R2);

            // walls between previous robot and current
            if (i > 0) {
                num[i] = count(walls, r[i - 1][0], r[i][0]);
            }
        }

        int prevLeft = left[0];
        int prevRight = right[0];

        for (int i = 1; i < n; i++) {
            int currLeft = Math.max(
                prevLeft + left[i],
                prevRight - right[i - 1] + Math.min(right[i - 1] + left[i], num[i])
            );

            int currRight = Math.max(
                prevLeft + right[i],
                prevRight + right[i]
            );

            prevLeft = currLeft;
            prevRight = currRight;
        }

        return Math.max(prevLeft, prevRight);
    }

    private int count(int[] walls, int L, int R) {
        int l = lowerBound(walls, L);
        int r = upperBound(walls, R);
        return r - l;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}