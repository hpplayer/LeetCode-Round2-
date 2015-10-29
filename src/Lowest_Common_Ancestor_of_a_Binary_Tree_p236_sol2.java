import java.util.*;
/**
 * Iterative solution only using one stack and exactly follow the logic of recursive version
 * 
 * If we say the recursive solution is a good solution and medium level, then iterative is a great solution with hard level!
 * To operates like recursion, here we need an inner class MyNode and a stack
 * 
 * In recursive solution, we are using post-order traversal. Our recursion will end when we reach bottom (null) or we found one 
 * of our target node (p/q). During the backtrack, we return the information based on result from left and right subtree.
 * In iterative solution, we also need do the similar way. If current node is an unvisited node, then we will keep it in stack,
 *  and do search on its left and right subtree. Our traversal of the tree should stop when we reach null or found p or q.
 *  Like recursion, we should let current node's parent node know we have reached bottom or found target node. When we are done 
 *  with subtrees, and reach the parent again, we have gathered information from its subtrees. We should have two results, left and right.
 *  Same to recursion, if left and right are all non-null nodes, then current node is LCA, we need return to its parent node and return
 *  this LCA. If we just found one node (this node may be the ancestor of another node), we will still tell its parent, and let parent decide
 *  LCA based on the status from the other subtree. To treat the root node as other nodes, we create a dummy node, so if root node is 
 *  LCA, then we could found it by looking at dummy node
 *  
 * 
 * @author hpPlayer
 * @date Sep 21, 2015 10:32:56 PM
 */

public class Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol2 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.right = b;
		a.left = c;
		
		System.out.println(new Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol2().lowestCommonAncestor(a, c, b).val);
	}
    public class MyNode{
        TreeNode node;
        MyNode parent;
        boolean visited;
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        public MyNode(TreeNode node, MyNode parent){
            this.node = node;
            this.parent = parent;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        MyNode dummy = new MyNode(null, null);//used to retrive global result
        MyNode rootNode = new MyNode(root, dummy);
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(rootNode);
        
        while(!stack.isEmpty()){
            MyNode curr = stack.peek();
            TreeNode node = curr.node;
            MyNode parent = curr.parent;
            //if we reach bottom or we found target
            if(node == null || node == p || node == q){
                parent.result.add(node);
                stack.pop();//we are done with this node, pop out
            }else if(!curr.visited){
                //have not visited curr node, push right first then left
            	//we push right first, left second to the stack, so left will pop first then right
            	//and the order will follow the post-order traversal
                curr.visited = true;
                stack.push(new MyNode(node.right, curr));
                stack.push(new MyNode(node.left, curr));
            }else if(curr.visited){
                //if visited, update result
            	//we only allowed to provide one node to parent node.
            	//if left and right both not null, we provide current node
            	//if left not null, return left, if right not null, return right
            	//otherwise we return null
                TreeNode left = curr.result.get(0);
                TreeNode right = curr.result.get(1);
                if(left != null && right != null){
                    parent.result.add(node);//curr treeNode is LCA
                }else if(left != null){
                    parent.result.add(left);
                }else if(right != null){
                    parent.result.add(right);
                }else{
                    parent.result.add(null);
                }
                
                stack.pop();//we are done with this node, pop out
            }
        }
        
        //since dummy node has only one subtree, which is our whole tree
        //there will be only one node returned to dummy node, i.e. LCA from whole tree
        //so we get it and return it as result
        return dummy.result.get(0);
        
    }
}
