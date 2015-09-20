import java.util.*;

/**
 * Bit manipulation solution.
 * We can firstly use math equation to get the total number of subsets (2^n)
 * This math equation is calculated as we have two choices to decide whether to insert element at current index into result.
 * So n number of element will give 2^n selection result.
 * 
 * We found the binary representation of subset number can represent those selection as well:
 * {1,2,3}
 * 0: 0 0 0, {} 
 * 1: 0 0 1, {1}
 * 2: 0 1 0, {2}
 * 3: 0 1 1, {1, 2}
 * 4: 1 0 0, {3}
 * 5: 1 0 1, {1, 3}
 * 6: 1 1 0, {2, 3}
 * 7: 1 1 1, {1,2,3}
 * 
 * Thus we only need to check ith digit of each subset's binary representation to find whether to insert ith digit into this subset
 * So we will have a nested loop, outter loop scan all numbers in input array and inner loop scan all subsets and check if we have
 * 1 in ith digit in subset's number, then insert the number if needed
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 1:46:08 AM
 */
public class Subsets_p78_sol3 {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        
        //get the number of subsets by mathmatical way: 2^len
        int size = (int) Math.pow(2, nums.length);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0; i < size; i++){
            result.add(new ArrayList<Integer>());
        }
        
        //scan each number
        for(int i = 0; i < nums.length; i++){
            //scan each list of result and decide where to insert new number
            for(int j = 0; j < size; j++){
                //very important observation, only when the binary representation of subset's Number has 1 in the
            	//correspending index, then we will insert the number in that place
            	
            	//we firstly right shift subset number(j) by i bits to make ith digit become the last digit
            	//then we & with 1, to get the value, if the value is 1 it means we need to insert this number, otherwise
            	//we just skip it
                if (((j>>i)&1) == 1){
                    result.get(j).add(nums[i]);
                }
            }
        }
        
        return result;
    }
}
