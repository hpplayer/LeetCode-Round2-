import java.util.*;

/*
Unique Binary Search Trees II

Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/  
   
/**
 * Divide-and-conquer(backtracking) + memorization approach
 * 
 * The tricky part is to write the clean code of this approach
 * 
 * In backtracking version, we just need to push down the max and min value, so all following nodes will be generated in range. This is very
 * similar to problem Validate Binary Search Tree (p98). Max and min here have two functions 1) control the value range 2) control how many 
 * nodes of current tree. 
 * 
 * The basic idea is that we are required to generate all styles of trees have node from 1 to n. We use divide-and-conquer
 * approach to recursively shrink the range until we reach the leaf node and just need to generate a single node. Then during the 
 * backtracking, each returned list should have subtrees with qualified root node and tree structure, so we simply attach two subtrees
 * to current node to build a valid tree within current range, we do this to all combinations of subtrees and build a tree list
 * based on current range, then return to above range. To speed up the program, we can use a HashMap to add the memorization feature
 * 
 * 
 * Remark:
 * For this problem, it is better to use  Divide-and-conquer(backtracking) approach with memorization.
 * If we use DP, we need an extra array or HashMap to store all generated trees AND we have to deep copy the tree as well,
 * so it is not a good choice
 * 
 * @author hpPlayer
 * @date Oct 21, 2015 12:00:03 AM
 */

public class Unique_Binary_Search_Trees_II_p95_sol1 {
    public List<TreeNode> generateTrees(int n) {
    	
    	//min node value is 1, max node value is n
        return DFS(1, n, new HashMap<String, List<TreeNode>>());
    }
    
    public List<TreeNode> DFS(int min, int max, HashMap<String, List<TreeNode>> hs){
        String key = min + "#" + max;
        if(hs.containsKey(key)) return hs.get(key);
        
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        //boundary case, like its parent level is leaf level, just return null
        if(min > max){
            result.add(null);
            return result;
        }
        
        //we have to generate nodes in controlled range with all possible styles
        //like we have number from 2 - 5, how many BST can you generate
        //we sete i from min to max, bot inclusive to include the single node case
        for(int i = min; i <= max; i++){
            //generate tree with root i
            
            //get root of left subtree from DFS with controlled range
            List<TreeNode> leftList = DFS(min, i - 1, hs);
            //get root of right subtree from DFS with controlled range
            List<TreeNode> rightList = DFS(i + 1, max, hs);
            
            
            //each left subtree and right subtree is valid, so need generate current tree list with all combinations 
            
            for(TreeNode left : leftList){
                for(TreeNode right : rightList){
                     TreeNode root = new TreeNode(i);
                     root.left = left;
                     root.right = right;
                     result.add(root);
                }
            }
        }
        
        hs.put(key, result);
        return result;
    }
}
