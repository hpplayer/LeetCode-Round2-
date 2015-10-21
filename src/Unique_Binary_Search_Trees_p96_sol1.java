/*
Unique Binary Search Trees

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * DP solution
 * 
 * The tricky part is to get the idea of DP solution.
 * 
 * Solution here is to count the way to build a large tree with n nodes by dividing it into count the ways to build left subtree and
 * the ways to build right subtree.
 * For example, for a tree with 3 nodes. we can have 3 styles: left subtree 0 node with right tree 2 nodes (root itself count 1 node), 
 * left subtree 1 node with right tree 1 node, and left tree 2 nodes with right tree 0 node. Now we just need to need to look up the count
 * of ways to build tree with 0, 1 and 2 nodes, and then we can get the solution!
 * 
 * So the basic idea is to build a dp array. The dp array has length n + 1 since we need to include 0 node case. Value in cell represents
 * how many ways to build a tree with such number of nodes. To fill up the cell i, we need to divide the tree with i nodes into all possible ways,
 * and sum ways together. Since we can freely change the root of tree or subtree, it is guarantee that we can have a way to divide tree in that way,
 * as long as the total nodes in two subtrees are < n. 
 * 
 * @author hpPlayer
 * @date Oct 20, 2015 10:46:21 PM
 */
public class Unique_Binary_Search_Trees_p96_sol1 {
    public int numTrees(int n) {
        //include empty tree case, so we have n + 1 cells
        int[] dp = new int[n+1];
        
        //boundary case, empty tree and single node tree
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++){
            //for each tree with i nodes, we can build it from having j nodes in left subtree and i - 1 - j in 
            //right subtree (-1 to subtract the root node itself). By changing the root, we can always have ways to do that.
            for(int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i-1-j];
            }
        }
        
        return dp[n];
    }
}
