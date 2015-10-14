/**
 * BST + array
 * 
 * Similar solution to sol1, but here we do the operation on array instead.
 * To make the array version similar to stack, we will use a pointer "index"
 * we will assign new "root" value to cell at index. if we found value in [index - 1] is smaller than incoming node (like curr > top),
 * then we will move pointer backward, until we found the value in [index-1] > incoming node.
 * So here index - 1 is like the top node in stack, and index is the current node that will be pushed to stack
 * 
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 11:52:52 PM
 */
public class Verify_Preorder_Sequence_in_Binary_Search_Tree_p255_sol2 {
    public boolean verifyPreorder(int[] preorder) {
        //we use index to track the "top" node in array
        int lowerBound = Integer.MIN_VALUE, index = 0;
        
        for(int curr : preorder){
            //if we found new node < lowerBound return false
            if(curr < lowerBound) return false;
            
            //check if curr node is in right subtree
            //if it is, we will search backward to get the root of this right subtree (the root above its subtree, not the one in subtree)
            //while we still have node left and "top" node < curr node, we "pop" it out
            while(index > 0 && curr > preorder[index-1]){
                //update lowerBound to this value (the value in index - 1)
                lowerBound = preorder[--index];
            }
            
            //now curr Node is still in the left tree of remaining nodes
            //we assign curr to [index], and move index forward
            preorder[index++] = curr;
        }
        
        return true;
    }
}
