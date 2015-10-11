import java.util.*;

/*
 * Course Schedule II 
 * 
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses 
 * you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to 
 * finish all courses, return an empty array.
 * 
 * For example:
 *      2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
 * So the correct course order is [0,1]
 * 
 *      4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
 * Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Read more about how a graph is represented.
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 *  - This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, 
 *    no topological ordering exists and therefore it will be impossible to take all courses.
 *
 *  - Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining 
 *    the basic concepts of Topological Sort.
 *
 *  - Topological sort could also be done via BFS.
 * 
 *               
 */

/**
 * DFS/topological problem
 * 
 * Similar problem to Course Schedule (p207)
 * This problem can be more easily solved by BFS, where we record the degrees of each node, then only push nodes without
 * incoming edges into the queue. So I did not put it here. Instead, this solution is a DFS approach, which is very interesting
 * 
 * The tricky part is how to fill the array with correct order. In p207, we use DFS to detect cycle, here we can make use of DFS. Now we will fill
 * the result array AND detect cycle during visit. The problem requires us put the elementary course first. Since we use recursive DFS, we must
 * put those elementary courses in bottom, so we can fill them firstly during the backtrack. Therefore, we will use "depends on" relation. Visit
 * advanced course first, then their prerequisites...
 * 
 * The basic idea is not hard. We firstly create a course array, then fill the prerequisites for each course, then use DFS to visit each course.
 * During the DFS we will detect cycle while fill the result array
 * 
 * Remark:
 * We still use two parameters "cleanNode" and "visited" to speed up and make use of the DFS. But remember we need put "cleanNode" check first,
 * otherwise we may set "visited" to be true while never has a chance to reset it back due to the exit on "cleanNode" check!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 11, 2015 2:43:15 PM
 */
public class Course_Schedule_II_p210_sol1 {
    private class Node{
        int num; //course number, so we can fill the result array
        boolean cleanNode;//if we have visited this node before and it is clean, it will help reduce time
        boolean visited;//if this node has been visited in current path
        List<Node> nodes;//neighbor nodes
        
        private Node(int num){
            //You only need to use this - and most people only use it - when there's an overlapping local variable with the same name
            this.num = num;
            this.cleanNode = false;
            this.visited = false;
            this.nodes = new ArrayList<Node>();
        }
        
        private void addNode(Node node){
            nodes.add(node);
        }
        
        private boolean hasCycle(int[] result){
            //we want put cleanNode check first, so that we won't miss the reset for visited parameter
            if(cleanNode) return false;
            if(visited) return true;
            visited = true;//if we put cleanNode check after this, then we will never has a chance to reset "visited"
            for(Node node : nodes){
                if(node.hasCycle(result)){
                    return true;
                }
            }
            
            cleanNode = true;//cleanNode
            visited = false;//reset it back
            //we are doing backtrack style visit, and we want visit advanced course first then its prerequisites
            //so during the backtrack, we can fill the prerequisites first then advanced course
            //We don't need to visit the most advanced course first. For curr course, if it is prerequisites of
            //some other courses, then based on curr logic, those advanced courses will all placed after curr course
            result[index++] = num;
        
            return false;
        }
        
    }
    
    int index = 0;//global index
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Node[] nodes = new Node[numCourses];
        //initialization
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(i);
        }
        //update prerequisites
        for(int[] pair : prerequisites){
            //we will use "depends on" relation, so that we can visit advanced course first then element course,
            //which will give correct order during the backtrack, i.e. the reverse order of visit
            nodes[pair[0]].addNode(nodes[pair[1]]);
        }
        
        int[] result = new int[numCourses];
        //then detect cycle while fill our array
        for(Node node : nodes){
            if(node.hasCycle(result)) return new int[]{};
        }
        
        return result;
    }
}
