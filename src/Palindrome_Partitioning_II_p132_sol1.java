/*
Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/**
 * DP problem
 * 
 * The tricky part is to come with the idea of extending palin substring from mid to find the longest substring which will minimize the num of cut
 * 
 * The basic idea is to build a DP table. The value in DP array means the minimal cut we need to partition previous substring into
 * palindrome substrings. If we find substring from a to b is a palindrome substring, then we know the min cut to cover substring from 0
 * to b is the min cut value stored in dp[a -1] plus 1. In other words, we just need an extra cut to cover the substring from a to b. 
 * There may be several ways to reach cover current substring, but we only need to memorize the one with min Cut, so each time we update the DP[],
 * we always use the Min() before update the value.
 * 
 * There are several things that we need to notice: A valid palindrome substring may have one or two mid char(s). when we search for the palindrome substring,
 * we have to cover both cases. Besides, to cover the case that we have a substring from 0 to b that is palindrome, we have to add an extra cell in head of
 * dp array with value -1, so it will make our update equation dp[a-1] + 1 still be valid for such case
 *  
 * 
 * Remark:
 * 1) Since we add an extra cell in head, we have to deduct 1 from index in array in order to get the corresponding char in string!!!!!!!!
 * 2) Time complexity is O(n^2)
 * @author hpPlayer
 * @date Oct 25, 2015 9:44:38 PM
 */

public class Palindrome_Partitioning_II_p132_sol1 {
	public static void main(String[] args){
		Palindrome_Partitioning_II_p132_sol1 sol = new Palindrome_Partitioning_II_p132_sol1();
		System.out.println(sol.minCut("bb"));
	}
    public int minCut(String s) {
        int n = s.length();
        
        //value in dp array means the min cut we need to partition substring so far into palindrome substrings
        
        //we add an extra cell in the head to cover the case that substring from 0 to curr index is a palindrome
        int[] dp = new int[n + 1];
        
        //Initialize the dp table with max cuts we need
        for(int i = 0; i <= n; i++){
            //the max cut to partition string s into palindrome substrings is just cut between each char pair
            //so if we have n chars so far, (n-1) cuts is enough.
            dp[i] = i - 1;
        }
        
        //update the dp table to find the min cut
        for(int i = 1; i <= n; i++){
            
            //Notice: since we add an extra cell in head, all indexes in array are 1 unit larger than the real
            //index in string. Therefore, if we need to get a char based on index in dp[], we need -1 
            
            //single core case
            for(int a = i, b = i; a > 0 && b <=n &&s.charAt(a-1) == s.charAt(b-1); a--, b ++){
                //if we find substring from a to b is palindrome
                //then we just need an extra cut before a to cover the substring from a to b
                dp[b] = Math.min(dp[b], dp[a-1] + 1);
            }
            
            //double core case
            for(int a = i, b = i + 1; a > 0 && b <= n && s.charAt(a-1) == s.charAt(b-1); a--, b ++){
                //same as above
                dp[b] = Math.min(dp[b], dp[a-1] + 1);                
            }
        }
        
        
        return dp[n];
    }
}
