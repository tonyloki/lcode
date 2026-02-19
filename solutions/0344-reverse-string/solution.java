class Solution {
    public void reverseString(char[] s) {
        int l=s.length;
        for(int i=0,j=l-1;i<=j;i++,j--){
            char temp=s[i];
            s[i]=s[j];
            s[j]=temp;
        }
        System.out.println(s);

    }
}
