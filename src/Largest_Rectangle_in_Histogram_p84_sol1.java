import java.util.Stack;

/*
Largest Rectangle in Histogram

(figures are in file p084)
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.
             6         
         5   __   
         __ |  |
        |  ||  | 2    3
 2   1  |  ||  |     __
 __     |  ||  | __ |  |
|  | __ |  ||  ||  ||  |
|  ||  ||  ||  ||  ||  |
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
             6         
         5   __   
         __ |  |
        |\\||\\| 2    3
 2   1  |\\||\\|     __
 __     |\\||\\| __ |  |
|  | __ |\\||\\||  ||  |
|  ||  ||\\||\\||  ||  |

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.

*/

/**
 * Stack problem
 * 
 * The tricky part is come up with the idea and be careful about the boundary
 * We can't calculate rectangle's area until we know the right boundary of it. So we need stack to hold the left boundary, while we found its boundary
 * we will start calculate its area.
 * 
 * How can we use stack to tell the range of a rectangle? Well we can do that by keep bar in stack as non-descending.
 * We noted that the rectangle with a bar can be extended as long as next bar has higher height than it. To calculate the area 
 * of a rectangle, in addition to height, we also need the width which can be got from indexing. So we push indexes to stack and keep
 * their corresponding height as non-descending. If next incoming bar has a lower height, then we know we couldn't extend all rectangles 
 * with height taller than this new incoming bar. Therefore, we will pop all bars on the top of stack that has height taller than incoming bar
 * We will repeat do this until we found a bar in stack that has a height lower or equal the new incoming bar. For each rectangle, we will
 * look at the bar before it (stack.peek()) and the bar after it (new incoming bar at index i) to calculate the width. Notice: the bar in
 * stack is not necessary the real left edge of a rectangle, as the real left edge may hidden in some taller bar that already popped out
 * before we look at current rectangle. But we guarantee the bar at stack.peek() is bar before the real left edge, and the bar at i is the
 * bar after real right edge. So we can use them combining with height to calculate the area. In case we don't have stack.peek(), i.e.
 * the rectangle starts from index 0 and end at i (exclusive), the width is just i
 * 
 * Remark:
 * The same approach can be used to solve problem Maximal Rectangle (p85)
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 5:40:41 PM
 */

public class Largest_Rectangle_in_Histogram_p84_sol1 {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int len = height.length;
        int result = 0;
        
        //we will add a dummy tail with index len, so we can cover all indexes before
        for(int i = 0; i <= len; i++){
            //newBar is the height of new incoming bar. For the dummy tail, we set its height to be -1
            //so we will calculate rectangles with all heights, if we want speed up the program and 
            //exclude the rectangle with height 0, we can just set the dummy tail's height to be 0
            int newBar = i == len? -1 : height[i];
            //the rectangle can be extended as long as our new incoming bar has a higher height
            if(stack.isEmpty() || newBar >= height[stack.peek()]){
                stack.push(i);
            }else{
                //now the newBar has a lower bar, so at least the rectangle whose left edge is on the top
                //of stack couldn't be extended. We will pop it out and calculate area based on difference of
                //indexes. We will do these operations to all left edges in stack whose height is higher than
                //newBar, since rectangles with those left height couldn't be extended any more and stop here
                
                //we may have to pop all left edges in stack, and we calculate the width of rectangle 
                //by looking at the unpoppped edges in stack, so we need do a boundary check
                while(!stack.isEmpty() && newBar < height[stack.peek()]){
                    int index = stack.pop();//pop a bar out, its rectangle has height of height[index]
                    //if stack is Empty, then the rectangle has i width, otherwise it has width of 
                    //(i-1 - stack.peek()), i - 1 is the right edge of rectangle, stack.peek() is the 
                    //bar before left edge of this rectangle, their difference is just the width with 1 based
                    
                    //Note: stack.peek()-1 may be an index far away from index, since we may have poped 
                    //some higher rectangle in between. But we guarantee height with stack.peek() is the first
                    //bar that less than left edge. If it is taller, then we will already popped it out
                    //if we have another lower bar before it, then we should already stop at that bar
                    //so, stack.peek() is the bar before rectangle and i is the bar after rectangle, and the 
                    //rectangle between them just has width of i - stack.peek() - 1
                    result = Math.max(result, height[index] * (stack.isEmpty()? i : i - stack.peek() -1));
                }
                
                //push new incoming bar to the stack, and now we can keep stack as non-descending
                stack.push(i);
            }
        }
                    
            return result;
    }
}
