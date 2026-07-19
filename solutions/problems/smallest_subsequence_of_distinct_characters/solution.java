class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();

        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (visited[ch - 'a']) continue;

            while (!stack.isEmpty()
                    && stack.peekLast() > ch
                    && last[stack.peekLast() - 'a'] > i) {

                visited[stack.removeLast() - 'a'] = false;
            }

            stack.addLast(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.removeFirst());
        }

        return ans.toString();
    }
}