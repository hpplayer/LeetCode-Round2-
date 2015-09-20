import java.util.*;

/**
 * Iterative solution. 
 * We found the result subsets actually can be composed of copying all existing lists in result, and add new number into all copies
 * to compose new lists.
 * Example:
 * nums: {1,2}
 * Initially:[]
 * Then: [] [1] (insert 1)
 * Then: [] [1] [2] [1,2]  (insert 2)
 * 
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 1:19:11 AM
 */

public class Subsets_p78_sol2 {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            for(int j = 0; j < size; j++){
                List<Integer> list = result.get(j);
                List<Integer> newList = new ArrayList<Integer>(list);
                newList.add(nums[i]);
                result.add(newList);
            }
        }
        
        return result;
    }
}
