import java.util.*;

/**
 * Topological/cycle-detect problem w/ DFS Solution
 * 
 * We use a classic DFS solution to detect cycle. In DFS, we will mark each visited node in current path. If our next node is a node already marked
 * as visited, then we know there exists a back edge in current path, which means we got a cycle.
 * We may have several paths that shares one node, to avoid the mix of search on those paths, after we done a path, we will reset all nodes as unvisited.
 * But this will let us do repeat work on same sub-paths. So here we use a marker cleanNode to let us skip nodes that have been visited before but 
 * reported no cycles.
 * 
 * Remark:
 * With an extra marker "cleanNode", we can avoid the search of same sub-paths again. It greatly reduce the time!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 10, 2015 11:14:16 PM
 */
public class Course_Schedule_p207_sol2 {
    private class Node{
        //if this node has been visited before and is not in any cycle
        //this parameter will greatly speed up our program by avoid visiting visited nodes
        boolean cleanNode;
        
        //we have marker visited to tell us if we have visited this node before in current path
        //to avoid the mix of different search paths, we will reset it after we done current path
        boolean visited;
        
         //nodes around
        List<Node> nodes;
        
        //recursive DFS to detect cycles
        public boolean hasCycle(){
            //firstly check if this node is a visited clean node
            //this pre-check will avoid repeat search and save time
            if(cleanNode){
                return false;
            }
            
            //if our curr node is a visited node, then it means we got a back edge in current path, so there is a cycle
            if(visited){
                return true;
            }
            
            //mark visited in current search
            visited = true;
            
            //check its neighbor nodes
            for(Node node : nodes){
                //recursivly search each next node 
                if(node.hasCycle()){
                    return true;
                }
            }
            
            //reset node as unvisited to avoid the impact on next search
            visited = false;
            //if there is no cycle in this node, we will set it as cleanNode
            cleanNode = true;
            return false;
        }
        
        public void addNode(Node node){
            this.nodes.add(node);
        }
        
        public Node(){
            cleanNode = false;
            visited = false;
            nodes = new ArrayList<Node>();
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //create a node list, so we can visit node easier
        Node[] nodes = new Node[numCourses];
        
        //initialize each node
        for(int i = 0; i < nodes.length; i++){
           nodes[i] = new Node(); 
        }
        
        //update neighbors of each node 
        for(int[] pair : prerequisites){
            nodes[pair[0]].addNode(nodes[pair[1]]);
        }
        
        //scan array to check if there is any cycle
        for(Node node: nodes){
            if(node.hasCycle()) return false;
        }
        
        return true;
    }
}
