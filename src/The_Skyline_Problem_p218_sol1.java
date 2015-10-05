import java.util.*;

/*
 * 
 * The Skyline Problem
 * 
 * 
* A city's skyline is the outer contour of the silhouette formed by all the buildings 
* in that city when viewed from a distance. Now suppose you are given the locations and 
* height of all the buildings as shown on a cityscape photo (Figure A), write a program 
* to output the skyline formed by these buildings collectively (Figure B).
* 
*  ^                                        ^                                                                   
*  |                                        |                                                                   
*  |                                        |                                                                   
*  |    +-----+                             |    O-----+                                                        
*  |    |     |                             |    |     |                                                        
*  |    |     |                             |    |     |                                                        
*  |    |  +--+------+                      |    |     O------+                                                 
*  |    |  |         |                      |    |            |                                                 
*  |  +-+--+----+    |   +------+           |  O-+            |   O------+                                      
*  |  |         |    |   |      |           |  |              |   |      |                                      
*  |  |         |    |   |    +-+--+        |  |              |   |      O--+  
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  +--+---------+----+---+----+----+--->    +--+--------------O---+---------O--->                               
*  
*   https://leetcode.com/static/images/problemset/skyline1.jpg  
*   https://leetcode.com/static/images/problemset/skyline2.jpg  
* 
* The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
* where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
* and Hi is its height. It is guaranteed that 0 ¡Ü Li, Ri ¡Ü INT_MAX, 0 , and Ri - Li > 0. 
* You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
* 
* For instance, the dimensions of all buildings in Figure A are recorded as: 
*  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
* 
* The output is a list of "key points" (red dots in Figure B) in the format of 
* [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
* A key point is the left endpoint of a horizontal line segment. 
*
* Note that the last key point, where the rightmost building ends, is merely used to mark 
* the termination of the skyline, and always has zero height. Also, the ground in between 
* any two adjacent buildings should be considered part of the skyline contour.
* 
* For instance, the skyline in Figure B should be represented as:
*  [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
* 
* Notes:
* 
*  - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
*  - The input list is already sorted in ascending order by the left x position Li. 
*  - The output list must be sorted by the x position. 
*  - There must be no consecutive horizontal lines of equal height in the output skyline. 
*    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
*    the three lines of height 5 should be merged into one in the final output as such: 
*    [...[2 3], [4 5], [12 7], ...]
* 
*/

/**
 * Merge Sort solution
 * 
 * This problem is very similar to problem Merge Intervals (p56), both of them use mergeSort
 * The only difference is how they merge parts. In p56, we merge two parts mainly based on start index but also considering end inex
 * Here, we merge parts mainly based on a-axis but also considering height. We wouldn't add new Key point to the result list if it has 
 * same height with height of last key point in the result list.
 * 
 * In this solution, we firstly split the input list into halves. After we reach the single building, we will building two key points for it.
 * I call those key points as "raw" key points as they may not be inserted into our final result. We will build two key points for each building.
 * That's because key points only come from the left or right edge of a building. Then we start backtrack, and merge key points. To merge two
 * "raw" key points, we will always pick the one with smaller indexes in x-axis. We also need two variables to mark current height from two lists.
 * This is like, now we are looking at building 2 from list 2, which starts before the end of building 1 from list 1. So we may or may not need
 * a key point depends on whether building 1 has a taller height than 2. So, after we compare the height, we will decide whether to insert current 
 * key point into the result list. To be convenient, and in case our result list is empty, in this solution we create a "temp" key points for each
 * row key point, which has the height of max(height1, height2). We will insert temp key point only if its height is differed from last key point
 * in result list
 * 
 * Remark:
 * Since we will frequently use pollFirst() and addLast(), it is better to use a LinkedList instead of ArrayList
 * The time complexity should be similar to Merge sort, which is nLog(n)
 * 
 * Sol1 uses mergeSort solution
 * Sol2 uses max-heap solution
 * 
 * Sol1 is faster and cleaner, so sol1 is recommended
 * in my round 1 solution, I also provide a optimized sol2 solution, but it is too complicated so I did not put it here
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 10:34:16 PM
 */
public class The_Skyline_Problem_p218_sol1 {
    public List<int[]> getSkyline(int[][] buildings) {
        return mergeSort(0, buildings.length -1, buildings);
    }
    
    public LinkedList<int[]> mergeSort(int start, int end, int[][] buildings){
    	LinkedList<int[]> result = new LinkedList<int[]>();
        if(start > end) return result;//boundary case, if input builings has 0 length
        if(start == end){
            //now we are looking at a single building
            //Each building may have 2 key points in the final list, so we add both left and right edge,
            //to be more specifically, left edge would has current height while right edge only has 0 height
            int[] building = buildings[start];
            result.add(new int[]{building[0], building[2]});
            result.add(new int[]{building[1], 0});
            return result;
        }
        
        int mid = start + (end - start)/2;
        //like merge sort, we always split the list into halves, then merge backward
        return Merge(mergeSort(start, mid, buildings), mergeSort(mid + 1, end, buildings));
    }
    
    public LinkedList<int[]> Merge(LinkedList<int[]> list1, LinkedList<int[]> list2){
        LinkedList<int[]> result = new LinkedList<int[]>();
        //It is like, each time we poll a head building from list, the current buildings from this list
        //will has this height. To insert a keyPoint, we need compare the height of both current buildings
        //and build a keypoint with higher height
        int h1 = 0, h2 = 0;//current height in list1 and list2
        
        while(!list1.isEmpty() && !list2.isEmpty()){
            int newX = 0, newH = 0;//the x-axis and height of new keypoint
            if(list1.get(0)[0] < list2.get(0)[0]){
                //if head building in list 1 has smaller x-axis, then we will use this one
                int[] building1 = list1.pollFirst();
                newX = building1[0];
                h1 = building1[1];//update current height in list1
                newH = Math.max(h1, h2);//height of new keypoint is the max height of h1, h2
            }else if(list1.get(0)[0] > list2.get(0)[0]){
                //if head building in list 2 has smaller x-axis, then we will use this one
                int[] building2 = list2.pollFirst();
                newX = building2[0];
                h2 = building2[1];//update current height in list2
                newH = Math.max(h1, h2);//height of new keypoint is the max height of h1, h2                
            }else{
                //same x-axis, we poll both, but use the one with higher height
                 int[] building1 = list1.pollFirst();
                 int[] building2 = list2.pollFirst();
                 newX= building1[0];//same x-axis, just choose any one
                 h1 = building1[1];//update current height in list1
                 h2 = building2[1];//update current height in list2
                 newH = Math.max(h1, h2);//height of new keypoint is the max height of h1, h2                
            }
            
            //we will only add a new keypoint if the result is empty or current keypoint has different height
            //with last keypoint in result list (maybe taller or lower)
            if(result.isEmpty() || result.getLast()[1] != newH){
                result.add(new int[]{newX, newH});
            }
        }
        
        //if one the list is done, and the other list still has buildings, we just add them all
        if(!list1.isEmpty()) result.addAll(list1);
        if(!list2.isEmpty()) result.addAll(list2);
        
        return result;
    }
}
