import java.util.*;

/**
 * Hash solution
 * 
 * The tricky part is to come up with such idea
 * 
 * Here, we won't track the "top" room, instead, we will run the program based on the timestamp. So we will sort the data structure based
 * on timeStamp, instead of meeting as a whole.
 * 
 * The basic idea is to use a HashMap to include all timestamps(start/end), the value in hashMap means how many rooms will be occupied
 * starts from this moment. Note: we may have multiple meetings starts or ends at this moment, we will only count the num of real meetings
 * that starts from here, other meetings will be cancelled. If we got an negative number, then it means we won't starts new meeting,
 * but we only clear old finished meeting at this moment. 
 * 
 * Then we will scan the keySet() of the hashMap, to find what is the maximum count of real meeting that we will have at the same period,
 * that will reflect how many rooms we really need. Since in the end the sum of value must be 0 (we have +1 -1 for each meeting),
 * we have to sort the key during the update of hashMap, otherwise, we may incorrectly cancel meeting and make incorrect result
 * 
 * Remark:
 * 1) time complexity:
 * Firstly, we use TreeMap to keep (2*n) keys sorted, this would costs O(2nlog2n), then we will traversal keySet() to find the max room
 * we need, this costs O(2n), so the total time is O(2nlog2n + 2n), or simply O(nlogn) if n is large
 * 2) remember to sort the key in hashMap. Sol2 is based on TimeStamp, so it must be sorted!!!!!!!
 * @author hpPlayer
 * @date Oct 13, 2015 5:04:52 PM
 */
public class Meeting_Rooms_II_p253_sol2 {
	public static void main(String[] args){
		Interval[] intervals = {new Interval(9, 10), new Interval(4, 9), new Interval(4, 17)};
		System.out.println(new Meeting_Rooms_II_p253_sol2().minMeetingRooms(intervals));
	}
    public int minMeetingRooms(Interval[] intervals) {
        //we must sort the timestamp, otherwise we may incorrectly use offset and skip the max room usage
        /*
    	java.util.HashMap makes no guarantees as to the order of the map; in particular,
    	it does not guarantee that the order will remain constant over time.
    	So we have to use treeMap to sort the key
    	*/
    	//key is timeStamp, value is num of room that will be occupied start from this moment. 
    	//If a room will be cleared from this moment, then we simply let count --
    	
        TreeMap<Integer, Integer> hs = new TreeMap<Integer, Integer>();
        
        for(Interval temp : intervals){
            //put timestamp in map
            if(!hs.containsKey(temp.start)) hs.put(temp.start, 0);
            if(!hs.containsKey(temp.end)) hs.put(temp.end, 0);
            
            //based on timestamp to mark the usage of rooms
            hs.put(temp.start, hs.get(temp.start) + 1);//start a new meeting at this moment
            hs.put(temp.end, hs.get(temp.end) - 1);//end an old meeting at this moment
        }
        
        int rooms = 0, maxRoom = 0;
        for(int temp : hs.keySet()){
            //update room availability
            rooms += hs.get(temp);
            maxRoom = Math.max(rooms, maxRoom);
        }
        
        return maxRoom;
    }
}
