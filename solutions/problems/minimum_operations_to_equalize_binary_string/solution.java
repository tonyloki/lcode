import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int start = 0;

        for (char ch : s.toCharArray())
            if (ch == '0') start++;

        if (start == 0) return 0;

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();

        for (int i = 0; i <= n; i++) {
            if (i == start) continue;
            if (i % 2 == 0) even.add(i);
            else odd.add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int m = q.poll();

            int c1 = Math.max(k - n + m, 0);
            int c2 = Math.min(m, k);

            int lnode = m + k - 2 * c2;
            int rnode = m + k - 2 * c1;

            TreeSet<Integer> set =
                (lnode % 2 == 0) ? even : odd;

            Integer next = set.ceiling(lnode);

            while (next != null && next <= rnode) {
                dist[next] = dist[m] + 1;
                q.offer(next);
                set.remove(next);
                next = set.ceiling(lnode);
            }
        }

        return dist[0];
    }
}