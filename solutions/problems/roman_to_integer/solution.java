class Solution {
    public int romanToInt(String s) {
        int total = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int value = 0;
            
            if (s.charAt(i) == 'I') value = 1;
            else if (s.charAt(i) == 'V') value = 5;
            else if (s.charAt(i) == 'X') value = 10;
            else if (s.charAt(i) == 'L') value = 50;
            else if (s.charAt(i) == 'C') value = 100;
            else if (s.charAt(i) == 'D') value = 500;
            else value = 1000; // M
            
            if (i < s.length() - 1) {
                int next = 0;
                
                if (s.charAt(i + 1) == 'I') next = 1;
                else if (s.charAt(i + 1) == 'V') next = 5;
                else if (s.charAt(i + 1) == 'X') next = 10;
                else if (s.charAt(i + 1) == 'L') next = 50;
                else if (s.charAt(i + 1) == 'C') next = 100;
                else if (s.charAt(i + 1) == 'D') next = 500;
                else next = 1000;
                
                if (value < next)
                    total -= value;
                else
                    total += value;
            } else {
                total += value;
            }
        }
        
        return total;
    }
}
