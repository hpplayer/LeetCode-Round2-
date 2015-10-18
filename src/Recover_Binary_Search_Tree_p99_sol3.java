/**
 * 
 * The tricky part is to come up with the idea of using in-order traversal and be familiar with Morris-traversal
 * 
 * This explanation will focus on Morris-traversal. For the discuss of detecting swapped node, please ref to sol1.
 * 
 * The key idea of Morris-traversal is to create a backedge for the rightmost node in left subtree. 
 * When looking at each node, we will firstly check if it has left subtree, if it has, then we will need backedge to go back to current
 * node after we are done with left subtree. If there is no left subtree, then we will visit the right tree as general case.
 * To add the backedge, we will search the rightmost node in left subtree. If this is the first time we visit this node, we will add
 * root(curr) Node as this nodes' right child, which is same to we add a backedge on it. After we are done with left subtree,
 * we will stop at this rightmost node again, and go back to our root(curr) Node. After that we will visit the left subtree again, and
 * stop at this rightmost node for the third time, now we found this node's right child is our root(curr) node, so we know we have done
 * with left subtree, we just reset its right child to be null, and begin visit right subtree. Therefore each node will be visited at most
 * 3 times
 * 
 *  
 * 
 * @author hpPlayer
 * @date Oct 18, 2015 3:20:59 PM
 */
public class Recover_Binary_Search_Tree_p99_sol3 {
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode pred = null;
        
        //visit the tree node by node, unlike general in-order traversal, now we use back edge to go back
        while(curr != null){
            if(prev != null && prev.val > curr.val){
                if(first == null) first = prev;
                if(first != null) second = curr;
            }
            
            if(curr.left == null){
                //no left tree, visit right subtree directly
                prev = curr;
                curr = curr.right;
            }else{
                //we have left tree and need back edge to go back
                pred = curr.left;//set first potential rightmost node in left tree
                while(pred.right != null && pred.right != curr){
                    //loop until we reach rightmost node.
                    //this node may either have been added back edge or may be the first vist
                    pred = pred.right;
                }
                
                if(pred.right == null){
                    //if this is the first time to visit this node, then we will start visit the left tree
                    //don't update prev node since we have not done left tree yet
                    pred.right = curr;//add back edge
                    curr = curr.left;
                }else{
                    //if this node has backedge already, then we are done with left tree, just remove backedge 
                    //update prev and start visit right subtree
                    pred.right = null;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
