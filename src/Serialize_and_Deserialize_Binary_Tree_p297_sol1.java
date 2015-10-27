import java.util.*;
/*
Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/


/**
 * TreeNode solution
 * 
 * The tricky part is to write a clean code
 * 
 * BFS is perfect for this problem since we need to store the tree with level order. 
 * 
 * for serialize():
 * If current node is null, we add a "#" and ","
 * If current node has val, then we add "val" and ","
 * and poll two child to queue
 * 
 * for deserialize():
 * We still use BFS and build the tree layer by layer 
 * we firstly split the string based on delimiter ","
 * Then we look two chars each time. At the same time, we poll a node from que. 
 * If current char is '#', we do nothing.
 * If current char is val, then we create a new Node and attach it to current node. Then we offer this node to queue
 * then we move to next char, do same thing
 * After done current pair of char, we move to next pair and poll next node in que
 * 
 * Remark:
 * 1) In deserialize(), when we build the treeNode, remember to use Integer.valueOf() to convert string val to int val!!!!!!!!
 * 2) In deserialize(), our loop goes two chars per step, so it can match the node in queue. That's because in serialize(), we exactly build
 * two children for each valid node. So in deserialize(), as long as the node in que is valid, we must have two children following it!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 27, 2015 2:17:32 AM
 */

public class Serialize_and_Deserialize_Binary_Tree_p297_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(4);
		TreeNode c = new TreeNode(7);
		
		a.left= b;
		a.right = c;
		
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(2);
		b.left = d;
		c.left = e;
		
		TreeNode f = new TreeNode(-1);
		TreeNode g = new TreeNode(9);
		
		d.left = f;
		e.left = g;
		
		System.out.println( new Serialize_and_Deserialize_Binary_Tree_p297_sol1().serialize(a) );
	}


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //boundary case
        if(root == null) return "#";
        
        //we build the string layer by layer
        StringBuilder sb = new StringBuilder();
        //since we need build two child for each node, this time we allow null node in que
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                if(curr == null){
                    //"#" stands for null node, "," is the delimiter
                    sb.append("#").append(",");
                }else{
                    sb.append(curr.val).append(",");
                    //for valid node, we will add its two children as well
                    que.offer(curr.left);
                    que.offer(curr.right);
                }
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        //boundary case check
        if(data == null || data.equals("#") || data.length() == 0) return null;
        
        //we firstly split the data based on delimiter ","
        String[] vals = data.split(",");
        
        //we will build the tree layer by layer, so still need a queue
        //this time que only contains valid node
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        que.offer(root);
        
        //since during serialize(), we build two child for each valid node
        //this time we will pop a node from que, and look two strings in vals as its child
        for(int i = 1; i < vals.length; i+=2){
            TreeNode curr = que.poll();
            //if left child is not null
            if(!vals[i].equals("#")){
                TreeNode left = new TreeNode(Integer.valueOf(vals[i]));
                curr.left = left;
                //since left is a valid node, we must have built its two child as well
                //Therefore we will add it into que
                que.offer(left);
            }
            
            //if right child is not null
            if(!vals[i+1].equals("#")){
                TreeNode right = new TreeNode(Integer.valueOf(vals[i+1]));
                curr.right = right;
                que.offer(right);
            }
        }
        
        return root;
    }  
}
    
 // Your Codec object will be instantiated and called as such:
 // Codec codec = new Codec();
 // codec.deserialize(codec.serialize(root));

