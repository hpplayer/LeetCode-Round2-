/*
Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/**
 * Tree + array problem
 * 
 * The tricky part is to find the observation and use two arrays smartly
 * 
 * This is an interesting array and tree problem. We do need at least two arrays to build the tree. The purpose of inorder[] is just
 * telling us how large is the left tree and right tree. We can't grab other information from this array. But after getting such info,
 * we can locate the tree range in preorder[], therefore we can create tree from preorder[]. Tree first cell in preorder[] must be the
 * root of whole tree. Then we search same node in inorder[]. After get the index in inorder[], we know the left size of the array would
 * be the size of left tree, while the right size of array would be the size of right tree. So then we use the size to relocate the head
 * of two trees in preorder[]. Actually, we only use the left size to locate the root of right tree, cuz root of left tree is simply the next
 * cell of current root, if we have left tree. After locate the range of two subtrees in preorder[], we can recursively doing same operations
 * on two subtree trees until we are looking at boundary case (left node). So to solve this problem, we need at least 4 pointers. Two
 * of them will give the tree range in preorder[], and two of them will give the tree range in inorder[]. 
 * 
 * Remark:
 * 1) we can use HashMap to replace repeatedly searching in inorder[]
 * 2) Another tree and array problem can be found in problem Verify Preorder Sequence in Binary Search Tree (p255), problem Construct Binary
 * Tree from Inorder and Postorder Traversal (p106) and problem Convert Sorted Array to Binary Search Tree (p108)
 * 3) for p106, the solution is very similar, but in that case, we need look backward, and start build right subtree first. The 
 * recursive and iteraitve solution can all be slighly modified to fit the postorder case, so I did not list the solution to p106
 * 
 * @author hpPlayer
 * @date Oct 22, 2015 8:50:02 PM
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_p105_sol1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode helper(int[] preorder, int p, int q, int[] inorder, int a, int b){
        //boundary case(ex: input array is empty or current tree does not exist at all), return null
        if(p > q || a > b) return null;
        
        //we will pick node from preorder[]. This is because the leftmost node in current range of preorder[]
        //must be the root node of current tree. While in inorder[], the node maybe the a node in left tree
        //or root in current tree, and we can't decide
        TreeNode curr = new TreeNode(preorder[p]);
        
        //leaf node
        if(p == q) return curr;
        
        //current node may has left tree or not, we don't know. But we can find it from inorder[]
        int i = a;
        
        for(; i <= b; i++){
            if(inorder[i] == preorder[p]) break;
        }
        
        //it is to relocate the range in inorder[] since we were doing search on inorder[]
        //for preorder[], we have to calculate the left subtree size, then relocate the range accordingly
        
        //left subtree size
        int leftSize = i - a; 
        
        //recursively build left tree
        //Note: we may not have left tree, but it will be caught in the beginning of next recursion
        curr.left = helper(preorder, p + 1, q + leftSize, inorder, a, i - 1);
        //recursively build right tree
        curr.right = helper(preorder, p + leftSize + 1, q, inorder, i + 1, b);
        
        return curr;
    }
}
