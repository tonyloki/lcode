import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        // Step 1: Create robot array
        int[][] robots = new int[n][4]; 
        // {position, health, direction(0=L,1=R), original index}

        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = directions.charAt(i) == 'L' ? 0 : 1;
            robots[i][3] = i;
        }

        // Step 2: Sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // If moving right → push
            if (robots[i][2] == 1) {
                stack.push(i);
            } else {
                // Moving left → collision
                while (!stack.isEmpty() && robots[i][1] > 0) {
                    int j = stack.peek();

                    if (robots[j][1] < robots[i][1]) {
                        // Right robot dies
                        stack.pop();
                        robots[i][1] -= 1;
                        robots[j][1] = 0;
                    } else if (robots[j][1] > robots[i][1]) {
                        // Left robot dies
                        robots[j][1] -= 1;
                        robots[i][1] = 0;
                        break;
                    } else {
                        // Both die
                        stack.pop();
                        robots[i][1] = 0;
                        robots[j][1] = 0;
                        break;
                    }
                }
            }
        }

        // Step 3: Collect survivors
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            if (robots[i][1] > 0) {
                int originalIndex = robots[i][3];
                result[originalIndex] = robots[i][1];
            }
        }

        // Step 4: Build answer
        List<Integer> ans = new ArrayList<>();
        for (int x : result) {
            if (x != -1) ans.add(x);
        }

        return ans;
    }
}