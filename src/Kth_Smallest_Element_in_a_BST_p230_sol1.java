/*
Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
*/

/**
 * In-order Tree Traversal
 * 
 * Since the given tree is BST, and the problem asks us to return the kth smallest element. The intuitive solution is just traversal 
 * left tree, then current node, then right tree, i.e. follow the ascending order of value to search the node. Thus the in-order traversal
 * is perfect for this problem
 * 
 * But here I want to focus on the follow-up question. If the BST is modified often and we can modify the tree Node, which approach can
 * given us an optimized O(h) time?
 * The solution is modifing each node, and let each node contains the numbers of nodes in left tree. Thus if we found current Node's left
 * tree contains k-1 node, then current node would be kth tree. Otherwise we would search left tree, if k < count, or we would search 
 * right tree for k - (count + 1) th node. By using this trick, we can optimize the runtime to O(h) compared with O(n) from in-order solution
 * 
 * sol2 is the iterative version of in-order traversal
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 1:13:08 AM
 */
public class Kth_Smallest_Element_in_a_BST_p230_sol1 {
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        
        //Since we want kth SMALLEST element, so search left tree first 
        int left = kthSmallest(root.left, k);
        
        //if we have found this node if left tree, then return it
        if(count == k) return left;
        
        //if there are k-1 elements in left tree, and current node is kth node, we just return it
        count ++;
        if(count == k) return root.val;
        
        //otherwise we have to search right tree
        return kthSmallest(root.right, k);
    }
}
