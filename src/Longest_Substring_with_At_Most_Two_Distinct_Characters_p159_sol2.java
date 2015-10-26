/**
 * Sliding window + HashMap solution
 * 
 * The tricky part is to come up with the idea
 * 
 * This solution is more intuitive than sol1. We use an array to count the appearances of each char.
 * We use an array of 256 len to cover all ASCII chars. We still use two pointers as the left and right bound of the window
 * We just move the right bound forward to include more chars. If we found curr char pointered by right bound is a new char,
 * then we update the total count of distinct chars, if the count > 2, then we begin move left pointer, and discard chars.
 * We will move left pointer until we found the appearance of a char becomes 0. Then we calculate the size of new window, and update
 * result accordingly
 * 
 * Remark:
 * Time complexity is O(n^2), and space complexity is O(1) (constant time)
 * 
 * This result is more nature and can be applied to k distinct chars case, so I think it is better than sol1 
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 1:17:22 AM
 */
public class Longest_Substring_with_At_Most_Two_Distinct_Characters_p159_sol2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        //hashMap to count the appearance of each char
        int count[] = new int[256];
        //num of Distinct Characters
        int distinct = 0;
        //left bound pointer
        int left = 0;
        
        int result = 0;
        
        for(int right = 0; right < s.length(); right++){
            //we found a new char
            if(count[s.charAt(right)] ++ == 0){
                distinct ++;
            }
            
            while(distinct > 2){
                //if distinct > 2, then we need move our left pointer
                if(--count[s.charAt(left)] == 0){
                    //if we have made a char disappear
                    distinct --;
                }
                
                left ++;
            }
            
            //left and right are index with 0 based, so when convert to len, need + 1    
            result = Math.max(result, right + 1 - left);
        }
        
        return result;
    }
}
