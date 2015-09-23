import java.util.Arrays;
/**
 * Two pointer or I called "Sliding Window" problem.
 * 
 * We use a variable to locate the left bound, and use right bound to scan the string.
 * If we found a duplicate char, we will move the left bound to the index of its last occurrence + 1.
 * Thus we need a data structure to record the index. We can either use a HashMap for Unicode which is very large, or we can simply 
 * use an array with legnth of 256, since ASCII code only have 256 chars. Our initial left bound will be set to 0, and our index in string
 * will range from 0 to len(s), so we will begin move left bound only when left <= the index in array. == means we will meet a char that same 
 * with char at index 0, so obviously, the initial value in array could not start with 0, otherwise we will treat all of them has appeared at index 0.
 * So it is necessary to use Arrays.fill() to fill the initial value to -1, which will be differed from index 0
 * 
 * We have official solution in the pdf
 * @author hpPlayer
 * @date Sep 22, 2015 1:50:26 PM
 */

public class Longest_Substring_Without_Repeating_Characters_p3_sol1 {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, result = 0;
        
        int[] visited = new int[256];
        //Initialize array with -1s, so we will move left bound only when we have met this char before
        Arrays.fill(visited, -1);
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(left <= visited[c]){//if we need to update left bound
                left = visited[c] + 1;
            }
            
            result = Math.max(result, i - left + 1);//+1 to convert 0 based index to 1 based length
            visited[c] = i;
        }
        
        return result;
    }
}
