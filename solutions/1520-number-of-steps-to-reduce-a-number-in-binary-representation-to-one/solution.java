class Solution {
    public int numSteps(String s) {

        int steps = 0;
        int carry = 0;

        // traverse from right to left (ignore first bit)
        for (int i = s.length() - 1; i > 0; i--) {

            int bit = s.charAt(i) - '0';

            if (bit + carry == 1) {
                // odd → add 1 then divide
                steps += 2;
                carry = 1;
            } else {
                // even → divide
                steps += 1;
            }
        }

        // if carry remains at MSB
        return steps + carry;
    }
}
