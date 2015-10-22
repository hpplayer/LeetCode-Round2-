/*
Populating Next Right Pointers in Each Node II

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/    
    

/**
 * BFS + observation
 * 
 * This problem is follow-up of problem Populating Next Right Pointers in Each Node (p116)
 * The tricky part is now it is not easy for us to connect two valid nodes in next level, and also it is not easy for us to find the 
 * startNode of next level as well.
 * 
 * Although there are some changes, we can still use the same algorithm in p116 to solve it. But we need make some changes to make 
 * the algorithm works for this problem. Change 1) we have to detect the startNode of next level when scanning current level, instead
 * of just populating the leftmost node of next level 2) we have to keep an extra variable to hold last valid node in next level, instead
 * of simply connecting two children of a node or connect right child with left child of next node.
 * 
 * The basic idea is still same. We will connect nodes in same level from last level. But we need to set the startNode of next level during
 * the visit of current level. Since two valid nodes in next level may be very far away, we have to use an extra variable to hold the
 * prev valid node in next level, then as long as we found another valid node in next level, we can connect time. Then update the variable
 * and search for next valid node
 * 
 * Remark:
 * we will visit each node exactly once, so the time complexity should be o(n), while the space complexity should be O(1)
 * @author hpPlayer
 * @date Oct 21, 2015 4:49:54 PM
 */
public class Populating_Next_Right_Pointers_in_Each_Node_II_p117_sol1 {
    public void connect(TreeLinkNode root) {
        TreeLinkNode startNode = root;
        TreeLinkNode currNode = null;
        
        //In this problem, the start Node of next level may not necessary from leftmost node
        //so we need scan current level first then can we decide which node is the start node of next level
        while(startNode != null){
            currNode = startNode;
            startNode = null;//reset startNode to search for the startNode of next level
            
            //now it is not easy to connect two nodes in next level, they may be very far away
            //so we use prevNode to track last valid node in next level
            TreeLinkNode prevNode = null;
            while(currNode != null){
                //search for next valid node in next level
                if(currNode.left != null){
                    //left child is valid
                    if(startNode == null) startNode = currNode.left;
                    if(prevNode != null) prevNode.next = currNode.left;
                    prevNode = currNode.left;
                }
                
                if(currNode.right != null){
                    //right child is valid
                    if(startNode == null) startNode = currNode.right;
                    if(prevNode != null) prevNode.next = currNode.right;
                    prevNode = currNode.right;
                }
                
                currNode = currNode.next;
            }
            
        }
    }
}
