import java.util.*;

/*
Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/		
		
/**
 * 
 * This problem can be attacked by two ways
 * 1) Sort whole list then merge, where we just sort the list based on start. Then we scan the sorted list, if next Interval's start <= curr Intervals.end
 * then we know can merge two intervals, the merged interval's end is max(curr interval's end, next intervals'end). This is trivial, so I did not list it here
 * 
 * 2) Use traditional mergeSort, which is sol1 as below
 * 
 * In this solution, we firstly recursively split the list, after reach the bottom, we begin merge. The merge is based on start value 
 * in each interval. But here when we add a new interval to the result list, we need to consider the end value. If tail in the list
 * has a value >= new interval' start value, that means we can actually merge 2 intervals into 1, otherwise we will add a new 
 * interval.
 * 
 * To make the code clear, we use three helpers
 * AddOrMerge() will work on inserting the new interval to result list, and it consider the situation talked above
 * merge() will work on the lists of interval, since we always split the input list into half, then when we are in backtracking, 
 * we will get individual list from the split part, so we need a merge() to merge list
 * mergeSort() is the main function, which firstly recursively split the input list into halve, then merge the list from each split part
 * 
 * Remark:
 * 1. There is another great problem can be solved by MergeSort: problem The Skyline Problem (p218) check this out
 * 2. problem Meeting Romms (p253) is very similar to this problem
 * @author hpPlayer
 * @date Oct 4, 2015 3:05:56 PM
 */

public class Merge_Intervals_p56_sol1 {
	
	 public class Interval {
		     int start;
		    int end;
		    Interval() { start = 0; end = 0; }
		     Interval(int s, int e) { start = s; end = e; }
		}
	 
	    public List<Interval> merge(List<Interval> intervals) {
	        return mergeSort(0, intervals.size()-1, intervals);
	    }
	    
	    //merge sort recursively
	    public List<Interval> mergeSort(int start, int end, List<Interval> intervals){
	        List<Interval> result = new ArrayList<Interval>();
	        
	        if(start > end){
	            return result;// out of boundary
	        }
	        
	        if(start == end){
	            //single unit,  the boundary case
	            result.add(intervals.get(start));
	            return result;
	        }
	        
	        //otherwise we will split the list into half, then do mergeSort recursively
	        int mid = start + (end - start)/2;//make sure end > start, otherwise we will get error
	        
	        return Merge(mergeSort(start, mid, intervals), mergeSort(mid +1, end, intervals));
	    }
	    
	    public List<Interval> Merge(List<Interval> list1, List<Interval> list2){
	        int i1 = 0, i2 = 0;
	        List<Interval> result = new ArrayList<Interval>();
	        while(i1 < list1.size() && i2  < list2.size()){
	            
	            if(list1.get(i1).start < list2.get(i2).start){
	                //if interval1.start < intervals2.start, then we need add i1
	                addOrMerge(result, list1.get(i1 ++));
	            } else{
	                //if we have done i1, or interval2.start < intervals1.start, then we need add i1
	                addOrMerge(result, list2.get(i2 ++));
	            }
	        }
	        
	        while(i1 < list1.size())  addOrMerge(result, list1.get(i1 ++));
	        while(i2 < list2.size())  addOrMerge(result, list2.get(i2 ++));
	        
	        return result;
	    }
	    
	    public void addOrMerge(List<Interval> list, Interval i){
	        //check if we need to merge i to last interval, or just add a new interval
	        if(list.size() == 0){
	            list.add(i);
	            return;
	        }
	        
	        Interval last = list.get(list.size() -1);
	        if(last.end >= i.start){
	            //if last interval's range covered part of input interval, then we can merge them
	            last.end = Math.max(last.end, i.end);
	        }else{
	            //add a new one
	            list.add(i);
	        }
	        
	    }
}
