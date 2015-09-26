/*
Reverse Words in a String

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.

Could the input string contain leading or trailing spaces?

Yes. However, your reversed string should not contain leading or trailing spaces.

How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/

/**
 * String and two-pointer problem
 * 
 * The difficulty is to not use trim() and insert(0, x) to generate string
 * 
 * To avoid the use of insert(), we have to use append() and start backward.
 * We keep two pointer to mark the range of current word. our right pointer will always points to the space, and we will
 * begin insert new word, when left pointer reach head or its next char is ' ', i.e. we have found a whole word. Then we can
 * just append it to the result
 * 
 * This solution is based on the given official solution
 * @author hpPlayer
 * @date Sep 25, 2015 11:28:57 PM
 */

public class Reverse_Words_in_a_String_p151_sol1 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        
        //right index is always exclusive, so start with length
        int right = s.length();
        
        for(int left = s.length()- 1; left >= 0; left --){
            if(s.charAt(left) == ' '){
                //if curr char is ' ' wo do nothing but update right
                right = left;//update right
            }else{
                //if we found an valid char
                //and we reach head or the left bound of a word
                if(left == 0 || s.charAt(left-1) == ' '){
                    if(sb.length() != 0) sb.append(" ");//we add an extra space before non-head word
                    sb.append(s.substring(left, right));
                }
            }
        }
        
        return sb.toString();
    }
}
