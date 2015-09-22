/**
 * Tree problem.
 * 
 * This problem is trivial, but I like the following code.
 * If root.val is larger than max(p,q), then root is too large, we need search left tree for smaller value
 * if root.val is smaller than min(p,q), then root is too small, we need search right tree for larger value
 * @author hpPlayer
 * @date Sep 21, 2015 8:35:13 PM
 */
public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree_p235_sol1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            int max = Math.max(p.val, q.val);
            int min = Math.min(p.val, q.val);
            if(root.val < min){//too small
                root = root.right;
            }else if(root.val > max){//too big
                root = root.left;
            }else{
                break;
            }
        }
        
        return root;
    }
}
