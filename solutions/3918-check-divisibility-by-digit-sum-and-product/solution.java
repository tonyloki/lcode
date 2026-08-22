class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProduct = 1;
        
        // Extract each digit one by one
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }
        
        // Check if the original number is divisible by the combined sum
        int totalSum = digitSum + digitProduct;
        return n % totalSum == 0;
    }
}

