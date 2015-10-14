import java.util.*;

/**
 * heap solution
 * 
 * The trick part is to be careful with the operation on heap, we have to always keep the heap valid
 * 
 * In this solution, we firstly sort the array based on start time, so we will put meetings that are close together.
 * Then we use a minHeap to track the meeting that will end earliest, we call it "top". If next meeting' start time  < "top".end time
 * that means, there is no room that be available when next meeting start, so we have to open a new room. However if next meetings'start
 * time >= "top".end time, then we know at least room occupied by "top" meeting will be available to next meeting. So we can use this room,
 * then update this room's information instead (we only care about the end time of "top", so we can only update the end time)
 * 
 * 
 * Remark:
 * 1) if we use peek(), and change the end directly on peek(), then this operation will break the heap, since the heap will not heapfiy automatically
 * on such operation. To avoid that, we have to poll() top element first, then change value, then push it back! Remember this!!!!!!!!!!  
 * 2) Assume we have n meetings, firstly we sort the array costs O(nlogn), then we will check each interval again and do operations on 
 * heap, this part also costs O(nlogn), so the total time is O(nlogn)
 * 
 * Sol1 provides a heap solution, where the interval in heap can be treated as "room", the running time is O(nlogn)
 * Sol2 provides a hashMap solution, where each room is split based on its start and end time, the running time is still O(nlogn), but
 * n is reduced from 2n and logn is reduced from logn + n, so the total running time should be slower than sol1
 * 
 * So sol1 is recommended, but sol2 also provides a suggestive solution. Both are recommended
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 3:50:28 PM
 */
public class Meeting_Rooms_II_p253_sol1 {
	public static void main(String[] args){
		Interval[] intervals = {new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23),new Interval(4, 9)};
		System.out.println(new Meeting_Rooms_II_p253_sol1().minMeetingRooms(intervals));
	}
    public int minMeetingRooms(Interval[] intervals) {
        //firstly sort the intervals based on start point, so make the input more clear
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                //In array, we wanna put meeting starts earliest first 
                return a.start - b.start;
            }    
        });
        
        //then create a minHeap, which always put the meeting that will end earliest on top
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                //In heap, we wanna put meeting end earliest first 
                return a.end - b.end;
            }                 
        });
        
        for(Interval temp : intervals){
            if(pq.isEmpty()){
                //boundary case, put first interval in heap
                pq.offer(temp);
                continue;
            }
            
            //if we directly make change on value in heap (like modify pq.peek()),
            //then the heap will not automatically adjust the heap
            //To make our heap always valid, we have to poll() then push() manually, instead of peek()
            Interval endEarliest = pq.poll();
            
            //if endEarliest.end time is still > next meeting's start time, we have no choice but to open a new room
            if(endEarliest.end > temp.start){
                pq.offer(temp);
            }else{
            //if endEarliest.end time <= next meeting's start time, then we can use the same room, we just extend
            //this room's end time to the new meeting's end time
                endEarliest.end = temp.end;
            }
            
            //put it back to force heapify
            pq.offer(endEarliest);
        }
        
        //we will not remove rooms from heap, but add new one to it. 
        //so pq.size() is the max room we need
        return pq.size();
    }

}
