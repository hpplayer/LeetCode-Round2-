/**
 * 
 * Two pointer approach.
 * 
 * The tricky part is don't be confused with the max and min. For each index, we will try to pick its max left and right bound, and use the 
 * min bound to decide how much water can be trapped.
 * 
 * We need to notice that the how much water we can trap is determined by the lower max bound bar. So as long as we found one of its max
 * bound bar and its current another bar (no need to be max) is taller than this max bound bar, then we can calculate the water trapped
 * on this index. 
 * 
 * In sol1, we use two tables, each table record the max bar from left or right, then for each index, we check its left and right
 * and decide which one is lower. Thus sol1 gives accurate lower max bound information for each index.
 * 
 * In sol2 we don't have such information, we use two variables to record the max bound from left or right. We just guarantee one of 
 * the max bound is the accurate lower max bound, while the other bound is taller than this max bound. Based on this information, we can
 * still start calculate the water trapped at this index.
 * 
 * So we have to move our pointer smartly, to manually find its max left and right bar. To achieve that, we will use two pointers.
 * for left pointer, the maxLeft is the accurate max bound and for right pointer, the maxRight is the accurate max bound.
 * For index pondered by left pointer, if we found the maxRight > maxLeft, then we have met the above requirement. we guarantee, leftMax
 * is the accurate lower max bound, while the right bound it taller than leftMax, even if rightBound may not be the accurate one, but 
 * we can still start calculation. Of course, if the height at leftPointer > maxLeft, then we know we couldn't trap water here, we just 
 * move left pointer, otherwise we can start calculation. 
 * 
 * To be convenient, each loop, we just compare the maxLeft and maxRight, if maxLeft < maxRight, we will look at left pointer, otherwsie
 * we will look at right pointer
 * 
 * Time: scan array once, so O(n)
 * Space: constant space, so O(1)
 * @author hpPlayer
 * @date Oct 2, 2015 4:00:21 PM
 */
public class Trapping_Rain_Water_p42_sol2 {
    public int trap(int[] height) {
        int len = height.length;
        //we can trap water only when we have at least 3 bars
        if( len < 3) return 0;
        
        //max bar from left and max bar from right, initially, they point to the first and last index
        int maxLeft = height[0], maxRight = height[len -1];
        
        //two pointer that we used to look for next max left or right bar
        int left = 1, right = len -2;
        int result = 0;
        
        //we need to check each bar between first and last index
        while(left <= right){
            if(maxLeft < maxRight){
                //water height is determined by left bar
                if(height[left] < maxLeft){
                    result += maxLeft - height[left];            
                }else{
                    maxLeft = height[left];
                }
                left ++;
            }else{
                //water height is determined by right bar
                if(height[right] < maxRight){
                    result += maxRight - height[right];            
                }else{
                    maxRight = height[right];
                }
                right --;                
            }
        }
        
        return result;
    }
}
