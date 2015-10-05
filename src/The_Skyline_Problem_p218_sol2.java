import java.util.*;

/**
 * Heap solution
 * 
 * In this solution, we firstly build key points for each edge. Then sort them based on x-axis first, then based on left or right edge and height
 * For same x-axis, we want put the higher left edge first, then put the its corresponding right edge last, like this: left:321 right:123
 * Differed from sol1, here we use real height for both left and right edges, so we can remove left edge based on the height on right edge.
 * 
 * After sort the key points on above order, we will begin insert key points. We will scan the list scope by scope and use max Heap
 * 
 * For left edge, we will add it into result as long as its height is larger than the highest edge in current scope or heap is Empty.
 * We also insert the height into heap
 * For right edge, it means we will remove a building from current scope. So we firstly move the building with corresponding height.
 * If now the heap becomes empty, then we will add new key point with height 0 to mark the termination of the skyline. After the removal,
 * If the right edge's height is larger than max height in current scope, then we will add a new key point with max height in current scope,
 * these two cases are where key points come from right edge
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 12:02:18 AM
 */
public class The_Skyline_Problem_p218_sol2 {
    public class Edge{
        int x;//edge x-axis
        int h;//edge height
        boolean isLeft;
        public Edge(int x, int h, boolean isLeft){
            this.x = x;
            this.h = h;
            this.isLeft = isLeft;
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
        List<Edge> edgeList = new ArrayList<Edge>();
        
        //In sol2, we create keypoint for each edge with given height
        //this is because we need right edge to remove left edge with same height
        for(int[] building : buildings){
            Edge left = new Edge(building[0], building[2], true);
            Edge right = new Edge(building[1], building[2], false);
            edgeList.add(left);
            edgeList.add(right);
        }
        
        //sort the edge list, mainly based on x-axis, but also considering height
        Collections.sort(edgeList, new Comparator<Edge>(){
           public int compare(Edge a, Edge b){
               if(a.x != b.x){
                   //smaller x-axis first
                   return a.x - b.x;
               }else{
                   //if same x-axis, then we need check if it is left or right edge
                   if(a.isLeft && b.isLeft){
                       //if both are left edge, we choose to sort descendingly
                       //so we won't add key point with lower height at same x-axis
                       return b.h - a.h;
                   }else if(!a.isLeft && !b.isLeft){
                       //if both are right edge, we choose to sort ascendingly
                       //so corresponding left and righ edges are in right place
                       return a.h - b.h;
                   }else{
                       //one is left edge and the other one is right edge
                       //we will add left edge first, so our right edge will cover this left edge
                       //i.e. the height of buildings from right edge still valid for the new building 
                       return a.isLeft? -1 : 1;
                   }
               }
           } 
        });
        
        //max heap for height (only from left edge), right edge will remove height from left edge
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        
        for(Edge edge : edgeList){
            if(edge.isLeft){
                //if it is a left edge
                //if it is higher than any building in current scope, we will add it to keypoint
                if(pq.isEmpty() || edge.h > pq.peek()){
                    result.add(new int[]{edge.x, edge.h});
                }
                
                //now this building has included into our scope
                pq.offer(edge.h);
            }else{
                //if it is a right edge
                
                //a building end here, we will remove it
                pq.remove(edge.h);
            
                //if we have no building in current scope, we will record a keypoint with height 0
                if(pq.isEmpty()){
                    result.add(new int[]{edge.x, 0});
                }else{
                    //if we still have building in current scope, then we will check if the ended building 
                    //is taller than the next building, if it is yes, then we will add a new keypoint for right edge
                    if(pq.peek() < edge.h){
                        result.add(new int[]{edge.x, pq.peek()});
                    }
                }
            }
        }
        
        return result;
    }
}
