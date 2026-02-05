class Solution {
    public int findNumbers(int[] nums) {
        int Even=0;
        for(int n:nums){
            int count=0;
            while(n>0){
                n/=10;
                count+=1;
            }
            if(count%2==0){
                Even+=1;
            }
        }
        return Even;

    }
}