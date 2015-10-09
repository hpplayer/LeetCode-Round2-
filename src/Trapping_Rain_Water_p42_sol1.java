/*
* Trapping Rain Water 

* Given n non-negative integers representing an elevation map where the width of each bar is 1,
* compute how much water it is able to trap after raining.
*
* For example, 
* Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*     ^                                             
*     |                                             
*   3 |                       +--+                  
*     |                       |  |                  
*   2 |          +--+xxxxxxxxx|  +--+xx+--+         
*     |          |  |xxxxxxxxx|  |  |xx|  |         
*   1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+      
*     |   |  |xxx|  |  |xxx|  |  |  |  |  |  |      
*   0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
*       0  1   0  2  1   0  1  3  2  1  2  1        
* 
* The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 
* 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*     
*/

/**
 * 
 * DP solution
 * 
 * for each index, we need the info of its left and right, so it is very similar to problem candy (p135), where we need to scan the array
 * forward and backward before we can calculate the result on each index. Similar solution can also be found in problem Maximal Rectangle
 * (p85) where we still need to scan array (each row) forward and backward before we can get the width of rectangles
 * 
 * Here we use two tables to record the info of left and right specifically. After that, we can simply calculate the water on each index
 * by looking up the left info before it and right info after it. Obviously, the info means the height of bar. We can only trap water on
 * bar i, if we found bar at i -1 and i + 1 are all taller than bar at i
 * 
 * Remark:
 * Be careful about bar at 0 and len -1. They will not trap water but can provide left/right bar for its next/prev bars
 * 
 * Time complexity, we scan the array two times so O(n)
 * Space complexity, we use 2 arrays, so O(n)
 * 
 * sol1 is dp approach, which uses 2 arrays and is easy-understanding
 * sol2 is two pointer approach, which uses O(1) space, and become tricky
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 3:01:09 PM
 */
public class Trapping_Rain_Water_p42_sol1 {
    public int trap(int[] height) {
        int len = height.length;
        if(len == 0) return 0;//boundary case
        
        int[] left = new int[len];//the max left bar at each index, include the bar at this index
        int[] right = new int[len];//the max right bar at each index, include the bar at this index
        
        left[0] = height[0];//no left bar, only bar at index 0
        right[len -1] = height[len-1];//no right bar, only bar at index len -1
        
        for(int i = 1; i < len; i++){
            int j = len -1 - i;
            left[i] = Math.max(left[i-1], height[i]);
            right[j] = Math.max(right[j+1], height[j]);
        }
        
        int result = 0;
        
        //the leftmost bar and rightmost bar couldn't trap water, they can only provide bar, so we skip them
        for(int i = 1; i < len -1; i++){
            //For each bar (i), if its left (i-1) and right (i+1) are all taller than i, then we can trap water on i
            int minBar = Math.min(left[i-1], right[i+1]);
            if( minBar > height[i]){
                result += minBar - height[i];//each bar has width of 1, so the height of difference is the area 
            }
        }
        
        return result;
    }
}
