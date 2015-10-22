import java.util.*;

/*
Subsets II

Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/		


/**
 * BFS solution
 * 
 * This solution is similar to problem combinations p77_sol1, and two permutation problems P46 and p47. In all of them, we will add new 
 * number to previous list to create a new list. But in permutation problems, we will directly edit the old list, In combination and subsets
 * we will keep old list and add new lists.
 * 
 * We firstly sort the array to put duplicates together also it is easier for us to keep non-descending order.
 * Then we add each new input to previous lists and make a new list. So far our approach is very close to Subsets I. The difference is that 
 * now we need to avoid duplicates. Say now our inputs are [5,5,5]. After looking at the first 5, we should have [] [5]. When looking at second
 * 5 we are only allowed to insert 5 to [5] not [], so we will get [] [5], [5,5]. When looking at the third 5, we are only allowed to insert
 * 5 to [5,5], not [],[5]. So the trick is if we found duplicate, we will only add duplicates to the list that ends with last duplicate.
 * In other words, current duplicate will not change any previous list that has already been expanded with previous duplicate. Therefore
 * we just need to keep an extra variable to hold the count of lists that has already been expanded with previous duplicate, then we are done.
 * 
 * 
 * 
 * Remark:
 * This problem can be easily be attacked by using a HashSet, but since there are some patterns can be followed. We can use tricks to skip
 * duplicates instead of using HashSet
 * 
 * 
 * Sol1 is iterative solution with BFS style
 * Sol2 is recursive solution which is more intuitive
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 5:58:01 PM
 */
public class Subsets_II_p90_sol1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        //sort the array to keep duplicate together and help us keep numbers in non-descending order
        Arrays.sort(nums);
        
        //include the empty list!
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        
        //variable to hold the count of lists that are already expanded with prev duplicate
        int prev = 0;
        
        for(int i = 0; i < nums.length; i++){
            //total size
            int size = result.size();
            
            //choose the start of lists that we will begin insert current number
            //if we got duplicates, then we will skip all cells that already be expanded with prev duplicate
            int start = (i > 0 && nums[i] == nums[i-1])? start = prev : 0;
            
            for(; start < size; start ++){
                //create a copy of previous list
                List<Integer> temp = new ArrayList<Integer>(result.get(start));
                //insert current num
                temp.add(nums[i]);
                //add to result
                result.add(temp);
            }
            //update prev to num of lists that are expanded with current num
            prev = size;
        }
        
        return result;
    }
}
