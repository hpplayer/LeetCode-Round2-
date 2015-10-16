import java.util.*;

/*
Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/

/**
 * KMP solution!
 * 
 * The tricky part is to get understanding with KMP algorithm.
 * 
 * The problem can be solved by KMP with a trick.
 * 
 * The tricky is to build a new string like this s + '#' + reverse(s)
 * '#' is delimiter, so we will force the matching in reverse(s) begin with its initial char. 
 * Here we don't use KMP algorithm to compare strings, but we will make use of KMP table to find the match result of first and second
 * part in the string. Here first means the string starts from 0, second means the string ends at current index. 
 * In this way, we put the right substring that does not compose palindrome in the mid, so we can just search the left substring part
 * to find the palindrome 
 * 
 * ex:
 * abacd => abacd & dcaba
 * cd is in mid, and we found aba is the palindrome, so we just add the reverse of cd in front of s to build a palindrome string:
 * dc + abacd
 * 
 * Remark:
 * 
 * 1) There is another two-pointer solution, but it is very specific to this problem so I did not put it here.
 * If interested, please ref to p214_sol2 in round 1 project
 * 2) For the formal KMP algorithm, please ref to problem Implement_strStr_p28_sol2, where we will use the KMP table to shift needle
 * when current pair of chars does not match.
 * 3) I wrote a long post regarding my understanding of KMP and this problem, the URL is:
 * https://leetcode.com/discuss/64309/clean-kmp-solution-with-super-detailed-explanation
 * 
 * @author hpPlayer
 * @date Oct 16, 2015 2:33:22 PM
 */
public class Shortest_Palindrome_p214_sol1 {
	public static void main(String[] args){
		System.out.println(Arrays.toString(new Shortest_Palindrome_p214_sol1().getTable("ababbbabbaba")));
		//System.out.println(new Shortest_Palindrome_p214_sol1().shortestPalindrome("aacecaaa"));
	}
    public String shortestPalindrome(String s) {
        //build a temp string composed of s + reverse(s), so its like we are compare the substring from read forward with the substring read backward.
        //The match value recorded in last cell of KMP table will be the palindrome substring's length starts from
        //index 0 
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);
        
        
        //get the non-palindrom part, reversed it, then insert it before s, this will make curretn whole string become palindrome
        return new StringBuilder( s.substring(table[table.length-1]) ).reverse().toString() + s;
    }
    
    public int[] getTable(String s){
        int[] table = new int[s.length()];
        int pointer = 0;
        for(int i = 1; i< s.length(); i++){
           if(s.charAt(i) == s.charAt(pointer)){
        	   //if match, the value in cell is simply index of pointer + 1
        	   //ex: aaa, at index 2, pointer i at index 1, so the value is 1 + 1 = 2
        	   //So it can also be rewritten as:
        	   //pointer ++;
               //table[i] = pointer;
        	   //same rule applies matching case in else block
               table[i] = table[i - 1] + 1;
               pointer ++;
           }else{
               //try to find another string in first part that has same prefix
               pointer = table[i - 1];
               while(pointer > 0 && s.charAt(pointer) != s.charAt(i)){
                   //try all possible substring in first part that has same prefix
                   //During the search, we will shorten the length of prefix, or maybe lastly no prefix at all
                   //but as long as we follow the value in table[pointer - 1], we can always find string in 
                   //first part that has same prefix with different/same letter after prefix. We will stop at
                   //the string has letter after prefix that is same with current char at index i
                   pointer = table[pointer - 1];
               }
               
               //if we stop due to a match
               if(s.charAt(pointer) == s.charAt(i)){
                   //then we can extend the match by 1
                   //pointer current value is match result of prefix, which is similar to table[i-1] in above match case)
                   table[i] = pointer + 1;
                   pointer ++;
               }
           }
        }
        
        return table;
    }
}
