/*
Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

/**
 * Classic two-pointer problem
 * 
 * The tricky part is to deal with case height[left] == height[right]
 * 
 * We start with the first candidate that has the widest width, i.e. left = 0, right = len -1
 * Notice that the width will always be shorter in following cases, only a higher edge can give larger area
 * 
 * if left edge > right edge, then to find the larger area, we need move right to find a higher edge, thus right --
 * if left edge < right edge, then to find the larger area, we need move left to find a higher edge, thus left ++
 * if left edge = right edge, then to find the larger area, we need both two edges have a higher edge, thus left ++ and right --
 * 
 * Remark:
 * The width used to calculate the area is simply right - left, not right - left + 1, i.e. the width is the gap between two edges
 * @author hpPlayer
 * @date Sep 25, 2015 12:37:29 AM
 */
public class Container_With_Most_Water_p11_sol1 {
    public int maxArea(int[] height) {
        int max = 0;
        
        int left = 0, right = height.length - 1;
        
        //while the container at least has width of 1
        while(left < right){
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if(height[left] > height[right]){
                //keep left
                right --;
            }else if(height[left] < height[right]){
                //keep right
                left ++;
            }else{
                //both needs to move to find a higher edge
                left ++;
                right --;
            }
        }
        
        return max;
    }
}
