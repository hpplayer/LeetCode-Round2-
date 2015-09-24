/**
 * DP solution.
 * 
 * The difficulty is how to design the dp matrix.
 * 
 * In this solution, we treat the index of row as the start index, then we update the dp matrix based on length of palindrome that 
 * we are looking at. if dp[i][j] = true, it means the substring from i to j is palindrome, and the cell is visited when we are looking
 * at palindrome of len (j-i + 1) (remark: convert from len to index, we -1, convert from index to len, we + 1). 
 * 
 * To start with, we firstly fill the dp[][] with palindrome of 1 and 2, since they are base cases and cannot be referred from other cells.
 * To fill dp[i][j], we firstly compare char at i and at j, if they are same, we then compare the palindrome from i + 1 to j - 1
 * This maybe hard to come up with, instead of looking a top or left cell, we should look at the left bottom cell, which tells
 * us if the substring from i+1 to j -1 is palindrome. As long as we found a new palindrome, we will update our result, as the length
 * is ascending, the new palindrome should always be the longer one
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 11:13:22 PM
 */
public class Longest_Palindromic_Substring_p5_sol2 {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[1000][1000];
        
        //single mid case
        for(int i = 0; i < s.length(); i++){
            dp[i][i] = true;
        }
        
        //at least we have a palindrome with len 1, start from in 0
        int maxLen = 1;
        int start = 0;
        
        //double mid case
        for(int i = 0; i < s.length()-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxLen = 2;//if we have plaindrome of len2
            } 
        }
        
        //we have checked len 1,2, so now we check from len = 3
        //start from len 3, we will skip a lot of checked cells in dp[][]
        for(int len = 3; len <= s.length(); len++){
            //our start point could not be later than dp.length - len, as we won't have enough chars left
            for(int i = 0; i <= s.length() - len; i++){
                int j = i + len - 1;//get y index, len is 1 based, so use len -1 to find correct j 
                
                //to extend the palindrome, we need to look at the next char of i, and prev char of j
                //i.e. to check the substrings between i and j and see if they are palindrome
                //since we update the dp mainly based len, so we should already got this result, as it 
                //is a substring with shorter length (updates 2 loops before, as its shorter by 2 chars)
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){
                    dp[i][j] = true;
                    maxLen = len;//the outer loop is len, so we can directly replace max, if found a new Palin
                    start = i;
                }
            }
        }
        
        return s.substring(start, start + maxLen);
        
    }
}
