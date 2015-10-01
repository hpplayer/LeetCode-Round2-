import java.util.*;

/**
 * Min heap approach
 * 
 * The tricky part is to notice the overflow problem and handle unfamiliar Long type
 * Compared with dp approach, this approach is slower due to operations on heap and buggy due to overflow problem
 * But it provides another way to attack the problem.
 * 
 * The basic idea is using a MinHeap to contain current ugly numbers. Each time we will use the smallest 
 * ugly number in heap to generate new ugly numbers. Unlike sol1, here we keep three lists with same step
 * i.e. we don't care if we have used the head number in each list yet, we just generate a new number to each
 * list based on the smallest ugly number. Thus it may cause overflow, since maybe the last valid ugly int number
 * is got from *2, but we also calculate the one *5, thus cause overflow. To avoid it, we have to use Long type
 * Thus, we should know some basics of Long type like casting 1L or conversion a.intValue()
 * 
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 3:44:47 PM
 */
public class Ugly_Number_II_p264_sol2 {
    public int nthUglyNumber(int n) {
        //to avoid overflow, we use Long type
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        pq.offer(1l);// to convert 1 from int to long, we need use 1l
        
        //we have push 1, so the loop only executes n-1 times
        for(int i = 1; i < n; i++){
            Long currMin = pq.poll();//use currMin to generate next smallest possible nums
            
            //to avoid duplicates, we will remove all numbers have same value with currMin
            //Notice: Long is object, so we must use equals(), not ==
            while(!pq.isEmpty() && currMin.equals(pq.peek())) pq.poll();
            
            pq.offer(2 * currMin);
            pq.offer(3 * currMin);
            pq.offer(5 * currMin);
        }
        
        
        return pq.peek().intValue();
    }
}
