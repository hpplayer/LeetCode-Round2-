import java.util.*;

/**
 * Tree traversal problem.
 * 
 * The difficulty is how to link current Node with previous Node, so we know where we are
 * 
 * Differed from preorder and inorder, in postorder we have to track parent node, since after we done the left subtree we will back to 
 * current node then go the right tree before we insert current node into the list. So we need a way to find the parent node during traversal
 * 
 * The trick used in sol1 is to use a prev pointer, and the top Node on stack is the current Node, we will decide the next step based on 
 * this two nodes. If prev pointer is still parent of top node, then we have not done the tree, so we will try to search deeper. However,
 * if prev pointer is same with curr pointer that means we have reach bottom, we will pop a node, and try to visit its right tree. If we 
 * found prev is the right child of current node, then we are done both tree of this node, we just pop this node
 * 
 * Sol1 use one stack and an extra pointer. (Space complexity: O(h))
 * Sol2 uses two stack but is simpler. (Space complexity: O(n))
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 12:27:09 AM
 */

public class Binary_Tree_Postorder_Traversal_p145_sol1 {
	   public List<Integer> postorderTraversal(TreeNode root) {
	        List<Integer> list = new ArrayList<Integer>();
	        if(root == null) return list;
	        
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        stack.push(root);
	        
	        TreeNode prev = null; //previously visited node
	        while(!stack.isEmpty()){
	            TreeNode curr = stack.peek();//keep current Node stay still until we have done its left and right subtree
	            //if we are still visiting the tree from top to bottom, then we will search curr's children
	            if(prev == null || curr == prev.left || curr == prev.right){
	                if(curr.left != null){
	                    stack.push(curr.left);
	                }else if(curr.right != null){//if current Node only has right child, then we need add it
	                    stack.push(curr.right);
	                }
	            }else if(curr.left == prev){//if we have returned to a previous visited node, we need search right tree
	                if(curr.right != null){
	                    stack.push(curr.right);
	                }
	            }else if(curr == prev || curr.right == prev){
	                //if we have reach bottom or we have done right tree, then we finish current node, 
	                //we can add it to list
	                list.add(stack.pop().val);
	            }
	            
	            prev = curr;//update prev
	        }
	        
	        return list;
	    }
}
