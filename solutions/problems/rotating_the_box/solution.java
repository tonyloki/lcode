class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] res = new char[n][m];

        // Fill with empty cells
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], '.');
        }

        for (int i = 0; i < m; i++) {
            int emptyRow = n - 1; // lowest available row in rotated column

            for (int j = n - 1; j >= 0; j--) {

                if (box[i][j] == '#') {
                    // place stone at lowest available position
                    res[emptyRow][m - 1 - i] = '#';
                    emptyRow--;

                } else if (box[i][j] == '*') {
                    // place obstacle
                    res[j][m - 1 - i] = '*';
                    emptyRow = j - 1; // reset falling position above obstacle
                }
            }
        }

        return res;
    }
}