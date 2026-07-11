class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;

            int vertices = 0;
            int degreeSum = 0;

            while (!q.isEmpty()) {
                int node = q.poll();
                vertices++;
                degreeSum += graph[node].size();

                for (int nei : graph[node]) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        q.offer(nei);
                    }
                }
            }

            int edgesInComponent = degreeSum / 2;
            if (edgesInComponent == vertices * (vertices - 1) / 2) {
                ans++;
            }
        }

        return ans;
    }
}