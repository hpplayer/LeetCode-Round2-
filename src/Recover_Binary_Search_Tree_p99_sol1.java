import java.util.*;
/*
Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/	

/**
 * In-order traversal solution
 * 
 * The tricky part is to realize that in-order traversal is very perfect to scan the BST and be clear about what would happen after two nodes 
 * are swapped
 * 
 * First of all, lets get clear what would happen after two nodes are swapped
 * During the in-order traversal, we expect each pair of prev and curr Nodes all have prev.val < curr.val.
 * If two nodes are swapped, we will have 2 cases for each swapped node.
 * case 1. this Node has swapped with a larger value node, so prev.val < curr.val relation will be broken when this node is treated as prev,
 * cuz we will have prev.val > curr.val. For the other swapped node, which must appears behind this node in in-order traversal, it gets a smaller
 * value, so prev.val < curr.val relation will be broken when that node is treated as curr 
 * case 2. this Node has swapped with a smaller value node, so prev.val < curr.val relation will be broken when this node is treated as curr.
 * For the other swapped node, which must be in the left subtree of this node, it gets a larger value, so prev.val < curr.val relation will be
 * broken when that node is treated as prev. Also, we notices in such case, the other swapped node will be detected earlier than this node. 
 * 
 * So now the logic becomes very clear. We use in-order traversal to visit the BST, and we use a prev variable to record the prev node in 
 * in-order traversal. If we found prev.val > curr.val, then we must detect at least one swapped node. We will record it. Also based on the 
 * analysis of case 1 and case 2, we found the first swapped node is always found as prev while the second swapped node is always found as curr.
 * We can just use this observation to update swapped node accordingly.
 * 
 * Remark:
 * 1) Time and space complexity are both O(n), since we may have extreme case the tree looks like a linkedlist
 * 2) we only begin update prev, when we are done with left tree. This is based on the rule of in-order traversal
 * 
 * Sol1 is the iterative solution that uses in-order traversal with a stack
 * Sol2 is the recursive solution that uses in-order traversal. Since sol1 has extra costs on stack, sol2 is faster
 * 
 * @author hpPlayer
 * @date Oct 18, 2015 2:28:52 PM
 */
public class Recover_Binary_Search_Tree_p99_sol1 {
    public void recoverTree(TreeNode root) {
        //iterative, O(n) space
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;
        
        //use standard in-order traversal way to visit the tree
        while(!stack.isEmpty() || p != null){
            if(p != null){
                //we have not done left tree yet, don't update prev
                stack.push(p);
                p = p.left;
            }else{
                //we have done the left tree, now looking at current Node
                TreeNode curr = stack.pop();
                if(prev != null && prev.val > curr.val){
                    //first swapped node must be found when it is treated as prev
                    if(first == null) first = prev;
                    //second swapped node must be found when it is treated as curr
                    if(first != null) second = curr;//we can remove the if condition, but we keep it here for clearness
                }
                
                //update prev node to be curr node
                prev = curr;
                p = curr.right;
            }
        }
        
        //recover swapped node
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
