import java.util.*;

/*
Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Preorder traversal + iterative solution
 * 
 * If we use two pointer approach + recursive approach, each time we will use two pointers to locate the range of current tree, then
 * get mid from this range. Similarly, in iterataive approach, we build an inner class that represents each tree. It includes the left
 * and right boundary and root TreeNode. In recursive solution, we will check if we reach the boundary case that having invalid range,
 * similarly, here each time we will also check if mid and left/right boundary is valid. Then we will get the mid, build node, then
 * push to the stack
 * 
 * 
 * 1) This problem can be easily solved by two pointers + recursion as we used in other array-tree problem,
 * the solution is trivial so I did not list it here
 * 2) other array-tree problem can be found in problem Verify Preorder Sequence in Binary Search Tree (p255),
 * problem Construct Binary Tree from Inorder and Postorder Traversal (p106) (not included in this solution set)
 * and problem Construct Binary Tree from Preorder and Inorder Traversal (p105)
 * 
 * @author hpPlayer
 * @date Oct 22, 2015 11:58:32 PM
 */
public class Convert_Sorted_Array_to_Binary_Search_Tree_p108_sol1 {
    private class MyNode{
        int left;
        int right;
        int mid;//store mid index to avoid duplicate mid calculation
        TreeNode node;
        MyNode(int left, int right, int mid, TreeNode node){
            this.left = left;
            this.right = right;
            this.mid = mid;
            this.node = node;
        }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        //use preorder traversal to visit the tree
        if(nums.length == 0) return null;
        Stack<MyNode> stack = new Stack<MyNode>();
        //get mid number and make it as the root of whole tree
        int mid = (nums.length - 1)/2;
        MyNode root = new MyNode(0, nums.length-1, mid, new TreeNode(nums[mid]));
        stack.push(root);
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            //check left part of curr node to see if we have cells remaining 
            //this is similar to boundary check in recursion
            if(curr.mid - 1 >= curr.left){
                //get mid in left part
                mid = (curr.mid - 1 + curr.left)/2;
                TreeNode left = new TreeNode(nums[mid]);
                curr.node.left = left;
                stack.push(new MyNode(curr.left, curr.mid - 1, mid, left));
            }
            
            //check right part of curr node to see if we have cells remaining 
            //this is similar to boundary check in recursion            
            if(curr.mid + 1 <= curr.right){
                //get mid in right part
                mid = (curr.mid + 1 + curr.right)/2;
                TreeNode right = new TreeNode(nums[mid]);
                curr.node.right = right;
                stack.push(new MyNode(curr.mid + 1, curr.right, mid, right));
            }
            
        }
        
        return root.node;
    }
}
