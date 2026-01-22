class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int s=0;
        int or=x;
        while(x>0){
int r=x%10;
s=s+r;
x=x/10;
        }
        if(or%s==0){
            return s;
        }
        else{
return -1;
        }
    }
}