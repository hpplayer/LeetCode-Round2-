/*
Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/**
 * Bucketing solution
 * 
 * The tricky part is to be clear with the logic of bucketing algorithm and be careful with several minor but important issues
 * 
 * The given input is unsorted, and it requires us to solve it with O(n) time. So we have to provide information when scanning the input 
 * array. Bucketing is a very good approach, we assign each input into desired bucket, then only look the difference between neighbor buckets
 * 
 * Firstly, we calculate the average size of gap from inputs by (max - min) / (N-1) (where N -1 is number of gaps), then take ceiling to
 * get the min value of MAX-GAP. Then we create buckets that new gap in each bucket is at most gap -1. This is done by (max-min)/gap + 1
 * We plus one here, to include all numbers (think about two number cases [min, max], their difference is gap, but we define the gap
 * in one bucket <= gap - 1, so max and min will no longer in same bucket, therefore we need + 1 buckets). By defining such way, we don't 
 * need to look at numbers in same bucket, we just need to care about numbers in neighbor buckets. There are two pairs of such number 
 * in each bucket, i.e. the heading and tailing number. We just calculate the difference between the tailing/heading number in neighbor
 * bucket and we can get the Maximum Gap in successive elements
 * 
 * Remark:
 * 1. we build the buckets based on max and min, so when calculating the No. of buckets, we should also convert each input into "min" based
 * 2. Don't forget boundary case that (max-min == 0 ) i.e. all inputs are same !!!!!!!
 * 3. Don't forget the case some buckets may be empty, so we need a prev variable to hold the tailing number in last valid bucket
 * 
 * Sol1 provides a bucketing solution
 * Sol2 provides a radix solution
 * Both runs in O(n) time, so both are recommended
 * 
 * @author hpPlayer
 * @date Oct 24, 2015 10:06:25 PM
 */
public class Maximum_Gap_p164_sol1 {
    private class bucket{
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE; 
    }

    public int maximumGap(int[] nums) {
        //1. boundary check
        if(nums.length < 2) return 0;
        
        //2. get gap
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        //2.5 boundary check, if all cells have same value 
        if(max == min) return 0;
        
        //if we got floating num in gap, then it means some gaps are small some gaps are large
        //so the min MAX GAP will not be smaller than ceil(floating num)
        //max - min: total gap, nums.length - 1: total number of gaps
        int gap = (int) Math.ceil((max - min) / (nums.length - 1.0));
        
        //3. create buckets
        
        //we want to make a such bucket that the values in it will have a new gap < gap, therefore we can have at most gap size of
        //non duplicate numbers. Ex: like gap 2 [0, 1], [2, 3, 3], each bucket has new gap < 2, and at most have 2 different numbers
        
        //to make each bucket of size gap, we need (max-min)/gap + 1 num of buckets
        //(don't take ceiling, took 2 inputs as an example, 1 bucket is not enough)
        //think about form: [min, min + gap -1], [max] we allow each bucket contains at most "gap" num of items
        //so we need (max - min)/gap + 1 numbers of bucket
        int numOfbuckets = (max - min)/gap + 1; 
        
        bucket[] buckets = new bucket[numOfbuckets];
        
        for(int i = 0; i < numOfbuckets; i++){
            buckets[i] = new bucket();
        }
        
        //4. fill the bucket
        for(int num : nums){
            //value in bucketis "min" based, so we need covert 0 based input to "min" based input
            int index = (num-min)/gap;
            
            buckets[index].min = Math.min( num, buckets[index].min  );
            buckets[index].max = Math.max( num, buckets[index].max  );
        }
        
        //5. the diff inside on bucket is <= gap - 1, so we just need to check diff between neighbor buckets
        int result = 0;
        int prev = min;
        for(bucket bucket : buckets){
            if(bucket.min == Integer.MAX_VALUE) continue;
            
            result = Math.max(result, bucket.min - prev);
            prev = bucket.max;
        } 
        
        return result;
    }
}
