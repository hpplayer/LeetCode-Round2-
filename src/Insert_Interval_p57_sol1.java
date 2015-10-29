import java.util.*;
/*
Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/

/**
 * Problem specific solution and we changed the structure of original input list
 * If we are not allowed to do that, then we can simply create a new list, and add element from original input to new list 
 * in each step, i.e. add in first part, then add newInterval, then add second part
 * 
 * This solution split the input lists into three parts
 * The first part contains all intervals that are before newInterval, which should have end < newInterval.start
 * The second part contains all intervals that will be affected by newInterval, we will combine them together, so finally
 * we will get only one merged interval for this part
 * The third part contains all intervals that are after newInterval, which should have start > newInterval.end
 * 
 * We use a pointer i to find the second part. Since we are changing the original input, we have to delete and merge all intervals 
 * in second part, it's like left shift the array. After we are done with second part, we just need to add the merged newInterval
 * node to index i, then we are done
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 4:40:15 PM
 */

public class Insert_Interval_p57_sol1 {
	public class Interval {
	     int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	     Interval(int s, int e) { start = s; end = e; }
	}
	 
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i = 0;//pointer in intervals, we will use it to find the place to insert newInterval
        
        //skip the intervals that will not be affected by the start of newInterval
        //if newInterval is the smallest one, then we won't move i at all
        while(i < intervals.size() && intervals.get(i).end < newInterval.start) i++;
        
        //merge all intervals that will be affected by the newInterval
        //we will update newInterval accordingly if affected intervals has a smaller start index or has
        //a larger index
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            intervals.remove(i);//remove affected intervals due to merge completed
        }
        
        //i tells exactly where we should insert newInterval, so add newInterval at index i
        //remember to insert newInterval at index i, since we may still have third parts left or we don't have first part at all(need
        //insert at index 0)
        intervals.add(i, newInterval);
        
        return intervals;
    }
}
