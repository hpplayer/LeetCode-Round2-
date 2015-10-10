import java.util.*;

/*
Closest Binary Search Tree Value II

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.


Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ¡Ü total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.
*/

/**
 * Stack problem
 * 
 * The tricky part is how to solve the problem using property of BST. The solution is to use the stack.
 * Based on the BST, we can know where to go to find nodes after target and where to go to find the nodes before target.
 * So, we just need two stacks to save all nodes before (equal) target and all nodes after target.
 * Then we can easily get k nodes around target, which is similar to "merge" problem.
 * To get nodes before target, we need to use in-order traversal until we get target
 * To get nodes after target, we need to use a reversed version of in-order traversal, where we visit right child first then curr node
 * then left child, until we reach target.
 * So in our stack1, from bottom to top, we will get all nodes following ascending order to target
 * In our stack2, from bottom to top, we will get all nodes following descending order to target
 * Obviously, in both stack,s the node near top will be closer to target then the one on bottom.  So can use "merge" process to pop
 * k nodes that are near target value
 * 
 * This problem can also be solved by using a PriorityQueue, which sort the treeNode based on difference. Although we can limit the 
 * queue size to be <= k, we still haven't use the property of BST. So I don't think this is an excellent solution
 * 
 * @author hpPlayer
 * @date Oct 9, 2015 4:51:28 PM
 */
public class Closest_Binary_Search_Tree_Value_II_p272_sol1 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        //we record nodes before and after target in order, then use merge-sort like style to pick nodes
        
        List<Integer> result = new ArrayList<Integer>();
        if(root == null || k == 0) return result;//boundary case
            
        Stack<Integer> pre = new Stack<Integer>();//stack stores val before or equal target
        Stack<Integer> post = new Stack<Integer>();//stack stores val after target
        
        //for pre stack, we will add nodes follow small -> large
        //so it is original in-order traversal
        getNodes(root, pre, target, true);
        //for post stac, we will add nodes follow large -> small
        //this is reversed in-order traversal
        getNodes(root, post, target, false);
    
        //after we got two stacks, we will start pop node near target, until we got k nodes
        //this is very similar to "merge" stage of mergeSort
        
        while(k-- > 0){
            if(pre.isEmpty()){
                //pre is empty, we have to pop from post
                result.add(post.pop());
            }else if(post.isEmpty()){
                //post is empty, we have to pop from prev
                result.add(pre.pop());
            }else{
                //both stack are not empty, we will first pop the node with smaller difference
                if( Math.abs(pre.peek() - target) < Math.abs(post.peek() - target) ){
                    result.add(pre.pop());
                }else{
                    result.add(post.pop());
                }
            }
        }
        
        return result;
    }
    
    public void getNodes(TreeNode root, Stack<Integer> s, double target, boolean forward){
        Stack<TreeNode> stack = new Stack<TreeNode>();//normal stack we used to traversal the tree
        TreeNode curr = root;
        
        //slightly modified in-order traversal
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                //for pre stack, we will visit left child first
                //for post stack, we will visit right child first
                curr = forward? curr.left : curr.right;
            }else{
                TreeNode temp = stack.pop();
                //backtrack stage, we will stop as long as we reach the boundary of two stacks
                //for pre, the boundary is temp node > target
                //for post, the boundary is temp node <= target
                if(( forward && temp.val > target ) || (!forward && temp.val <= target)){
                   return; 
                }
                
                //otherwise, we will add curr node start visit the other child of curr node
                s.add(temp.val);
                curr = forward? temp.right : temp.left;
            }
        }
    
    }
}
