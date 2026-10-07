class Solution {
    public boolean valid(String s) {
        int cnt = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                cnt--;

                if (cnt < 0) {
                    return false;
                }
            }
        }

        return cnt == 0;
    }

    public List<String> removeInvalidParentheses(String s) {
        List<Integer> pos = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                pos.add(i);
            }
        }

        int p = pos.size();
        int minRemove = p;
        Set<String> st = new HashSet<>();

        for (int mask = 0; mask < (1 << p); mask++) {
            int removed = Integer.bitCount(mask);

            if (removed > minRemove) {
                continue;
            }

            StringBuilder temp = new StringBuilder();
            int j = 0;

            for (int i = 0; i < s.length(); i++) {
                if (j < p && pos.get(j) == i) {
                    if ((mask & (1 << j)) != 0) {
                        j++;
                        continue;
                    }

                    j++;
                }

                temp.append(s.charAt(i));
            }

            String tempStr = temp.toString();

            if (valid(tempStr)) {
                if (removed < minRemove) {
                    minRemove = removed;
                    st.clear();
                }

                st.add(tempStr);
            }
        }

        return new ArrayList<>(st);
    }
}
