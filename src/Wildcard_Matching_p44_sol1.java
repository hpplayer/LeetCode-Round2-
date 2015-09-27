import java.util.Arrays;

/*
Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "*") ¡ú true
isMatch("aa", "a*") ¡ú true
isMatch("ab", "?*") ¡ú true
isMatch("aab", "c*a*b") ¡ú false
*/


/**
 * DP solution
 * 
 * This problem is very similar to problem Regular Expression Matching (p10)
 * The difficulty is to deal with "*"
 * 
 * We firstly build a matrix of (m+1) * (n+1) where m is the length of string, n is the length of pattern. We choose m+1 and n+1 because
 * we want to treat the initial char as general case. So in our matrix, first column and first row stands for empty case "". The value in
 * matrix means whether the string from 0-i will match the pattern from 0-j. dp[0][0] = true since empty string matches empty pattern "" is
 * a match. Then we will fill the matrix. For general case (non * and ?), we need compare the match of current char, and the match result
 * of previous substring. For (?), it will be similar. But for (*), we need consider two cases 1) not use this *, then we will try to match
 * current char in string with the previous char in pattern. 2) we use *, then we will use the result of match previous char in string with
 * current * in pattern. Why? because we may already use * in previous match. If we do that, then current match is just use another extra
 * "*" in matching or we may not use "*" in previous match, then this result will also contain the corresponding result. In other words,
 * if we can use some ways to make previous char in s match with * in p, either by using * or not using *, we will always get the true value.
 * Otherwise we couldn't match previous char, then current match result will also be false.
 * 
 * Since for each cell, we at most just need two previous cell, top-left, or top, in below solution we optimize the matrix into an array.
 * So the time complexity is O(mn), and the space complexity is O(n), where n is the length of pattern
 * 
 * Sol1 use dp solution. We will get result after whole matrix is filled O(mn)
 * Sol2 use backtracking solution. We will get result immediately if we couldn't find a match. The worst case, I would say it still
 * O(mn), but should be much faster, since we will report false immediately
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 9:42:44 PM
 */

public class Wildcard_Matching_p44_sol1 {
	public static void main(String[] args){
		System.out.println( new Wildcard_Matching_p44_sol1().isMatch("aa"
				,"aa"));
	}
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        //create an extra col to treat first char as general case
        boolean[] dp = new boolean[n+1];
        dp[0] = true;//boundary case, "" match ""
        
        for(int i = 0; i <= m; i++){
            boolean prev = dp[0];
            //we will change dp[0] to false from second row since we need let prev record the last row
            if(i > 0) dp[0] = false;
            
            for(int j = 1; j <= n; j++){
                //record the value before change
                boolean temp = dp[j];
                //dp[] has extra col/row in front, so we need use i-1, j-1 to get char in string
                if(p.charAt(j-1) != '?' && p.charAt(j-1) != '*'){
                    dp[j] = i > 0 && prev && s.charAt(i-1) == p.charAt(j-1);
                }else if(p.charAt(j-1) == '?'){
                    dp[j] = i > 0 && prev;
                }else{//"*" case
                    boolean zeroUse = dp[j-1];// skip *
                    //why we look up the cell above it? because that cell contains the maximum possibility of matching previous chars
                    //In previous char, we can match without using *, then this cell will get true value, if we must use * to match
                    //then we will get true value too as we used the "*".
                    //So we look up the cell above it and that cell will tell us whether previous parts could match if there exist a such way
                    
                    boolean multiUse = dp[j];//use * to match char, special case
                    
                    dp[j] = zeroUse || multiUse;
                }
                
                prev = temp;
            }
        }
        
        return dp[n];
    }
}
