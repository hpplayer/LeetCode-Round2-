import java.util.*;

/*
Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

/**
 * Hash problem
 * 
 * The tricky part is to come up with the idea of using hash and be clear about the way to search the Consecutive Sequence
 * 
 * We firstly push all numbers into HashSet
 * 
 * Then we scan the array, if we found the hashSet does not contain num - 1, then we now num will be a start of new consecutive Sequence,
 * so we just need to iteratively search the HashSet with num + 1 to find the max length of consecutive Sequence.
 * For any other number in this sequence, they will all have num -1 in hashSet, so we will not search this sequence twice.
 * Finally we just need to update the result variable to be the max one, and we are done
 * 
 * Remark:
 * when search the len of seq, we will stop when we find current num is not in hashSet. So this number - our start number will just
 * give us the len of seq. Example: [1,2,3], we start with 1 and end at 4, 4 -1 =3 is just the len of seq.
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 1:23:48 AM
 */

public class Longest_Consecutive_Sequence_p128_sol1 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        //first, add all nums into hashSet
        for(int num : nums){
            hs.add(num);
        }
        
        int result = 0;
        
        for(int num : nums){
            //then search the start of Consecutive Sequence.
            //this number will be the first number in Consecutive Sequence, which means our hs will not 
            //contain num - 1 in it
            if(!hs.contains(num -1)){
                //found new start number, begin search the len of seq
                int start = num;
                while(hs.contains(start)){
                    start ++;
                }
                
                //update result accordingly (since start is end with 1 more unit, the difference of start and num will be the len)
                result = Math.max(start-num, result);
            }
        }
        
        return result;
    }
}
