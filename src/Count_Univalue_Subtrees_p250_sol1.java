/*
Count Univalue Subtrees

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
*/

/**
 * Tree / post-order traversal
 * 
 * The tricky part is to be clear with the things we need to do. We need firstly check if a subtree has a same value with root's val
 * Secondly, we need to count the nodes that are in the subtree. Also we need to know what is UniValue subtree. It means all nodes in
 * the tree should have same value. In above graph, the subtree rooted at 1 does not have unique value in nodes, so it is a uniValue subtree
 * However, its two subtrees, 1 and 1 are uniValue subtree. For the tree rooted at 5, we have left child = 1, so it is not uniValue tree,
 * but its right subtree 5->5 is a uniValue subtree. So now, we got 3 uniValue subtrees with totally 4 nodes
 * 
 * To achieve two goals above, we need a global variable count and a boolean function. If we the boolean function returns true on both
 * left and right subtree, then we know the tree rooted at current node a Univalue tree, nodes in both subtrees have same value with current
 * value, so we just need to add current node into the count. Then we further check if current node's val is same with parent's val, this
 * will help determine if we can expand to a larger tree.
 * 
 * Remark:
 * In below code, we use a beautiful trick to make the code clean. We use "|" in checking the boolean result of two subtrees.
 * Since we requires both subtrees return true, if any subtree return false, then ! operator will make it true, then we use | to 
 * catch that. Why don't we use ||? that's because if we use ||, then there will be short-cut, java will skip the execution of the remaining
 * recursion if the first recursion already return true. We want java executes both, so we use | here
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 4:33:40 PM
 */
public class Count_Univalue_Subtrees_p250_sol1 {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        //for the root of main tree, it does not have parent node, we just assign an arbitrary value
        isUniTree(root, -1);
        
        return count;
    }
    
    public boolean isUniTree(TreeNode root, int val){
        //val is parent node's val
        if(root == null) return true;
        
        //if any of its subtrees do not have same value with root, then we return false directly
        //we use | here to force Java execute two recursion
        if( !isUniTree(root.left, root.val)  |  !isUniTree(root.right, root.val)){
            return false;
        }
        
        //otherwise, two subtrees have same value with current Node, we just add current Node to the count
        count ++;
        
        //return comparsion of curr node's val with its parent's val to see if we can further expand the tree
        return root.val == val;
    }
}
