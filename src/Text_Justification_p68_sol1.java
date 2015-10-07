import java.util.*;

/*
Text Justification

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.

*/

/**
 * String problem
 * 
 * This solution is very problem specific, if I need to assign it to a category, then I will call it Greedy. For each line, we always try
 * to assign as much words as possible, i.e. we meet the min requirement just add one space after each word. 
 * 
 * The tricky part is to handle corner cases: 1) the last line 2) a line contains single word. For general cases, we just need to 
 * use % and / operations to calculate the spaces we need to add after each word (except the last word). For those two special cases,
 * for case 1, we firstly add one space after each word, then add all required spaces after the last word, for case 2) we can treat it 
 * as a variation of case 1, where we only have the last word, so we just need to add all required spaces after the last word, i.e. the 
 * single word itself.
 * 
 * Remark:
 * A beautiful trick here is to use math equation to calculate spaces needed for each line. It saves a lot of time comparing with try-and-test
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 5:34:02 PM
 */
public class Text_Justification_p68_sol1 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        
        //i the main pointer that skip line by line
        //j is the second pointer that skip word by word
        //after each loop, j will be the first word of next line
        //so the increment of i is i = j
        for(int i = 0, j = 0; i < words.length; i = j){
            //len is the initial length of line, which is simply collections of (one word + one space)
            //but we won't have spaces after last word, so we set the initial value to -1 indicating last space
            //is removed
            int len = -1;
            //as long as we can fit (one word + one space), we will add words to current line
            for(j = i; j < words.length && len + words[j].length() + 1 <= maxWidth; j++){
                len += words[j].length() + 1;
            }
            
            int spaces = 0;//spaces we need to add after each word
            int extras = 0;//extra spaces that we need to add as left as possible
            
            //use math way to calculate spaces and extras
            
            if(j == words.length){//j == words.length indicating current line is last line
                //for the last line, we only add one spaces after each word
                spaces = 1;
            }else{
                //for the line contains only one word, we won't calculates spaces and extras, since 
                //we can only add spaces after it
                if(i + 1 != j){
                 //j - i - 1 is the count of spaces in current line, like j - i is the word length, j - i - 1
                //is the count of spaces betten them                   
                    spaces = (maxWidth - len) / (j - i - 1) + 1;
                    extras = (maxWidth - len) % (j - i - 1);                    
                }
            }
            
            //build string and append spaces after each word
            StringBuilder sb = new StringBuilder();
            
            for(int l = i; l < j; l++){
                sb.append(words[l]);
                if(l == j - 1) break;//break after we insert the last word, as we don't add spaces after it
                
                for(int m = 0; m < spaces; m++){
                    sb.append(" ");
                }
                
                if(extras > 0){
                    sb.append(" ");
                    extras --;
                }
            }
            
            //dealing with last line or line with single word(which can also be treated as head)
            int remaining = maxWidth - sb.length();
            while(remaining > 0){
                remaining --;
                sb.append(" ");
            }
            
            result.add(sb.toString());
        }
        
        return result;
    }
}
