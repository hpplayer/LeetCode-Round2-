import java.util.Arrays;

/*
Edit Distance

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/**
 * a classic DP problem
 * 
 * We will firstly create a DP matrix, each cell represents the min value to match word1 to word2 ended at this cell.
 * When update each cell, we will have two cases 1) current char in two strings match, then we simply lookup the the min effort
 * we need to match subStrings ended at dp[i-1][j-1], and copy the value 2) if current char in two strings does not match, then we 
 * need do operation to make them match. We can either replace, insert or delete char in word1, we will choose the one gives min effort.
 * Effort to insert char in word1 can be looked up from dp[i][j-1], that's like we use a virtual char in word1 to match current char in word2
 * Effort to delete char in word1 can be looked up from dp[i-1]j], that's like we delete current char in word1, and have to use previous char
 * in word1 to match current char in word2
 * Effort to replace char in word1 can be looked up from dp[i-1][j-1], that's like we still use current char in word1 to match char in word2
 * 
 * Below solution optimizes the DP[][] to DP[], due to the fact that for each cell, we only need the value on the left, on the top and 
 * on the left-top
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 2:56:45 PM
 */
public class Edit_Distance_p72_sol1 {
	public static void main(String[] args){
		System.out.println(new Edit_Distance_p72_sol1().minDistance("zoologicoarchaeologist", "zoogeologist"));
		
	}
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        
        //we only need the top, left and left-top value, so we can use an array instead of dp matrix
        //each row represents a char in word1, each colmn represents a char in word2
        //To treat first char as general case, we add an extra column and row before two strings
        //those extras can be treated as empty char
        int[] dp = new int[n+1];
        
        //prev is the left-top value. Since we only have one array, top and left-top occupys the same
        //cell so, we will use a prev to record the left-top value
        int prev = 0;
        
        //initial first row i.e. use empty char in word1 to match chars in word2
        for(int i = 1; i <=n; i++){
            dp[i] = dp[i-1] + 1;
        }
        
        for(int i = 1; i <= m; i++){
            //initialize the first column for each row, i.e. use empty char in word2 to match word1
            //we will do this on fly
            prev = dp[0];
            dp[0] ++;
            
            for(int j = 1; j <= n; j++){
                //1. since we add extra col/row ahead, to our real index in string, we should -1
                //2. since we need use prev to get dp[j], we need another variable "temp" to record old value
                //then we can update prev basesd on "temp"
                int temp = dp[j];
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                //current pair of chars match means we do nothing to current pair, we just copy
                //the effort to match dp[i-1][j-1]
                    dp[j] = prev;
                }else{
                //current pair of chars does not match means we need to an operation to make them match
                //we can either replace, or add or delete, we will pick the one with least effort
                //REPLACE: length does not change, we use dp[i-1][j-1] + 1
                //INSERT: we add an extra char into word1 to match word2, so it's like we keep pointer in
                //word1 stay still, and move pointer in word2 forward, i.e. we will use this virtual extra char to
                //match current char in word2, hence we use dp[i][j-1] + 1
                //DELETE: we remove a char in word1 to match word2, so it's like we keep pointer in word2 stay
                //still, and move pointer word1 backward to match current char in word2, hence we use dp[i-1][j] +1
                //dp[j-1] is value from left, dp[j] is value from top, prev is value from left-top
                    dp[j] = Math.min(dp[j-1], Math.min(dp[j], prev)) + 1;
                }
                
                //update prev to temp
                prev = temp;
            }
        }
        
        return dp[n];
    }
}
