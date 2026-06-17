class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            long cur = len[i];

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = cur + 1;
            } else if (ch == '*') {
                len[i + 1] = cur > 0 ? cur - 1 : 0;
            } else if (ch == '#') {
                len[i + 1] = cur * 2;
            } else { // '%'
                len[i + 1] = cur;
            }
        }

        long finalLen = len[n];
        if (k >= finalLen) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = len[i];
            long curLen = len[i + 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == prevLen) {
                    return ch;
                }
                // character came from the previous string
            } else if (ch == '*') {
                // indices map directly to the previous string
            } else if (ch == '#') {
                k %= prevLen;
            } else { // '%'
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}
