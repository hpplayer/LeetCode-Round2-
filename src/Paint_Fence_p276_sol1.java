/*
Paint Fence

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

/**
 * DP problem
 * 
 * This problem for me is very similar to problem House Robber (p198). In both problems, whether rob curr house/choose curr color depends 
 * on the previous decision. In this problem, if we decide to paint curr post same color with last post, then the last second painted post
 * must not be same color with last post. However, if we decide to paint curr post diff color with last post, then the last second painted
 * post color can by any of k.
 * 
 * This problem can be solved by using a DP array, but it is not intuitive (logic not clear), nor space-wisely. The better approach is to split each
 * post into two cases as discussed above. Doing this way we can also reduce the space to O(1), and it is also more easy-understanding
 * 
 * Remark:
 * 1. Since we need at least two posts to discuss same/diff color, we need be careful about the boundary case, where we don't have post at all
 * or we only have one post 
 * 2. This problem is also very similar to problem Paint House II (p265), but 265 is an advanced version that has n choices for each cell
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 3:05:47 PM
 */
public class Paint_Fence_p276_sol1 {
    public int numWays(int n, int k) {
        //since we need at least two prev posts to start analyze
        //both n == 0 and n == 1 are boundary case
        if(n == 0) return 0;
        if(n == 1) return k;
        
        //for two posts case, we got following result
        int sameColor = k;
        int notSameColor = k * (k-1);
        
        //we will review 3 posts to n posts
        for(int i = 3; i <= n; i++){
            //we need sameColor to calculate next notSameColor,
            //but we need calculate next sameColor as well
            //so we need an extra temp to hold old value 
            int temp = sameColor; 
            
            //same color can only come from notSameColor
            //In other words, only when previous 2 posts are not same color
            //could we paint this post same color with previous one
            sameColor = notSameColor;
            
            //notSameColor can either come from previous 2 posts are not same color
            //or come from previous 2 posts are same color
            
            //if previous 2 posts are same color, then we have k - 1 choices left
            //if previous 2 posts are not same color, to make a notSameColor pair, we still have k - 1 choices left
            notSameColor = (temp + notSameColor) * (k-1);
        }
        
        //the total paint way is simply the sum of them
        return sameColor + notSameColor;
    }
}
