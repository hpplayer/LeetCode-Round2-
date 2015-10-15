import java.util.*;

/**
 * Tree / post-order traversal
 * 
 * This approach is very similar to problem Balanced Binary Tree (p110)
 * 
 * The main algorithm is similar to sol1, but here we translate it to iterative solution.
 * We need do a post-order traversal. For each node, we also need the info on its parent's val and info from its two subtrees.
 * So the hashMap will be a perfect structure in this solution. I use an inner class to record the parent's val and boolean info
 * from two subtrees. Since now we need parent's val for each node, we have to put the TreeNode into the map before we look at it.
 * Therefore, I always put TreeNode into the hashMap at the same time we put it to stack
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 5:02:17 PM
 */
public class Count_Univalue_Subtrees_p250_sol2 {
    private class MyNode{
        int val;//assign before postorder traversal
        boolean isUni;//update after postorder traversal
        private MyNode(int val){
            this.val = val;
        }
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        //boundary case
        if(root == null) return 0;
        
        //we need an inner class to record the parent's val and check status from its subtrees
        HashMap<TreeNode, MyNode> hs = new HashMap<TreeNode, MyNode>();
        //we don't have parent of root of main tree, we just care about its two subtrees
        //so we set an arbritary value here
        hs.put(root, new MyNode(-1));
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        int count = 0;
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            //if left and right subtrees are both visited, then we can look at current Node
            if(  (curr.left == null || hs.containsKey(curr.left)) && ((curr.right == null) || hs.containsKey(curr.right))){
                //check if all nodes in two subtrees have same value with curr node
                boolean left = curr.left == null? true : hs.get(curr.left).isUni;
                boolean right = curr.right == null? true : hs.get(curr.right).isUni;
                
                if(!left || !right){
                    //if any of its subtree has node that got a different value, then set curr node's isUni = false
                    hs.get(curr).isUni = false;//not necessary, as default value is false, but I put it here to make logic more clear
                    continue;
                }
                
                //tree rooted at curr is a UniValue tree, we add it to count
                count ++;
                hs.get(curr).isUni = curr.val == hs.get(curr).val;//update isUni in curr Node to see if we can further expand the tree
            }else{
                //we have not done the postorder traversal on the tree rooted at curr
                stack.push(curr);
                if(curr.left != null && !hs.containsKey(curr.left)){
                    //we need explore the left subtree
                    hs.put(curr.left, new MyNode(curr.val));
                    stack.push(curr.left);
                }else{
                    //we need explore the right subtree
                    hs.put(curr.right, new MyNode(curr.val));
                    stack.push(curr.right);
                }
            }
        }
        
        return count;
    }
}
