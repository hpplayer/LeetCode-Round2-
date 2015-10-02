import java.util.HashSet;

/*
Contains Duplicate II

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

/**
 * Sliding win approach
 * 
 * This problem can be solved by using HashMap, and compare index, but it is trivial so I did not list it here
 * 
 * The problem asks us to have two duplicate that has distance within k, so it implies that we can use slide window approach.
 * The window size is fixed, so we even don't need the left pointer. But to make my code more understandable, I still keep
 * the left pointer. Here we use a HashSet to make the container with k size. If new value matches any value in the container 
 * then we return true, otherwise we will insert it into the container. Don't remember remove the leftmost val, if we have exceeded
 * the win size
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 1:06:50 AM
 */

public class Contains_Duplicate_II_p219_sol1 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int left = 0;//mark the left bound of win
        
        for(int i =0; i < nums.length; i++){
            //if curren win size is larger than k, remove leftmost val first
            if(i > k){
                hs.remove(nums[left]);
                left ++;
            } 
            //then we check if new val match any value in current win of k size
            if(!hs.add(nums[i])) return true;
        }
        
        return false;
    }
}
