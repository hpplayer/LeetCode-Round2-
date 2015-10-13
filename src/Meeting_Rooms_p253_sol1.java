import java.util.*;

/*
Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
*/

/**
 * Sort problem
 * 
 * This problem is very similar to problem Merge Intervals (p56).
 * The problem itself is not hard, but I want to discuss some things about Java built-in sort, so I still put it here.
 * 
 * In p56, the input is list, so we can split and merge easily. Here the input is array, so it is not easy to apply merge-sort
 * In sol1, I just use built-in sort algorithm to sort the array based on the meeting start time, then we just need
 * to check if two consecutive meetings have overlap, then we can tell if a person can attend all meetings
 * 
 * Remark:
 * 1. The built-in sort algorithm in Java 7 and later is Timesort
 *    best case O(n), worst case O(nlogn)
 * 2. I tried to use bottom-up merge sort algorithm, but I found it is also not easily to apply bottom-up merge sort. We need indexes, aux array, etc
 *    a standard bottom-up merge sort working on list can be found in Sort_List_p148_sol1
 *    
 * @author hpPlayer
 * @date Oct 13, 2015 2:29:36 PM
 */

public class Meeting_Rooms_p253_sol1 {
	public static void main(String[] args){
		Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
		System.out.println(new Meeting_Rooms_p253_sol1().canAttendMeetings(intervals));
	}
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        
        //check if any two consectuive meetings have overlap
        for(int i = 0; i +1 < intervals.length; i++){
            //if next meeting starts before curr meeting end, then return false
            if(intervals[i].end > intervals[i+1].start){
                return false;
            }
        }
        
        return true;
    }
}
