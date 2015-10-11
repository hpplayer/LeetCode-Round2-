import java.util.*;

/*Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to 
 * finish all courses?
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So it is possible.
 * 
 *      2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic 
 *    concepts of Topological Sort. (https://class.coursera.org/algo-003/lecture/52)
 *
 *  - Topological sort could also be done via BFS. (http://en.wikipedia.org/wiki/Topological_sorting#Algorithms)
 * 
 */
/**
 * Topological/cycle-detect problem w/ BFS Solution
 * 
 * This problem can be treated as a graph problem, and whether we can have a valid course schedule depends on whether we have a cycle
 * in the graph. To detect cycle in a graph, we can either use DFS or BFS. Sol1 is the BFS solution.
 * 
 * The key idea is to focus on nodes without incoming edges, i.e. courses that doesn't have prerequisites.
 * We will start from nodes without incoming edges, add them into queue. This can be treated as first layer. 
 * Then we remove outgoing edges from first layer, and add all updated nodes that do not have incoming edges into the queue, this 
 * can be treated as second layer.
 * For nodes in cycle, we will never have chance to cut a edge of cycle, so those nodes will always have incoming edges. We can use 
 * property to detect cycle. We use queue to visit graph and only add nodes that can have 0 incoming edges to queue. So finally for 
 * those nodes that never in our queue will be nodes in cycle. 
 * 
 * 
 * Remark:
 * 1) If we have a cycle between a and b, then the cycle will always be there. So we can either treat each edge as "depends on" or 
 * "decides", i.e. the direction can be reversed. But for easy understanding, it is more intuitive to treat the course without 
 * prerequisites as root node, which means we will have "decides" edge
 * 2) in both sol1 and sol2, I try to describe the problem more like a graph problem.
 * 
 * Sol1 provides a BFS solution that use queue to visit graph and only add nodes without incoming nodes
 * Sol2 provides a recursive DFS solution that detect cycle with traditional way, we speed up the DFS by mark all nodes
 * that have been visited before. They may in cycle or not in cycle, so we need two parameters to record that.
 * 
 * 
 * @author hpPlayer
 * @date Oct 10, 2015 10:39:11 PM
 */

public class Course_Schedule_p207_sol1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //num of incoming edges each node has
        int[] degrees = new int[numCourses];
        
        //we need a HashMap to record each node and nodes that depend on it.
        //ex: [1,0], then hashMap should be [0:1], which means for node 0, we have one node depende on 1, which is node 1
        
        HashMap<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        
        for(int i = 0; i < numCourses; i++){
            //initialize the hashMap
            hs.put(i, new ArrayList<Integer>());
        }
        
        //based on the prerequisites[], we can fill degree[] and hs
        for(int[] pair : prerequisites){
            //we only care about incoming edges. node a has an incoming edge from b, means a depends on b
            //now we have pair[0] depends on pair[1], so degree[pair[0]]++
        	degrees[pair[0]]++;
            
            //after visit one node, we will remove all its outcoming edges and free the dependency of nodes
            //that have incoming edges from it. So in hashMap we need to record each node and all nodes that 
            //depend on it.
            hs.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> que = new LinkedList<Integer>();
        
        //we will firstly add all nodes without incoming edges into the queue.
        //In this problem, it is same as the courses that dont have prerequisites
        for(int i = 0; i < numCourses; i++){
            if(degrees[i] == 0) que.offer(i);
        }
        
        int count = 0;
        
        //then we will use BFS to scan all courses. During the visit we will remove outgoing edges from current 
        //layer, then add nodes in new layer without incoming edges.
        //If some nodes are in a cycle, then they will always have incoming edges, so they will never go into our 
        //queue. finally we just need to compare nodes that once existed in our queue with the total input nodes,
        //we can easily find how many nodes are in cycles
        
        while(!que.isEmpty()){
            //get a node from current layer
            int curr = que.poll();
            
            //we will remove all outgoing edges from this node
            for(int nextNode : hs.get(curr)){
                //After remove the edge, if some nodes now do not have incoming edges, we will add it into queue
                if(--degrees[nextNode] == 0){
                     que.offer(nextNode);
                }
            }
            
            //let count ++ to count how many nodes in our queue
            count ++;
        }
        
        return count == numCourses;
    }
}
