import java.util.Arrays;

/*
Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "a*") ¡ú true
isMatch("aa", ".*") ¡ú true
isMatch("ab", ".*") ¡ú true
isMatch("aab", "c*a*b") ¡ú true
*/

/**
 * Solution1 : a pure DP approach
 * Even this is the second time I am doing this problem, I still think it is very hard.
 * We build a matrix that each row is the result of matching a char in string with each char in pattern
 * Each column is the result of matching a char in pattern with each char in string.
 * To treat the initial char as same with other chars, we add "" in front of both input strings. Therefore, index i, j in matrix
 * actually becomes i - 1 and j - 1 in input strings
 * 
 * We can easily deal with alphabetic and '.' pattern, by look at dp[i-1][j-1], compare current pair of chars
 * But for '*' pattern, we will have two cases:
 * 1) We can use * to remove preceding element. Thus we need to check the value dp[i][j-2]. (j is *, j-1 is preceding element)
 * 2) We will use at least one occurrence of preceding element. Thus, firstly we need to check if current char(i) in string is 
 * same with preceding char (j-1). Then we keep the index in pattern still, and check the result of matching previous char in string
 * with current pair of "preceding element + *", which is like we are using the pair repeatedly to match the chars in string s
 * 
 * Besides, be careful with boundaries. If we are querying dp[i-1][j], then i must >= 1. To be convenient, we will not look
 * at first column since the match of empty pattern with other strings (except empty string) will not be true.
 * 
 * 
 * Finally, we just need to return the last cell in matrix, which tells the matching result of whole pattern with string 
 * 
 * Remark:
 * This problem is very similar to problem Wildcard Matching(p44), we can treat them as a combo practice
 * 
 * @author hpPlayer
 * @date Sep 18, 2015 5:21:30 PM
 */
public class Regular_Expression_Matching_p10_sol1 {
	public static void main(String[] args){
		System.out.println(new Regular_Expression_Matching_p10_sol1().isMatch("", "."));
	}
	
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        
        //we make matrix one unit higher and wider to treat the first char in string
        //same as other chars, otherwise we will have a specific loop to initalize the first row and first col
        //So dp[i][j] actually match the substring from 0 to i (exclusive) in string s with the substring from
        //0 to j (exclusive) in pattern p
        boolean dp[][] = new boolean[m+1][n+1];
        
        
        //empty pattern match other string will always return false, except the string is also empty
        //so it means our first col will always be false except the first cell
        //but empty string may match several patterns due to "*" like empty, like ".*", ".*.*", so first row may
        //have several true
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                //each inner loop will look at a pair of (i, j), but due to the extra inital col and row
                //our index in string s and p is actually i -1 and j -1
                //just alphabets
                if(p.charAt(j-1) != '*' && p.charAt(j-1) != '.'){
                    //if we are looking at index i > 0 i.e. non empty string s
                    //if index i -1 and index j - 1 have perfect match
                    //if current chars at index i and index j match
                    //notice: index i and j is index in matrix, for string, we need convert to i -1 and j -1
                    dp[i][j] = i > 0 && dp[i-1][j-1] && s.charAt(i-1) == p.charAt(j-1);
                }else if(p.charAt(j-1) == '.'){
                    dp[i][j] = i > 0 && dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    //we don't need to check if j >=2, why? Because we must have an element before *,
                    //and our j start from 1, thus the index of "*" must >= 2
                    boolean zeroOccur = dp[i][j-2];//if we can remove the preceding element of *
                    
                    //if we need use *, then we firstly compare the preceding element with current char in string
                    //if they match, then we check the previous result of using this * to match previous char in
                    //string s. Be careful, here we mean the result of matching substring of [0, i-1] with pattern
                    //of [0, j]. So it is not enough not to only compare a single pair of char
                    //example: a*..... 
                    //        aa .....
                    //we firstly compare the preceding "a" with current char a, and make sure we can use * to match
                    //more preceding element. So we will check dp[i-1][j], which is the result of matching * with s[0,i-1]
                    
                    //why we look up the cell above it? because that cell contains the maximum possibility of matching previous chars
                    //In previous char, we can match without using *, then this cell will get true value, if we must use * to match
                    //then we will get true value too as we used the "*".
                    //So we look up the cell above it and that cell will tell whether previous parts could match by considering the case 
                    //of using and not using *
                    boolean multOccur = i > 0  && ((p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') && dp[i-1][j]);
                    dp[i][j] = zeroOccur || multOccur;
                }
            }
        }
        
        return dp[m][n];
        
    }
}
