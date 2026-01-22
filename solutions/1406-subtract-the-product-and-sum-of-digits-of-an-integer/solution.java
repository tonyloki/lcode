class Solution {
    public int subtractProductAndSum(int n) {
        int s=0;
        int c=1;
        while (n>0){
            int digit=n%10;
            s+=digit;
            c*=digit;
            n=n/10;
        }
        int result=c-s;
        return result;
    }
    
}
