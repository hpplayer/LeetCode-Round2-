import java.util.*;

/**
 * DFS solution
 * 
 * The tricky part is to be careful about direction of edges. Given edge is not undirected, but we want to visit the graph node by node and
 * we don't know which node will be visited first, so we have to create two directed edges for each undirected edge.
 * We also need to be careful about the completeness. In sol1, we visit the graph edge by edge, so as long as there is no cycle composed of
 * edge and number of edges is match, we can safely this is a valid tree. But here, if we picked a wrong node, then we may miss a cycle detection
 * like a cycle that does not have connection with other nodes.
 * 
 * Remark:
 * 1. we can use Queue to replace Stack, so we will convert DFS to BFS. But I think DFS is more nature in detecting cycle, so I put DFS here
 * 2. Unlike topological cycle detection, where we are given directed edges, here the given edge is undirected, so we may have a little
 * difference like we won't have several edges points to same node but come from different paths. We will only have one node has several
 * outgoing paths or a single path contains all related nodes
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 12:57:19 AM
 */
public class Graph_Valid_Tree_p261_sol2 {
	public static void main(String[] args){
		int[][] edges = {{2,3},{1,2}, {1,3}};
		System.out.println(new Graph_Valid_Tree_p261_sol2().validTree(4, edges));
	}
    public boolean validTree(int n, int[][] edges) {
        //prev check 
        //if edge number does not match, return false
    	//although we need scan visited[] to make sure the coverage of nodes
    	//the pre check can still help us save a lot of time
    	
        //note if n == 0, then it means we do not have node at all! return false
        if(n - 1 != edges.length) return false;
        
        //create map that connects each node with its connected node
        HashMap<Integer, Set<Integer>> hs = new HashMap<Integer, Set<Integer>>();
        
        //firstly initialize the map, since we may not include each node in edge list,
        //we may not have a chance to insert those single nodes into the hash, therefore there would be a problem
        for(int i = 0; i < n; i++){
            hs.put(i, new HashSet<Integer>());
        }
        
        //then fill the map based on input
        for(int[] edge : edges){
        	//we don't which node will be visited first, so we have to insert two directed edges
            hs.get(edge[0]).add(edge[1]);
            hs.get(edge[1]).add(edge[0]);
        }
        
        //visited table, help us get 
        boolean[] visited = new boolean[n];
        
        //DFS search, if we have a valid tree, then we can traversal tree with any start node
        //pick any start node, here I pick node 0
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        
        while(!stack.isEmpty()){
            Integer curr = stack.pop();
            //if we are visiting a visited node, then we must got cycle
            if(visited[curr]) return false;
            visited[curr] = true;
            
            for(Integer next : hs.get(curr)){
                //add next nodes 
                stack.push(next);
                //now we know which node in a pair is visited first
                //remove curr node from next's node set, so we won't have back visit
                hs.get(next).remove(curr);
            }
        }
        
        //since now we are visiting the tree based on node, if our firstly picked node is incorrect (single node)
        //then we may miss some cycle. To be safe, we have to scan visit[] to make sure each node is visited
        //while there is no cycle
        for(boolean visit : visited){
            //if we miss some nodes during DFS, then it must indicates those nodes are in cycle, but we picked
            //nodes do not have connection to them
            if(!visit) return false;
        }
        
        //otherwise, no cycle, all nodes are cover, then we are good, return true
        return true;
    }
}
