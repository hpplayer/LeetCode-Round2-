/*
Closest Binary Search Tree Value

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

/**
 * Binary Search Tree problem
 * 
 * It is easy to write an iterative solution, but it is not intuitive iterative solution
 * Below solution will avoid set the initial value of difference (we just set the initial value to root.val which is our first candidate)
 * It also combines the difference check into one statement, so I think it is beautiful and put it here
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 4:02:07 PM
 */
public class Closest_Binary_Search_Tree_Value_p270_sol1 {
    public int closestValue(TreeNode root, double target) {
        if(root == null) return -1;
        
        int result = root.val;//
        
        while(root != null){
            //our new node has smaller difference, then update result
            if( Math.abs(root.val - target) < Math.abs(result - target)){
                result = root.val;
            }
            
            root = root.val > target? root.left : root.right;
        }
        
        return result;
    }
}
