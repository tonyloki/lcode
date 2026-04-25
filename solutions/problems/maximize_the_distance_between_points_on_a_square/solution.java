class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];

        // Step 1: Convert to 1D
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (x == 0) arr[i] = y;
            else if (y == side) arr[i] = side + x;
            else if (x == side) arr[i] = 3L * side - y;
            else arr[i] = 4L * side - x;
        }

        Arrays.sort(arr);

        // Step 2: Binary Search
        int low = 1, high = side, ans = 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPick(arr, k, mid, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Step 3: Check feasibility
    private boolean canPick(long[] arr, int k, int dist, int side) {
        int n = arr.length;
        long perimeter = 4L * side;

        // try every starting point
        for (int i = 0; i < n; i++) {
            int count = 1;
            long last = arr[i];

            for (int j = i + 1; j < n; j++) {
                if (arr[j] - last >= dist) {
                    count++;
                    last = arr[j];
                    if (count == k) {
                        // circular check
                        if (last - arr[i] <= perimeter - dist) return true;
                        break;
                    }
                }
            }
        }
        return false;
    }
}