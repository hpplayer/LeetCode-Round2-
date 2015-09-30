import java.util.*;

/*
Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ¡Ü k ¡Ü input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window¡¯s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/

/**
 * dequeue problem
 * 
 * The tricky part is to come up with the idea of using dequeue, and observe that we don't need to keep a size of win numbers in 
 * deq. This problem can also be attacked by using MaxHeap, but it is trivial nor efficient, so I did not put it here.
 * 
 * In this problem, we have a sliding window, in which we can remove elements from one end and insert elements in another end.
 * So it seems dequeue structure is very perfect for such operation. But how can we make the deque behaves like MaxHeap? The solution
 * needs a very important observation: for each new input, we can remove all elements in current deque that have smaller value than
 * it. Why? because, new input has a higher value also a farther index, it will be a better candidate for max value in current win,
 * so we can remove those useless elements. If we keep doing this, then our dequeue will follow descending order, so each time
 * we just need to remove elements from tail. 
 * The basic idea is like this: we will create a dequeue, the value in which is the index. For each loop, we will firstly check
 * if we need remove the leftmost index in dequeue, if our win has passed it. Then we will remove all elements in dequeue that
 * have smaller values than new input, the removal starts from tail. Then we insert new input into the tail of deque. Then 
 * we just simply fill our result array based on the leftmost value
 * 
 * Time complexity: O(n)
 * 
 * @author hpPlayer
 * @date Sep 29, 2015 1:38:10 AM
 */


public class Sliding_Window_Maximum_p239_sol1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        
        if(nums.length == 0) return result;
        
        Deque<Integer> deq= new LinkedList<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            //remove leftmost index if already out of boundary
            if(!deq.isEmpty() && deq.peekFirst() < i - k + 1){
                deq.pollFirst();
            }
            
            //we will remove all indexes in deque that has value smaller than input value
            //since we will do this to all inputs, our final deque will be in descending order
            //So, that's why we remove indexes start from tail, because it contains the smallest value 
            while(!deq.isEmpty() && nums[deq.peekLast()] < nums[i]){
                deq.pollLast();
            }
            
            //insert new input
            deq.offerLast(i); 
            
            //if we have gained enough win size, then we will insert into result
            if(i >= k-1){
                //think about i = k-1, and we need to fill index 0, so the offset would be - k +1
                //like I said above, number in deq is in descending order
                //the leftmost number is the largest one
                result[i - k + 1] = nums[deq.peekFirst()];
            }
        }
        
        return result;
    }
}
