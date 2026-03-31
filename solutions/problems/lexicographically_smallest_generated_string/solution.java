class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;

        char[] word = new char[len];
        boolean[] fixed = new boolean[len];

        // Step 1: initialize
        for (int i = 0; i < len; i++) {
            word[i] = '?';
        }

        // Step 2: apply 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    if (word[pos] == '?' || word[pos] == str2.charAt(j)) {
                        word[pos] = str2.charAt(j);
                        fixed[pos] = true; // lock it
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < len; i++) {
            if (word[i] == '?') {
                word[i] = 'a';
            }
        }

        // Step 4: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean fixedDone = false;

                    for (int j = m - 1; j >= 0; j--) {
                        int pos = i + j;

                        if (fixed[pos]) continue; 

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != str2.charAt(j)) {
                                word[pos] = c;
                                fixedDone = true;
                                break;
                            }
                        }

                        if (fixedDone) break;
                    }

                    if (!fixedDone) return "";
                }
            }
        }

        return new String(word);
    }
}