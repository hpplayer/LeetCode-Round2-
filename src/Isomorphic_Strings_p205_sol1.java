/*
Isomorphic Strings 

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

/**
 * HashMap
 * 
 * This solution is very intuitive. The tricky part is how to set initial value in HashMap
 * 
 * In the problem, we need a one-to-one mapping relationship between each char in string s and t
 * ex:
 * string s: a b   
 * string t: b a
 * In above relationship, char a in s maps to char b in t while char b in s maps to char a in t
 * So we need at least two hashMap to record the relationship. In sol1, we use an array of 256 length to be used as hashMap
 *  
 * Remark:
 * integer 0's char value is 48
 * char value 0's corresponding value is NUL or simply as ''
 * So we can directly use array's initial value 0, as it does not represent a valid char, at least not a valid char in our string
 * Example: string s is "" while string t is "a", then s and t even do not have same length, but the problem states that 
 * we would have same length, so this would return false
 * 
 * Sol1 provides a hashMap solution that uses char value as key and value
 * Sol2 provides a hashMap solution that uses char value as key but index as value
 * 
 * for me I think sol2 is better since we will not mix initial value with char value in the cell, also sol2 is shorter
 * We have another problem Word Pattern (p290) that can be solved with a similar solution. That problem also needs a one-to-one relationship
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 11:10:13 PM
 */
public class Isomorphic_Strings_p205_sol1 {
    public boolean isIsomorphic(String s, String t) {
    	if(s.length() != t.length()) return false;
    	
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        
        for(int i = 0; i < s.length(); i++){
            if(map1[s.charAt(i)] == -1 && map2[t.charAt(i)] == -1){
                map1[s.charAt(i)] = t.charAt(i);
                map2[t.charAt(i)] = s.charAt(i);
            }else{
                if( map1[s.charAt(i)] != t.charAt(i) || map2[t.charAt(i)] != s.charAt(i)){
                    return false;
                }
            }
        }
        
        return true;
    }
}
