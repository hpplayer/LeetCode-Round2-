import java.util.*;
/*
4Sum
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ¡Ü b ¡Ü c ¡Ü d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
    
 */

/**
 * Variation of 2 sum (hashMap + 2 pointers)
 * 
 * This problem can be attacked in two ways: 1) use three loops to search combination of 4 numbers, the running time is O(n^3) 2) use HashMap and
 * become this problem to a variation of 2 sum, where the new array become n^2, the running time is arguable, but is bounded by O(n^3)
 * 
 * To convert the problem to 2 sum version, we will first generate all combinations of 2 indexes, then record their sum. So we create an inner class
 * called Pair, where each pair contains the sum, and left/right indexes. To save time, we will only begin generate pairs contains i when
 * we reach index i. Assume, now we reach index i, then we stop at index i and scan all values after i, and calculate their sum. Then we try to find
 * target - sum from hashtable, where stores the sum information before index i. If found 4 numbers that can sums to target, we will insert into 
 * our result. But the problem does not allow duplicates, so we use a HashSet to check duplicates. After done index i, before moving to next index,
 * we will generate new pairs contain index i and put into the HashMap
 * 
 * The problem could also be solved by general ways we used to get 3 sum, i.e. now we use 4 pointers to scan all possible values and calculate
 * the sum. But this solution is trivial and similar to problem 3 sum (p15) so I did not list it here.
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 3:02:42 PM
 */
public class _4Sum_p18_sol1 {
	   public class Pair{
	        int left;
	        int right;
	        public Pair(int left, int right){
	            this.left = left;
	            this.right = right;
	        }
	    }
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        //map key is sum of pair, value is indexes of pair
	        HashMap<Integer, List<Pair>> hs = new HashMap<Integer, List<Pair>>();
	        //hashset to avoid duplicate lists
	        HashSet<List<Integer>> visited = new HashSet<List<Integer>>();
	        
	        Arrays.sort(nums);
	        
	        //i is first index in pair
	        for(int i = 0; i < nums.length-1; i++){
	            //j is the second index in pair
	            for(int j = i + 1; j < nums.length;j++){
	                //sum of pair
	                int sum = nums[i] + nums[j];
	                //if we can find offset in hashMap, then we found a list
	                if(hs.containsKey(target - sum)){
	                    List<Pair> pairs = hs.get(target - sum);
	                    for(Pair pair : pairs){
	                    	
	                    	/*From Java Doc
	                    	 * This method also provides a convenient way to create a fixed-size list initialized to contain several elements:
	                    	 * List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
	                    	 */
	                    	
	                    	List<Integer> temp = Arrays.asList(nums[pair.left], nums[pair.right], nums[i], nums[j]);
	                        
	                        //if we havn't record this list before, then we found a new result!
	                        if(!visited.contains(temp)){
	                            visited.add(temp);
	                            result.add(temp);
	                        }
	                    }
	                }
	            }
	            
	            //we need to create a new pair with i, with all indexes before i
	            for(int l = 0; l < i; l++){
	                //smaller index first
	                int sum = nums[l] + nums[i];
	                if(!hs.containsKey(sum)){
	                    hs.put(sum, new ArrayList<Pair>());
	                }
	                hs.get(sum).add(new Pair(l, i));
	            }
	        }
	        
	        
	        return result;
	        
	    }
}
