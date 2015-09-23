import java.util.*;
/**
 * Tree traversal + DFS
 * 
 * Recursive solution is trivial, the difficulty is to write an iterative solution. 
 * Usually there are at least two ways to convert a recursive solution to iterative solution:1) Using two stacks 2) Using an inner class that contains
 * path information. For me, I like method 2) more, since we just need to use one stack and it will avoid duplicate operations on two stacks.
 * 
 * Here, I create an inner class which contains path information and TreeNode. We will iteratively add child to path when node has them.
 * If we reach the leaf and node does not have child, we will add the path to result list. Each node can get the path information from its
 * parent's path variable
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 7:07:20 PM
 */

public class Binary_Tree_Paths_p257_sol1 {
    public class MyNode{
        TreeNode node;
        String path;
        public MyNode(TreeNode node, String path){
            this.node = node;
            this.path = path;
        }
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null) return result;
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(new MyNode(root, root.val + ""));
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            TreeNode node = curr.node;
            //leaf
            if(node.left == null && node.right == null){
                result.add(curr.path);
                continue;
            }
            
            if(node.left != null){
                stack.push(new MyNode(node.left, curr.path + "->" +node.left.val));
            }
            
            if(node.right != null){
                stack.push(new MyNode(node.right, curr.path + "->" +node.right.val));
            }
        }
        
        return result;
    }
}
