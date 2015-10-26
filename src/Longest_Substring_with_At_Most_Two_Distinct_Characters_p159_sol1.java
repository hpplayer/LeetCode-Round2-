import java.util.*;

/*
Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = ¡°eceba¡±,

T is "ece" which its length is 3.
*/

/**
 * sliding window solution
 * 
 * The tricky part in this problem is to deal with case like aba, where chars are not consecutive. So we need to modify our sliding 
 * window algorithm.
 * 
 * This solution is not intuitive, even after I have carefully studied the logic behind it
 * 
 * The basic idea is still using two pointers to build a window. But this time, the window will only cover consecutive chars, like:
 * aaaabbbbb. For other valid cases like aaaaa or aaaabbbbaaa, the window will not cover all of chars. The reason for us to do that 
 * is to make it convenient for us to slide window. For example if we got aaabbbbaaaaac, then we can just move left pointer to the 
 * right of right pointer, otherwise we don't know how to slide the window. So in this solution, the second pointer always points to
 * the last index of second char. We will keep the window with same size, when we got first char after second char. Only after we got
 * third char, shall we move the left and right pointer accordingly.
 * 
 * Remark:
 * 1) Don't forget compare the last string ends at tail!!!!!!!
 * 2) We have official solution in manuals
 * 
 * Sol1 provides a very problem specific solution and can only applies to allowing at most 2 Distinct Characters
 * Sol2 provides another sliding window solution with HashMap
 * 
 * I prefer sol2 which is more intuitive and can be applied to general case
 * 
 * This problem is similar to problem:
 *  Longest_Substring_Without_Repeating_Characters_p3_sol1,
 *  Minimum_Window_Substring_p76_sol1
 *  Substring_with_Concatenation_of_All_Words_p30_sol1
 *  
 * @author hpPlayer
 * @date Oct 26, 2015 12:08:47 AM
 */

public class Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1 {
	public static void main(String[] args){
		Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1 sol = new Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol1();
		System.out.println(sol.lengthOfLongestSubstringTwoDistinct("aba"));
	}
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        
        //left points to the first index of first char
        //right points to the last index of second char (at least after we found a second char)
        
        //this is not a standard sliding window problem, since the window will not cover all valid chars
        //for example abba, right bound points to index 2, even index 3 is also a valid char
        int left = 0, right = - 1, result = 0;
        
        
        for(int i = 1; i < s.length(); i++){
            // do nothing when getting same char. Except for inital case, we are extending the first char after second char
            //ex: abbaaa
            if(s.charAt(i) == s.charAt(i-1)) continue;
            
            //if current char is different, then we have two cases.
            if(right != -1 && s.charAt(right) != s.charAt(i)){
              //case 1: this char is differed from second char, then it means this char is third char
              //so we have to readjust window and update result
              
              result = Math.max(result, i - left);
              
              //move left bound to be the right of right bound
              left = right + 1;
            }
            
            //case 2: we don't have right bound yet, we just update it
            
            right = i - 1;
        }
        
        //boundary case, if our ending char is not third char
        return Math.max(result, s.length() - left);
    }
}
