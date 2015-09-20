import java.util.Stack;
/**
 * Stack + tree traversal.
 * 
 * The problem requires us to use at most O(h) memory, so we cannot push all nodes into the stack.
 * For each root, the min value always come from the left subtree. Each left subtree can also be divided into the left and right subtree,
 * but the node in this right subtree will still has a smaller value then root node, as it is still in the left subtree of root.
 * So the idea is pushing all left nodes in one path into the stack. Then we pop them one by one, and begin push the left nodes in one path
 * from each node's right subtree. As described above, even now we are now searching a local right subtree, but node's value should still
 * be smaller than next node in stack.
 * 
 * Since we always try to insert the left nodes in one path, the path may be split into several segmentations, some top nodes are from
 * global root while some nodes are from right subtree of local subtree root, which has been popped out, but we will only have one node
 * for each layer, thus the total number of nodes in stack will not exceed the height h, thus this algorithm will at most needs only O(h) memory
 * 
 * Here is a graph illustrate the path:
 * Nodes in stack:
 *  		n    			n
 * 		   /			   / 
 * 		  a				    
 * 		 /	 			   \
 *   	n		  			n  <- node in path from local right subtree of global left subtree
 *     /		  		   /
 *    n					  n  
 *    
 *    
 * After we pop node a, instead of looking its parent, we will begin push nodes in its right subtree, even they are larger than a,
 * but they will still smaller than a's parent, and since we will only have one node per layer, the total nodes in stack will 
 * be limited to the height of tree, which is O(h)
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 8:23:49 PM
 */

public class Binary_Search_Tree_Iterator_p173_sol1 {

    
    public static void main(String[] args){
    	TreeNode a = new TreeNode(2);
    	TreeNode b = new TreeNode(1);
    	TreeNode c = new TreeNode(3);
    	a.left = b;
    	a.right = c;

    	Binary_Search_Tree_Iterator_p173_sol1 sol = new Binary_Search_Tree_Iterator_p173_sol1(a);
    	while(sol.hasNext()){
    		System.out.println(sol.next());
    	}
    }
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public Binary_Search_Tree_Iterator_p173_sol1(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            stack.push(curr);
            curr = curr.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.pop();//current min node
        int result = curr.val;
        
        curr = curr.right;//although we used curr, but curr.right should still be smaller than curr.parent.val
        while(curr != null){
            stack.push(curr);
            curr = curr.left;//visit the right subtree of left subtree
        }
        
        return result;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */