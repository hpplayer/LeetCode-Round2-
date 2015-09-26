/*
Reverse Words in a String II

Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
*/

/**
 * Array Rotate problem
 * 
 * This problem is very similar to problem rotate array(p189), the tricky part is come up with the idea of rotate subpart and whole array,
 * and dealing with the last word 
 * 
 * if we need reverse ab, we can firstly get a^rb, then we can get a^rb^r, then we can get (a^rb^r)^r = ba (see ProgrammingPearls2nd)
 * i.e we can firstly reverse each word in input array, then reverse the input string as a whole
 * When we rotate each word, we always detect the space after word, so we know where the word ends, then rotate, but for the last word
 * we don't have space after it. So the trick is to let loop end until we reach s.length, here we treat s.length same as the stop sign " "
 * After wo rotate each bit, we can just rotate whole array and get result
 * 
 * So for those array rotating problem, we can divide the array into several parts, then rotate those parts, then rotate the whole array.
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 12:22:30 AM
 */
public class Reverse_Words_in_a_String_II_p186_sol1 {
    public void reverseWords(char[] s) {
        int left = 0;
        
        //we let right stop at s.length to deal with the last word, so s.length is same as " "
        for(int right = 0; right <= s.length; right++){
            if(right == s.length || s[right] == ' '){
                //we only reverse word, so we will skip ' '
                reverse(left, right -1, s);
                left = right + 1;
            }
        }
        
        reverse(0, s.length - 1, s);
    }
    
    public void reverse(int left, int right, char[] s){
        while(left < right){
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left ++;
            right --;
        }
    }
}
