class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        int sum = 0;
        long pow10 = 1;

        while (n > 0) {
            int d = n % 10;
            if (d != 0) {
                x += (long) d * pow10;
                pow10 *= 10;
                sum += d;
            }
            n /= 10;
        }

        return x * sum;
    }
}
