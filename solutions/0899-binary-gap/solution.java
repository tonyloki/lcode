class Solution {
    public int binaryGap(int n) {

        int last = -1;     // position of last seen 1
        int maxGap = 0;
        int position = 0;

        while (n > 0) {

            if ((n & 1) == 1) {  // if last bit is 1
                if (last != -1) {
                    maxGap = Math.max(maxGap, position - last);
                }
                last = position;
            }

            n >>= 1;   // shift right
            position++;
        }

        return maxGap;
    }
}
