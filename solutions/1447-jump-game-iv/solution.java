class Solution {
    public int minJumps(int[] arr) {

        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;

        int steps = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int s = 0; s < size; s++) {

                int i = queue.poll();
                if (i == n - 1) {
                    return steps;
                }
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }
                if (map.containsKey(arr[i])) {

                    for (int next : map.get(arr[i])) {

                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    map.remove(arr[i]);
                }
            }

            steps++;
        }

        return -1;
    }
}
