/**
 * Binary search problem
 * 
 * Differed from other binary-search problems, here we are not finding a given target, but we just to find a cell that larger than 
 * its neighbors. So we at least needs two cells to compare, which make the loop condition become start < end not start <= end.
 * If num[mid] > num[mid+1], then may be mid is the peak, so we can only move end to index of mid
 * if num[mid] < num[mid+1], then we are sure num[mid] is not peak, so we can safely move start to index of mid + 1
 * We won't have num[mid] == num[mid+1] as we do not have duplicates in array
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 1:15:44 AM
 */
public class Find_Peak_Element_p162_sol1 {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        //when we still have a pair to compare
        while(start < end){
            int mid = start + (end - start)/2;
            //we always take floor(), so mid can be start, thus we want compare curr and next cells
            if(nums[mid] > nums[mid+1]){
                end = mid;
            }else if(nums[mid] < nums[mid+1]){
                start = mid + 1;
            }
        }
        
        return start;
    }
}
