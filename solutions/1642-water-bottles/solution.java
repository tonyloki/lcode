class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {

        int totalDrank = 0;
        int empty = 0;

        while (numBottles > 0) {

            // Drink all full bottles
            totalDrank += numBottles;
            empty += numBottles;

            numBottles = 0;

            // Exchange empty bottles
            numBottles = empty / numExchange;
            empty = empty % numExchange;
        }

        return totalDrank;
    }
}
