import java.util.*;
/*
Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

/**
 * BFS solution
 * 
 * This problem can also be solved by recursive DFS (using technique 2 in below), but it is trivial, so I did not list it here.
 * 
 * There are many ways to avoid generating duplicate list. 1) using HashSet 2) skip duplicates 3) use nextPermutation method to generate result mathmatically
 * 4) avoid insert duplicate in the same position.
 * 
 * For 1), it is time consuming + space consuming
 * For 2), we couldn't do that if we solve the problem iteratively since we still need duplicates appear in one list, but not in same position
 * For 3), it is another method
 * For 4), this our sol1!
 * 
 * 
 * Here we still use the similar technique as used in Permutations (p46). Where we try to insert each num into positions of existing lists. But 
 * in order to avoid generate duplicate list, we only insert num after the last occur of num if num has duplicates. Ex, (1, 1), if next num is still 1,
 * we will only insert new 1 after all duplicates of 1, i.e. index 2. So in this solution, we need a helper function to find the last occurrence of 
 * duplicate. To improve the speed, we will only do that if we find current num has duplicate. So we need sort the array first, then check the prev num
 * before current num, then decide whether to execute the helper function. The remaining parts are very similar to p46.
 * 
 * Remark:
 * problem Permutations(p46) and problem Combinations (p77) use the similar BFS solution, check it out!
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 8:46:47 PM
 */
public class Permutations_II_p47_sol1 {
	public static void main(String[] args){
		int[] nums = {1,1,0,0,1,-1,-1,1};
		System.out.println(new Permutations_II_p47_sol1().permuteUnique(nums));
	}
    public List<List<Integer>> permuteUnique(int[] nums) {
        //declear an linkedlist so we can pollFisrt()
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        result.add(new ArrayList<Integer>());//initial case
        Arrays.sort(nums);//sort the array so we can find the duplicate
        
        //we insert each num one by one
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            //insert num into each existing list we got
            for(int j = 0; j < size; j++){
                //since now we got duplicates and we don't want to insert duplicates into same position
                //which will cause duplicate list, here we use a variable "start" to mark the first place
                //to insert. Bascially it is the index after previous duplicates. Ex, (1, 1), now the start
                //of next 1, would be index 2, i.e. we can only insert new 1 after all duplicates of 1
                int start = 0;
                List<Integer> list = result.pollFirst();
                
                if(i > 0 && nums[i] == nums[i-1]){
                    start = findLast(list, nums[i]);    
                }
                
                for(; start <= list.size(); start++){
                    List<Integer> temp = new ArrayList<Integer>(list);
                    temp.add(start, nums[i]);
                    result.add(temp);
                }
            }
        }
        
        return result;
        
    }
    
    public int findLast(List<Integer> list, int num){
        int i = list.size() - 1;//place of last occurence of num
        for(; i >= 0; i--){
            if(list.get(i) == num) break;
        }
        //return the index after last occurence of duplicate
        return i + 1;
    }
}
