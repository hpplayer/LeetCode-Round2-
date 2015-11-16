import java.util.*;

/**
 * Bucketing solution!
 * 
 * The difficulty is to come up with the idea of bucketing and be careful about overflow
 * 
 * We make each bucket to be size of t +1, so that each val in same bucket will be guaranteed to be duplicates. Ex: t = 2, [1,2,3]
 * But we may also have duplicates in next and prev bucket, since last val in curr bucket + t + 1 = last val in next bucket and
 * first val in curr bucket - t - 1 = first val in prev bucket. But last val in curr bucket - t - 1 = first val in current bucket and
 * first val in curr bucket + t + 1 = last val in prev bucket, which means we got value in prev/next bucket does not necessaily mean
 * we got duplicates. So we need to check the value if we got some values in prev/next bucket. 
 * 
 * Another problem is scope. if abs(neg) = abs(pos), then neg and pos will points to a same bucket. To avoid such case, we will convert 
 * all values to pos or neg by - int_min or - max_min. Thus it may cause overflow problem. To solve that, we will use long type in operation
 * 
 * For the boundary of k, we simply use the technique in problem Contains Duplicate II (p219) as sliding win.
 * 
 * Remark:
 * we use val/(t + 1) to get the bucket No. But we may have the case that t > Integer.MAX_VALUE, so we need casse (t+1) as well
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 3:16:04 AM
 */
public class Contains_Duplicate_III_p220_sol2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //we need two distinct indices, so k < 1 is not allowed
        //t < 0 is meaningless for difference, since we can always looks from another direction
        if(k < 1 || t < 0) return false;
        int left = 0;//keep left boundary of win
        HashMap<Long, Long> hs = new HashMap<Long, Long>();//key is bucket No, val is value
        for(int i = 0; i < nums.length; i++){
            if(i > k){//need move left bound
                //to avoid the mix of pos and neg, we convert all neg to po or to convert all pos to neg
                //to achieve that, we just need to - int_min, then all neg will be pos, while pos still be pos
                //similarly, if we want convert all pos to neg, - int_max, then all pos will be neg, while
                //neg will still be neg
                
                long val = (long) nums[left] - Integer.MAX_VALUE; //don't forget casting!!!!
                
                //why t + 1? since a bucket with t+1 size will make the bucket just fill the number within t range
                //like t = 2, bucket [1,2, 3] and any number from 1 to 3 (both inclusive) will be duplicate
                long bucket = val / ((long) t + 1);
                hs.remove(bucket);
                left ++;
            }
            
            long val = (long) nums[i] - Integer.MAX_VALUE;
            long bucket = val / ((long) t + 1);           
            
            //if same bucket contains duplicate, or the previous bucket contains duplicate, or the 
            //next bucket contains duplicate, we will all report it. Why prev and next bucket can contain
            //duplicate? since each bucket has size t + 1, so the last val + t + 1 will be the last val in next
            //bucket, and the first val - t - 1 will be the first val in prev bucket 
            if(hs.containsKey(bucket) || hs.containsKey(bucket - 1) && val - hs.get(bucket - 1) <= t ||
            hs.containsKey(bucket + 1) &&  hs.get(bucket + 1) - val <= t) return true;
            
            hs.put(bucket, val);
        }
        
        return false;
    }
}
