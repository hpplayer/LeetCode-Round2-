import java.util.*;

/*
Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array
 such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/


/**
 * TreeSet problem
 * 
 * In problem LRU Cache (p146), we can use built-in LinkedHashMap to keep inputs with insertion order and use removeEldestEntry() to
 * keep the limit. Here, we can use built-in TreeSet to keep inputs with sorted order and easily retrieve the min and max value.
 * The tricky part is to come up with the use of TreeSet and be cautious with overflow
 * 
 * TreeSet can be used to keep inputs with sorted order and we can use same window-sliding technique in problem Contains Duplicate II
 * (p219) to keep the distance within k, thus here we just need use TreeSet to track the max and min value in the window. floor() and 
 * ceiling() functions can be used to do this. If we contains duplicate, floor()/ceiling() will return the duplicate, if not, it will
 * return the next/prev value before input. Otherwise return Null. So we just to get the min and max value in the window, and use it
 * to calculate the difference then compare with t, then we are done.
 * 
 * Remark:
 * we should be careful about the overflow, so here I case the input number to long type to calculate the difference
 * 
 * Sol1 is the solution using TreeSet
 * Sol2 is the solution using bucketing
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 2:00:49 AM
 */

public class Contains_Duplicate_III_p220_sol1 {
	public static void main(String[] args){
		TreeSet<Integer> hs = new TreeSet<Integer>();
		hs.add(1);
		hs.add(2);
		hs.add(3);
		System.out.println(hs.floor(2));
		System.out.println(hs.ceiling(2));
	}
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //use treeset to keep input in ascending order
        //we declare Long to avoid overflow
        TreeSet<Integer> hs = new TreeSet<Integer>();
        int left = 0;
        for(int i = 0; i < nums.length; i++){
            //win size exceeds limit, need remove leftmost one
            if(i > k){
                hs.remove(nums[left]);
                left ++;
            }
            
            //create a long for nums[i] to avoid overflow
            long val = (long) nums[i];
            //floor will return the floor number of nums[i], it none return, return NULL
            //if it contains the duplicate of input, then return duplicate
            Integer floor = hs.floor(nums[i]);
            //Ceiling will return the Ceiling number of nums[i], it none return, return NULL
            //if it contains the duplicate of input, then return duplicate            
            Integer ceil = hs.ceiling(nums[i]);
            
            if((floor != null && val - floor <= t) || (ceil != null && ceil - val <= t)) return true;
            
            hs.add(nums[i]);
        }
        
        return false;
    }
}
