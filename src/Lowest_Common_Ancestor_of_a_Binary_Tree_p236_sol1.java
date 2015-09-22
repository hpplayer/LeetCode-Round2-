/**
 * Tree post-order traversal
 * 
 * We can solve this problem in a bottom-up way, because only after we found p and q can we locate their common ancestor.
 * Since we want find the common root of p and q, it is better to use post-order traversal
 * 
 * We will start search each node's left and right subtree. If we found p and q exist in its left and right tree respectively, then 
 * we know current node is the LCA, we just return it. If we found one node in left or right subtree, that may indicate we only have one 
 * node in this subtree, or may be we still have two nodes in this subtree but one node is much deeper than the returned node. Since 
 * we always return the first node == p or q, now we will keep return this first node. If there is one node, we should return it, if there
 * are two nodes, we should still return it as it is THE LCA of two nodes. In either case, we will just return the same node from its subtree
 * 
 * However, if we found neither of current node's subtree return non-null node, then we know current node is not related with either node,
 * we can simply return null indicating there is no node from this subtree.
 * 
 * 
 * @author hpPlayer
 * @date Sep 21, 2015 5:05:23 PM
 */
public class Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //if reach null
        if(root == null) return null;
        
        //we are searching tree from top to bottom, if found target, then no need to further search
        //if we have only node in this subtree, then it is time to return
        //if we have two nodes in this subtree, then root is THE LCA of this subtree, still need to return
        if(root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        //if two nodes are in left and right subtree of current Node, then current Node is LCA
        if(left != null && right != null) return root;
        
        //if left subtree contains at one node, then we return root of left subtree, for further search
        //if we have two nodes in left subtree, we also need to return root of left subtree
        if(left != null) return left;
        
        //same as left
        if(right != null) return right;
        
        //if left and right subtree of current Node does not contain any node, then return null indicating 
        //we can't find node here
        return null;
    }
}
