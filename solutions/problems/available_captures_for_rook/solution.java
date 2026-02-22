class Solution {
    public int numRookCaptures(char[][] board) {

        int rookRow = 0, rookCol = 0;

        // Step 1: Find rook
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookRow = i;
                    rookCol = j;
                }
            }
        }

        int count = 0;

        // Step 2: Check 4 directions

        // Up
        for (int i = rookRow - 1; i >= 0; i--) {
            if (board[i][rookCol] == 'B') break;
            if (board[i][rookCol] == 'p') {
                count++;
                break;
            }
        }

        // Down
        for (int i = rookRow + 1; i < 8; i++) {
            if (board[i][rookCol] == 'B') break;
            if (board[i][rookCol] == 'p') {
                count++;
                break;
            }
        }

        // Left
        for (int j = rookCol - 1; j >= 0; j--) {
            if (board[rookRow][j] == 'B') break;
            if (board[rookRow][j] == 'p') {
                count++;
                break;
            }
        }

        // Right
        for (int j = rookCol + 1; j < 8; j++) {
            if (board[rookRow][j] == 'B') break;
            if (board[rookRow][j] == 'p') {
                count++;
                break;
            }
        }

        return count;
    }
}