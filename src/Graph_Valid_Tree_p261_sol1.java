import java.util.*;

/*
Graph Valid Tree

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path.
In other words, any connected graph without simple cycles is a tree.¡±
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0],
and thus will not appear together in edges.
*/

/**
 * Union find solution
 * 
 * The tricky part is to know this interesting union-find algorithm
 * 
 * Firstly, we should be clear that a valid tree should not have cycle, and all nodes should be connects.
 * Since the problem states we won't have duplicate edges, then we must exactly have n-1 edges in the input. 
 * Note: if input does not cover all nodes, but still meet this requirement, then it means we got a cycle which uses the extra edge.
 * So our next cycle detection process will find it. 
 * 
 * For the cycle detection, it is a little tricky. In sol1, we will use Union-Find algorithm to put each node in a group,
 * if we found there exists an extra edge connects two nodes in one group, then we will have cycle, therefore we should report false 
 * 
 * Union-find. Find means find the root of current subset/subtree. In this problem, we will look at the root of subtree.
 * We will scan each edge, and add the pair of nodes to existing subtree or create a new subtree contains only two nodes.
 * Union means merge two subtrees, so we can compose a larger valid subtree. For all pairs of nodes in subtree,
 * there should be exactly one edge connect them. So if we found new incoming edge connect two nodes in existing subtree,
 * then we must got a cycle.
 * 
 * In this problem we use an array to record the information of subtree. Each cell represents a node, and the value in cell 
 * means the root of subtree, which can also be treated as group number.
 * So by looking at the cell value, we can check whether two nodes are in same tree(group).
 * If we found new incoming edge connects two nodes in same tree, then we will report false. 
 * 
 * Remark:
 * 1. Since given edge is undirected, if the given edge is not ordered, then we may have interesting merge result.
 * Ex: [1, 2] [3, 2].
 * After visiting the first edge, we will merge node 1 and node 2 and build a small subtree rooted at 1
 * After visiting the second edge, we will merge node 3 and node 1 and build a small subtree rooted at 1
 * (we find the root of 3, which is 3, we then find the root of 2, which is 1 now, we merge them so we get a tree rooted at 1)
 * So it seems we didn't add the edge [3,2], but connect 1 and 3 directly instead.
 * Ex:[1,2],[2,3].
 * After visiting the first edge, we will merge node 1 and node 2 and build a small subtree rooted at 1
 * After visiting the second edge, we will merge node 3 and node 1 and build a small subtree rooted at 1
 * (we find the root of 2, which is 1, we then find the root of 3, which is 2, we merge them so we get a tree rooted at 1)
 * This is because we only merge the "root" of subtrees, we won't care about if the direction of edge, we only care about "root"
 * 
 * 
 * 
 * Sol1 provides a UNION-FIND solution, which is very clear. Also, since we are visiting the graph based on edges, if our edge number
 * meets requirement and we don't have cycle in edges, then we are guaranteed to get a valid tree
 * 
 * Sol2 provides a DFS solution, which is slower than sol1, maybe due to operations on map and sets(esp, remove()).
 * Besides, we need to visit the  graph based on Nodes, if the first picked node is incorrect, then we may miss cycle detection.
 * To avoid that, we have to create an visited array, then scan the array to find if we miss any node during the visit
 * 
 * So for this problem, sol1 is more recommended
 * 
 * @author hpPlayer
 * @date Oct 11, 2015 7:11:24 PM
 */
public class Graph_Valid_Tree_p261_sol1 {
	public static void main(String[] args){
		int[][] edges = {{2,3},{1,2}, {1,3}};
		System.out.println(new Graph_Valid_Tree_p261_sol1().validTree(4, edges));
	}
    public boolean validTree(int n, int[][] edges) {
    	if(n == 0) return true;
    	
    	/*
        for a valid tree that input edges cover all nodes, then we must exactly have n-1 edges 
        if we don't have such assumption, then we should return false
		if our edges[][] does not cover all cases, but sill got n-1 edges, then there must be loops
		we will found that during cycle check
         */
        if(n -1 != edges.length) return false;
       
    	
        //record root(group) for each node
        int[] roots = new int[n];
        
        //initialize root to be node itself
        for(int i = 0; i < n; i++){
            roots[i] = i;
        }
        
        for(int[] edge : edges){
            int root1 = getRoot(edge[0], roots);
            int root2 = getRoot(edge[1], roots);
            
            //if new edge connects two nodes in same group, then we will get a cycle, return false
            if(root1 == root2) return false;
            
             //pick one root and merge another root with it
            roots[root1] = root2;
        }
        
        /*
        Finally all nodes should share a same root, but we are using lazy-update, which means we only update the node
        if we use it again.  So to see a final roots[], we should manually run it
        for(int i = 0; i < n; i++){
        	getRoot(i, roots);
        }
        
		*/
        return true;
    }
    
    public int getRoot(int node, int[] roots){
        //find root, return
        if(node == roots[node]) return node;
        
        //otherwise we will search and update the root if necessary
        //since we may merged old root to a new tree, we need check if old root has been updated
        roots[node] = getRoot(roots[node], roots);
        
        //find root of current subtree
        return roots[node];
    }
}
