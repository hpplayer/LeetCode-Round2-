import java.util.*;

/**
 * DFS approach!
 * 
 * DFS approach is more intuitive in skipping duplicates.
 * If we are expanding a list, then we allow duplicates in same list.
 * However, if we are adding a new number to current list, then we are not allowed to create two lists with same number
 * Reflected in DFS, in different layers of recursion, we allow duplicates, but in the iteration of same recursion we don't allow duplicates
 * 
 * The basic idea is similar to most backtracking approach. We use a DFS() and a pointer to control the start of iteration in each recursion
 * We don't worry about the duplicate in same list, so when passing DFS(), we won't check the duplicates. But we worry about adding duplicates
 * to same list twice. So when we are expanding temp list from above recursion, after inserting one number, we will skip all remaining cells
 * that have same values
 * 
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 6:36:13 PM
 */
public class Subsets_II_p90_sol2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        //sort the array to keep duplicate together and help us keep numbers in non-descending order
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //include empty list
        result.add(new ArrayList<Integer>());
        
        DFS(result, new ArrayList<Integer>(), 0, nums);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> temp, int index, int[] nums){
        //use index to skip visited nums
        
        for(int i = index; i < nums.length; i++){
            //make a copy of previous list
            List<Integer> newList = new ArrayList<Integer>(temp);
            //expand it with current num
            newList.add(nums[i]);
            //add to result
            result.add(newList);
            //doing recursion to see if we can further expand it 
            DFS(result, newList, i+1, nums);
            
            //skip all duplicates
            while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
        }
    }
}
