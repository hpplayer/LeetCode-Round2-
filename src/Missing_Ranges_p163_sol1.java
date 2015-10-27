import java.util.*;
/*
Missing Ranges

Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/	
		
/**
 * String problem
 * 
 * The tricky part is don't be confused with the description and be careful with boundary cases
 * 
 * The basic idea is compare two neighbor cells in array and check if they are consecutive. If not, we have found one missing range
 * Since upper and lower are inclusive, we have to cover them as well. But how we can cover them in the array as we don't have cells before 
 * the first cell and after the last cell. The solution is to create the dummy head and head. We add lower - 1 before first cell and add upper + 1
 * after last cell. so we can cover the upper and lower
 * 
 * Remark:
 * 1) Don't forget update prev pointer when we scanning the array!!!!!!!!!!!!
 * we have official solution in attached manuals
 * 
 * @author hpPlayer
 * @date Oct 27, 2015 1:13:02 AM
 */

public class Missing_Ranges_p163_sol1 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        
        List<String> result = new ArrayList<String>();
        
        //prev variable is to hold the value in prev cell
        //since we need to check lower as well, we have to add a dummy cell before first cell in array
        //and the value is lower - 1. Therefore if we find first cell has a value > lower, we can find
        //the missing range from lower to first cell as well
        int prev = lower - 1;
        
        //we let the loop goes until we reach the dummy cell we add after last cell in array
        //and we will set the value of that cell to be upper + 1. Therefore if we find last cell has a value < upper,
        //we can find the missing ramge from last cell to upper as well
        for(int i = 0; i <= nums.length; i++){
            
            //curr variable holds the value in curr cell
            int curr = i == nums.length? upper + 1 : nums[i];
            
            if(curr - prev > 1){
                //if we found curr and prev are not consecutive, then it means we found a missing range
                //just create the string and add to result list
                //Note: the missing range is from prev + 1 to curr -1
                //think about example [1, 4] where prev is 1, curr is 4
                result.add(buildStr(prev + 1, curr - 1));
            }
            
            //update prev pointer
            prev = curr;
        }
        
        return result;
    }
    
    public String buildStr(int start, int end){
        //we may have two cases 1)only missing single num 2) missing range
        //we will build string accordingly
        return start == end? start + "" : start + "->" + end;
    }
}
