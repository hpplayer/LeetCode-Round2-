import java.util.*;

/**
 * Tree + array problem
 * 
 * The tricky part is to observe that first nodes following root node are always nodes in leftmost path, if we have left tree.
 * 
 * So we firstly follow nodes in preorder to add all nodes in leftmost path. Each next node will be treated as the left child of last node. 
 * inorder[] can tell us which is the leftmost node in leftmost path. After we reach that node, we will return back. Obviously we need a container
 * to store nodes that we have visited. In this solution, we pick stack. Now inorder[] can tell us which nodes in right paths that have not been
 * visited, after we reach that node, we will attach it to the right of the last node pop from stack, and then push right node as a new root node
 * to stack to build a new leftmost path. 
 * 
 * @author hpPlayer
 * @date Oct 22, 2015 9:43:50 PM
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_p105_sol2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //boundary check
        if(preorder.length == 0 || inorder.length == 0) return null;
        
        //stack helps us get the root of tree
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //i is pointer in preorder, j is pointer in inorder
        int i = 0, j = 0;
        TreeNode root = new TreeNode(preorder[i++]);
        stack.push(root);
        
        //we update the treeNode based on preorder[]
        while(i < preorder.length){
            TreeNode curr = stack.peek();
            
            if(curr.val != inorder[j]){
                //we have not reached the leftmost node in current tree
                //we are still in leftmost path of current tree
                //so we just create a new Node to be the left node of curr Node
                TreeNode left = new TreeNode(preorder[i++]);
                curr.left = left;
                stack.push(left);
            }else{
                //we have reached the leftmost node in current tree. It means next node in preorder[] is a node in right tree
                //now we need use inorder[] to get back to the root of this right tree, whose right tree will be next visited
                while(!stack.isEmpty() && stack.peek().val == inorder[j]){
                    //curr may not necessary the node we peek in the beginning of loop
                    //it will be the root of the first tree whose right tree has not been visited
                    curr = stack.pop();
                    j ++;
                }
                
                //now we can continue build node based on preorder. Just attach this new node to root we found from while loop
                TreeNode right = new TreeNode(preorder[i++]);
                curr.right = right;
                //in preorder[], we have done left tree and curr root Node, so now we will visit this right tree
                stack.push(right);
            }
        }
        
        return root;
    }
}
