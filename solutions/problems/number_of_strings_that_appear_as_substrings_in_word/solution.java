class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;

        for (String p : patterns) {
            if (word.indexOf(p) != -1) {
                ans++;
            }
        }

        return ans;
    }
}