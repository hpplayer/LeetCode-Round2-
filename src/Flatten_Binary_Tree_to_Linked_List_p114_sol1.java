/*
Flatten Binary Tree to Linked List

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
*/


/**
 * In-order traversal + tree problem
 * 
 * The tricky part is to observe that the final linkedList follows the pre-oreder traversal and come up with the idea of how to flat left subtrees
 * 
 * The basic idea is to insert left subtree between current node and its right subtree, so the final order will follow the pre-order traversal
 * To complete the insertion, we have to locate the first and last node in left subtree. First node is the root of left subtree while the last node
 * is the rightmost node in left subtree. So when we found a left tree existed, we will search the rightmost node of it. Then based on first and last
 * node, we insert left tree into the expected spot. Then we need to clean up the old left tree otherwise we will have two left subtrees. We will
 * iteratively do this to all nodes in tree that has left subtree. We will finish this process when we reach the null node
 * 
 * For me, it is kind of similar to Morris traversal, because in both solution, we need to get the rightmost node (last node) in left tree, then
 * do something to this node
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 2:02:18 PM
 */
public class Flatten_Binary_Tree_to_Linked_List_p114_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		a.left = b;
		b.left= c;
		c.left = d;
		d.left = e;
		
		new Flatten_Binary_Tree_to_Linked_List_p114_sol1().flatten(a);
		
		while(a != null){
			System.out.println(a.val);
			a = a.right;
		}
	}
	
    public void flatten(TreeNode root) {
        while(root != null){
            //the final order in linkedList follows in-order traversal, so we just need insert left subtree part
            //into the spot between current node and its right subtree
            
            if(root.left != null){
                //if we have left subtree, then we need do insertion
                
                //to achieve the insertion, we need know the first and last node of left subtree
                //first node is the root of left subtree and the last node is the rightmost node in left subtree
                //so now we need look for the rightmost node
                
                TreeNode rightMost = root.left;
                while(rightMost.right != null){
                    rightMost = rightMost.right;
                }
                
                //after found the rightmost node (last node), we need do the insertion
                rightMost.right = root.right;
                root.right = root.left;
                
                //then clean up old leftsubtree
                root.left= null;
            }
            
            //check next node in linkedList to see if it has the left subtree
            root = root.right;
        }
    }
}
