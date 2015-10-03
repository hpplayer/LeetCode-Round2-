import java.util.*;
/*
Symmetric Tree

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
*/

/**
 * 
 * BFS solution
 * 
 * This problem can also be attacked by recursive DFS solution, where we use an extra function to compare two nodes. It is trivial so I did
 * not list it here
 * 
 * In DFS solution, we always try to compare the left child of left child with right child of right child and right child of left child with
 * left child of right child, so it means we still need to compare nodes at same level, and it implies we can also solve it by BFS. We just need
 * to offer left child of left child and right child of right child first, then offer right child of left child with left child of right child,
 * repeat it iteratively, and we can still compare them
 * 
 * Remark:
 * Thanks to the Queue, we can offer and poll null to and from queue, so we can treat null node as normal case.
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 2:05:36 AM
 */
public class Symmetric_Tree_p101_sol1 {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;//boundary case
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        //que accepts null value, so we can offer it as normal case
        que.offer(root.left);
        que.offer(root.right);
        
        while(!que.isEmpty()){
            //since we add Node pair with order, the first two nodes will always be the corresponding pair
            TreeNode left = que.poll();
            TreeNode right = que.poll();
            
            if(left == null || right == null){
                if(left != right) return false;//if one is null, the other one is not, return false
                continue;
            }
            
            if(left.val != right.val) return false;//if two nodes do not have same value, return false
            //otherwise, we will add next corresponding pair (original vs mirror) to queue
            
            //left of left vs right or right
            que.offer(left.left);
            que.offer(right.right);
            
            //right of left vs left of right
            que.offer(left.right);
            que.offer(right.left);
        }
        
        return true;
    }
}
