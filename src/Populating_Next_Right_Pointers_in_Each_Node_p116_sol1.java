/*
 * 

Populating Next Right Pointers in Each Node

Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL

*/


/**
 * BFS + observation problem
 * 
 * This problem can be easily solved with BFS with a Queue.
 * The tricky part is to solve it with constant space and be careful about the logic: we should set curr.left.next = curr.right &&
 * if we got curr.next, then curr.right.next = curr.next.left
 * 
 * In BFS queue, we need to use a Queue to keep the order in same level.
 * In this problem we got next pointer in same level, so actually we don't need Queue at all!
 * We can build the next pointer from previous level, like how we put nodes in queue in standard BFS. 
 * In standard BFS, we also put nodes in queue from previous level. 
 * 
 * In this solution we use two pointers. One pointer always points to the start node of current level, so we can easily move to next level by 
 * calling this node.left. One pointer is the pointer in same level. We will use this pointer to scan current level and make nodes in next level
 * connected. Our main loop should stop at the last second level, since we don't have next level after leaf level
 * 
 * @author hpPlayer
 * @date Oct 17, 2015 1:53:46 AM
 */
public class Populating_Next_Right_Pointers_in_Each_Node_p116_sol1 {
    public void connect(TreeLinkNode root) {
        //boundary case, we even don't have "curr level"
        if(root == null) return;
        TreeLinkNode startNode = root;
        TreeLinkNode currNode = null;
        
        while(startNode.left != null){
            //we will loop until the last second level
            
            //set currNode to the head
            currNode = startNode;
            
            //while we still have nodes in current level
            while(currNode != null){
                //since the problem states each parent Node is guaranteed to have to chilrens, we are safely
                //to set left child.next = child.next
                currNode.left.next = currNode.right;
                
                //if we still have nodes in current level, then we can connect curr node's right child 
                //with left child of next node in current level
                if(currNode.next != null) currNode.right.next = currNode.next.left;
                
                //move current Node to next
                currNode = currNode.next;
            }
            
            //we are done with current level, move start pointer to next level
            startNode = startNode.left;
        }
    }
}
