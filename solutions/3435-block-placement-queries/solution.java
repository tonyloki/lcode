import java.util.*;

class Solution {
    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) >>> 1;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(1, 0, n - 1, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return 0;

            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) >>> 1;

            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        List<Integer> obstacles = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        final int MAX = 50001;

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(MAX);

        for (int pos : obstacles) {
            set.add(pos);
        }

        SegmentTree seg = new SegmentTree(MAX + 1);

        int prev = 0;
        for (int cur : set) {
            if (cur != 0) {
                seg.update(cur, cur - prev);
            }
            prev = cur;
        }

        List<Boolean> answer = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];

                int prevObstacle = set.floor(x);

                int maxGapBeforeX = seg.query(0, x);
                int tailGap = x - prevObstacle;

                answer.add(Math.max(maxGapBeforeX, tailGap) >= sz);
            } else {
                int pos = q[1];

                int left = set.lower(pos);
                int right = set.higher(pos);

                seg.update(pos, 0);
                seg.update(right, right - left);

                set.remove(pos);
            }
        }

        Collections.reverse(answer);
        return answer;
    }
}
