import java.util.*;

/**
 * Tree traversal problem.
 * 
 * Postorder and Preorder traversal are very similar, but one is visiting current Node first, one is visiting it last.
 * So actually we can get one order by reversing the result of other order. This can be done with 2 stacks
 * 
 * We can use two stacks to reverse the order.
 * In post-order traversal, our order will like: left->right->curr
 * In prev-order traversal, our order will like: curr -> left-> right
 * So if we use a stack to store those nodes by inserting curr first, then reverse child order then we will get exactly same order as postorder.
 * 
 * So in below code, we firsly insert the mid node "root" into stack1, which is used to store nodes with reversed order, then we do normal
 * preorder traversal: left->right, so in next loop, we will firstly insert right then left, then in our stack1, we will have:
 * curr -> right ->left, which can be used to generate postorder result
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 12:39:15 AM
 */
public class Binary_Tree_Postorder_Traversal_p145_sol2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();//used in postorder traversal
        Stack<TreeNode> stack2 = new Stack<TreeNode>();//used in preoreder traversal
    
        List<Integer> lst = new ArrayList<Integer>();
        if(root == null) return lst;
        
        stack2.push(root);
        
        while(!stack2.isEmpty()){
            TreeNode curr = stack2.pop();
            stack1.push(curr);
            if(curr.left != null) stack2.push(curr.left);
            if(curr.right != null) stack2.push(curr.right);
        }
        
        while(!stack1.isEmpty()){
            lst.add(stack1.pop().val);
        }
        
        return lst;
    }
}
