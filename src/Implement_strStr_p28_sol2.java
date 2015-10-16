import java.util.*;

/**
 * KMP algorithm!
 * 
 * This is very good example of applying KMP algorithm in string match
 * We firstly build Partial Match Table, then start scan haystack to search match.
 * 
 * We have two pointers, i is pointer in haystack, j is pointer in needle. If current chars in i and j match, then we simply move two pointers
 * further to see next pair of chars. However, if we found current pair does not match, but we also have some matched pairs before, then we use 
 * table to decide how many chars to skip to find next match. If pointer in needle has reached its length(), then it means we have found our result
 * just return the start index by i - j + 1. 
 * 
 * Remark:
 * The key is building Partial Match Table, the value in each cell means how many front chars we can match in tail:
 * a b a b
 * 0 0 1 2
 * The value also means how many chars we can skip, Example:
 * a b a a b a b
 * a b a b
 *       * not match
 * when index 4 in needle does not match, , we look up table[index-1] to find previous match (index 3) result, now it gives 1,
 * it means we can right shift needle until previous char has been updated to the first char (index 0). So then, our current index will be updated
 * to the second char (index 1), and the needle has been right shift by (4 - 1) - 1 = 2 units. Anyway, by assigning current index to a previous index
 * in needle, it means we are right shifting the needle, so we can skip unnecessary matches and start a new search:
 * 
 * a b a a b a b
 *       a b a b
 *         * been updated char in table[index-1]
 *         
 * The algorithm costs O(len(haystack) + len(needle) time.
 *       
 * @author hpPlayer
 * @date Sep 21, 2015 1:42:28 PM
 */
public class Implement_strStr_p28_sol2 {
	public static void main(String[] args){
		System.out.println(Arrays.toString( buildTable("aac#aaaa")));
		System.out.println(new Implement_strStr_p28_sol2().strStr("mississippi", "issip"));// "babba", "bbb"
	}
    public int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length()) return -1;
        if(needle.length() == 0) return 0;
        
        int table[] = buildTable(needle);
        
        int j = 0;//pointer in needle
        //i is pointer in haystack
        for(int i = 0; i < haystack.length(); i++){
            if(haystack.charAt(i) == needle.charAt(j)){
                j++;//one match, move pointer one step further
                
                //since we have moved j, now we need move i as well to get correct index of i
                if(j == needle.length()) return i + 1 - j;
            }else if (j > 0){
                //if not match
                j = table[j-1];// move pointer in j, (len(j) - table[j-1] - 1) steps forward
                i--;//keep pointer in i still, so we can match char in i with updated char in j
            }
        }
        
        return -1;
    }
    
    public static int[] buildTable(String s){
        //Partial Match Table
        int[] result = new int[s.length()];
        
        int start = 0;//pointer that screen from begin
        
        //fill the table, i is the pointer that screen until tail
        for(int i = 1; i < result.length; i++){
            if(s.charAt(i) == s.charAt(start)){
                start ++;
                result[i] = start;
            }else{
                //if not match, reset start
                start = 0;
            }
        }
        
        return result;
    }
}
