/**
 * Two pointer problem.
 * This is the official solution, I put it here since it is so elegant, though it is using Brute Force
 *
 * In brute force solution, we will start every time we found haystack[0] matches needle[0]. If later found it is a match, then we will restart 
 * search begin from i + 1. But we can still optimize if further, if remaining length in haystack is smaller than needle, then we can safely stop
 * search since we know we don't have enough length remaining in haystack.
 * 
 * Sol1 is brute force solution, but it is very beautiful
 * Sol2 is KMP solution, and I have rewritten it to make if easy-to-understand
 * 
 * @author hpPlayer
 * @date Sep 21, 2015 12:48:46 PM
 */
public class Implement_strStr_p28_sol1 {
    public int strStr(String haystack, String needle) {
        //Outer loop is pointer in haystack
        for(int i = 0; ;i++){//we are confident the loop will end with break or return
        //inner loop is pointer in needle
            for(int j = 0; ; j++){
                //found match
                if(j == needle.length()) return i;
                //reach haystack but still not found match
                if(i + j == haystack.length()) return -1;
                
                //when matching char in haystack and needle, we found an unmatched string, then we will
                //restart search from i+1
                if(haystack.charAt(i+j) != needle.charAt(j))break;
            }
        }
    }
}
