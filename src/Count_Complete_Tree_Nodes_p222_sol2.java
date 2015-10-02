/**
 * 
 * Tree problem
 * 
 * The basic idea of sol2 has been discussed in sol1.
 * Here we visit the leftmost treeNode of two subtrees. Based on their levels, we can discard one subtree. If they are on same level,
 * then left tree must be perfect tree, we can discard left subtree. If they are on same level, more specifically, if right tree is
 * shorter than left tree, then right tree must be perfect t ree, we can discard right tree
 * 
 * This iteration is very simple without extra container!
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 12:18:47 AM
 */
public class Count_Complete_Tree_Nodes_p222_sol2 {
    public int getHeight(TreeNode root){
        TreeNode curr = root;
        int height = 0;
        while(curr != null){
            height++;
            curr = curr.left;
        }
        // we count height even curr = null, so it can be treated as 1 based, and it is perfect for 
        //calculating the num of tree nodes
        return height;
    }
    public int countNodes(TreeNode root) {
        //approach 2, we always check the leftmost node of subtree, and each time we can discard one subtree
        
        int result = 0;
        
        while(root != null){//we will iteratively visit the tree until we reach the null
            int left = getHeight(root.left);
            int right = getHeight(root.right);
            
            if(left == right){
                //if left == right, left tree must be perfect tree, we visit right subtree
                result += (1<<left);//2^h - 1 + 1 = 2^h, + 1 for discard current root node
                root = root.right;
            }else if(left > right){
                //if left > right, right tree must be perfect tree, we visit left subtree
                result += (1<<right);//2^h - 1 + 1 = 2^h, + 1 for discard current root node
                root = root.left;                
            }
        }
        
        return result;
    }
}
