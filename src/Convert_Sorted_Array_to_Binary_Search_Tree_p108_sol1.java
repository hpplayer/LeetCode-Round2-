import java.util.*;

/**
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
        int mid;
        TreeNode node;
        
        MyNode(int l, int r, int mid, TreeNode node){
            this.node = node;
            this.mid = mid;
            left = l;
            right = r;
        }
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        int mid = (0 + nums.length-1)/2;
        MyNode root = new MyNode(0, nums.length - 1, mid, new TreeNode(nums[mid]));
        stack.push(root);
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            
            if(curr.mid -1 >= curr.left){
                mid = (curr.left + curr.mid-1)/2;
                TreeNode left = new TreeNode(nums[mid]);
                curr.node.left = left;
                stack.push( new MyNode(curr.left, curr.mid - 1, mid, left) );
            }
            
            if(curr.mid + 1 <= curr.right){
                mid = (curr.mid + 1 + curr.right)/2;
                TreeNode right = new TreeNode(nums[mid]);
                curr.node.right = right;
                stack.push(new MyNode(curr.mid + 1, curr.right, mid, right));
            }
        }
        
        return root.node;
    }
}
