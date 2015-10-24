import java.util.*;

/*
Best Meeting Point

A group of two or more people wants to meet and minimize the total travel distance.
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance,
where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?

*/

/**
 * More explanation will be updated tomorrow
 * 
 * Update:
 * 
 * Observation problem
 * 
 * The tricky part is to observe the way we used to calculate the  Manhattan Distance. 
 * 
 * Firstly Let's look at the 1D case, say we have two people on same row, one in head(index: 0) and the other one in tail(index: n).
 * To let them meet each other, they must cross at least n distance. The meeting point can be anywhere between them, but the meeting distance
 * will not change.Let's say now we have two more people in the same row. We can only look this two people, their meeting distance must also be
 * the difference of their index. Then the total meeting distance will be the sum of two meeting distance. But this time the meeting time will
 * not be anywhere, but a pointer between the new people. So assume we have people everywhere in the same row, then the meeting point must be
 * the mid cell. So when calculating the sum of Manhattan Distance in same row, we can just sum the difference in indexes in which one index
 * is from left and the other one is from right. 
 * 
 * Now let's look at 2D case. Basically, the solution is same, but now we also need to consider the distance from column. Fortunately we can
 * divide the problem to two 1D case based on definition of Manhattan Distance, the calculation of delta(x) and delta(y) is separated.
 * So we use same rule to the column calculation. That is the distance is the sum of all index pair, and the meeting point should be in mid
 * (or more accurate, the median, let's say we have [1,1,5], then the meeting point should be at 1, which is in left of "mid"). 
 * 
 * @author hpPlayer
 * @date Oct 23, 2015 1:13:34 AM
 */

public class Best_Meeting_Point_p296_sol1 {
    public int minTotalDistance(int[][] grid) {
        //we can split the 2D problem to two 1D problems based on the definition of Manhattan Distance
        List<Integer> SameRow = new ArrayList<Integer>();
        List<Integer> SameCol = new ArrayList<Integer>();
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    //add people's location into two lists
                    SameRow.add(j);
                    SameCol.add(i);
                }
            }
        }
        
        //we just need to calculate the sum of difference of indexes 
        //so we want the index be sorted in two lists
        
        //sameCol is already sorted since our outer loop is based on sameCol we must add index on non-descending order
        //so only samRow needs to be sorted
        Collections.sort(SameRow);
        
        //the Manhattan Distance is just |p2.x - p1.x| + |p2.y - p1.y|, which is the sum of sameRow and sameCol
        return getDistance(SameRow) + getDistance(SameCol);
    }
    
    public int getDistance(List<Integer> list){
        
        //get the difference of indexes in the list
        int start = 0, end = list.size() -1;
        int result = 0;
        
        while(start <= end){
            //end value >= start value, so use end.val - start.val
            result += list.get(end) - list.get(start);
            start ++;
            end --;
        }
        
        return result;
    }
}
