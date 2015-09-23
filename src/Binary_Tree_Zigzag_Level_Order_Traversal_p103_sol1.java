import java.util.*;

/**
 * Deque + BFS problem
 * 
 * Difficulty is to keep clear with the forward and backward order. It is better to draw the que in paper, then mimic the operation
 * 
 * To get the correct insertion order in current level, it is better to use an indicator. We can either use a flag on level counter.
 * In odd level, our dequeue behaves like a stack, read from left to right, but inserting next level children with order of right before left 
 * In even level, our dequeue behaves like a queue, read from right to left, but inserting next level children with order of left before right
 * 
 * This problem can also be solved by using general Queue, with the trick of inserting value in front or back in temp list
 * Again, use a paper and a pend to draw the operation to avoid confusion
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 1:18:54 AM
 */
public class Binary_Tree_Zigzag_Level_Order_Traversal_p103_sol1 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        que.addLast(root);
        int level = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            ArrayList<Integer> lst = new ArrayList<Integer>();
            level ++;
            for(int i = 0; i < size; i++){
                if(level%2 == 0){//forward
                    //poll front, offer end
                    TreeNode curr = que.pollFirst();
                    //offer back, right first
                    if(curr.right != null) que.offerLast(curr.right);
                    if(curr.left != null) que.offerLast(curr.left);
                    lst.add(curr.val);
                }else{
                    //poll back, offer first
                    TreeNode curr = que.pollLast();
                    //offer back, right first
                    if(curr.left != null) que.offerFirst(curr.left); 
                    if(curr.right != null) que.offerFirst(curr.right);
                    lst.add(curr.val);
                } 
                
            }
            result.add(lst);
        }
        return result;
    }
}
