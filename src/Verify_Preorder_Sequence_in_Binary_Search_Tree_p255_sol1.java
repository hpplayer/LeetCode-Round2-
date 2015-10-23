import java.util.Stack;

/*
Verify Preorder Sequence in Binary Search Tree

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/		

/**
 * Tree and array problem
 * 
 * The tricky part is to relate BST with input array.
 * 
 * We use a stack to contain visited nodes. The node value in stack is descending, which implies we are still visiting the left 
 * subtree. If the current input < then the top node in stack, it means we are still visiting a left subtree, so we just push
 * it to stack. However, if we curr input > top node, then it means we begin visit a right subtree. We don't know which right
 * subtree it belongs, so we just pop nodes until we found the last node < curr input. This last node must be the root of 
 * this right subtree. Based on BST, all nodes that are smaller than a given node must be in it left tree. So after found 
 * this root, which means we have done its left tree and begin its right tree, we shall never have a later node that has a smaller
 * value than this root. Therefore we use a lower bound to record this and check later nodes. 
 * 
 * Remark:
 * 1) There may be confusion, what if we have invalid trees that still match this rule?
 * Like this:
 *    5
 *   / \
 *  3   4
 * This tree [5,3,4] is an invalid BST while still meets our rule. 
 * But based on our rule, this tree can also be treated as:
 *   5
 *  /
 * 3
 *  \ 
 *   4
 * Where 3 is the root of 4, and 3,4 are still in left tree of 5.
 * 
 * Therefore it is still a valid BST
 * 
 * Sol1 provides a stack solution
 * Sol2 provides a similar solution but using the array itself instead of stack
 * 
 * Another tree and array problem can be found in problem Construct Binary Tree from Preorder and Inorder Traversal (p105)
 * problem Construct Binary Tree from Inorder and Postorder Traversal (p106) (I did not include in this solution set)
 * and problem Convert Sorted Array to Binary Search Tree (p108)
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 11:34:09 PM
 */
public class Verify_Preorder_Sequence_in_Binary_Search_Tree_p255_sol1 {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<Integer>();
        
        //the lower bound for all later nodes
        //it will be updated when we are done with some left subtrees
        int lowerBound = Integer.MIN_VALUE;
        
        for(int curr : preorder){
            //if new node is lower our lowerBound, return false
            if(curr < lowerBound) return false;
            
            //if current node > some nodes in stack, then it means we begin visit a node in a right subtree
            //we will pop all nodes until we reach the root node of this subtree, which is the last node < curr
            //Since in BST, all nodes that are smaller than a target node should all be in the left tree
            //Our later input should never have a smaller number than this
            
            while(!stack.isEmpty() && stack.peek() < curr){
                //if stack is empty, it means now we are in the right tree of whole tree
                lowerBound = stack.pop();//update lower bound to the root node of curr node
            }
            
            //for nodes remain in stack, curr node is still in their left tree. And its value is smaller than 
            //those nodes, so we have a valid tree so far. We just push curr to stack to make it as a new "root"
            //for following nodes
            stack.push(curr);
        }
        
        //all nodes are checked, none violates the rule, so we just return true
        return true;
    }
}
