import java.util.*;

/*
Word Break II

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

/**
 * DP + backtracking problem
 * 
 * The tricky part is to come up with the idea of using dp table to contain the info on substring behind.
 * 
 * This problem can be solved by pure backtracking solution, but that would be too costly. Because we may build a long substring, but find 
 * the last substring is not in dict, so we wasted a long time but get nothing. To avoid such case, we will build a dp table that indicates
 * whether the substring behind index i can be composed of words in dict. So we need to build the dp table backward, the way we used to update
 * cells is similar to solution of problem Word Break(p139). 
 * 
 * After fill the dp table, the next step is to build sentences and we will do this step with DFS or more specifically backtracking. we use a
 * pointer in original string to indicate which string we are now looking at. We keep the index consistent with original string, so we can use
 * same index to look up dp table. If we find current substring is contained in dict, and the dp table also indicates the later substring can 
 * be composed from words in dict, in such case, we will call our DFS on later substring and build the string recursively.
 * 
 * Remark:
 * I believe we can translate it into iterative solution with inner class. But due to the time limit, I will not translate it here
 * 
 * @author hpPlayer
 * @date Oct 24, 2015 5:39:10 PM
 */

public class Word_Break_II_p140_sol1 {
	public static void main(String[] args){
		String s = "catsanddog";
		Set<String> hs = new HashSet<String>();
		hs.add("cat");
		hs.add("cats");
		hs.add("and");
		hs.add("sand");
		hs.add("dog");
		System.out.println(new Word_Break_II_p140_sol1().wordBreak(s, hs));
	}
    public List<String> wordBreak(String s, Set<String> wordDict) {
        //to prune early, we need to know if the substring behind can be composed from words in dict
        int len = s.length();
        //whether substring starts from i to end can be composed from words in dict
        boolean dp[] = new boolean[len];
        
        //build dp table backward
        for(int i = len -1; i >= 0; i--){
            for(int j = i; j < len; j++){
            	//if we found substring(i, j+1) exists in dict, and substring(j+1, tail) is valid, then we update dp[i] = true
                if( wordDict.contains(s.substring(i, j + 1)) && (j == len - 1 || dp[j+1])){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        List<String> result = new ArrayList<String>();
        
        //we can't create a string from 0 to len -1, return empty list 
        if(!dp[0]) return result;
        
        for(int i = 0; i < len; i++){
            //if wordDict contains substring from 0 to i, and dp table tells us subtring from i + 1 to end
            //can also be made from words in wordDict, then we will do DFS
            String newStr = s.substring(0, i + 1);
            if( wordDict.contains(newStr) && (i == len - 1 || dp[i+1]) ){
                 DFS(result, newStr, s, i + 1, dp, wordDict);
            }
        }
        
        return result;
    }
    
    
    public void DFS(List<String> result, String temp, String s, int index, boolean[] dp, Set<String> wordDict){
        if(index == s.length()){
            result.add(temp);
            return;
        }
        
        for(int i = index; i < s.length(); i++){
            String newStr = s.substring(index, i + 1);
             if( wordDict.contains(newStr) && (i == s.length() - 1 || dp[i+1]) ){
                 DFS(result, temp + " " + newStr, s, i + 1, dp, wordDict);
             }
        }
    }
}
