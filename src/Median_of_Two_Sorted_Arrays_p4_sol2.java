/**
 * Two pointer/binary search solution
 * 
 * Hard, a bunch of boundary check and also the basic idea is not easy to come up with.
 * 
 * Firstly we assume the median is in nums1, then what will the range be? At least we know the range will be 0 to m.
 * But we can further shrink the range by put nums2 on the left or right of nums1. If put left, then the median index in nums1
 * will be median - n, if put right, then the median index in nums1 will be median.
 * 
 * After decide the range, we can use binary search to find the target index. Assume now the target index is i.
 * If index i is the median, then we should have (m+n)/2 elements before i (inclusive). Since now i is index in the nums1,
 * which means we already got i elements before i (inclusive). Then we still need (m+n)/2 - i elements in nums2 before i.
 * Assume the (m+n)/2 - i - 1th index in nums2 is j (we need convert len to index, so - 1).
 * Then we should have nums2[j] <= nums[i] <= nums2[j+1]. Thus by comparing the value in those cells, we can tell whether our 
 * index i is too small or too large.
 * 
 * After we got the index, we would have 2 cases:
 * 1) the len is odd, then the value in index is the median
 * 2) the len is even, then we would have several cases: 
 * the value in index is the second median (it is difficult to explain why, just draw some examples to conclude)
 * So we have too candidates for first median, nums1[i-1] and nums2[j]
 * a) j in nums2 is out of boundary, then first median is nums1[i-1]
 * b) i in nums1 reach the head, then first median is nums2[j]
 * c) both index i - 1 and j are valid, for such case, we will pick the one closer to nums1[i], since they all should be smaller
 * than nums[i], we will pick the one with larger value
 * 
 * However, as discussed above, we assume median is in nums1, but probably it is in nums2. For such case, we wouldn't find a i
 * that makes ums2[j] <= nums[i] <= nums2[j+1]. Then we can simply swap nums1 and nums2, and search in nums2 instead
 * 
 *Remark:
 *Be careful, end + (start - end)/2 will not work if start < end
 *
 * @author hpPlayer
 * @date Sep 28, 2015 9:19:08 PM
 */
public class Median_of_Two_Sorted_Arrays_p4_sol2 {
	   public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int m = nums1.length, n = nums2.length;
	        //notice mid is the index of median with 1 based
	        int mid = (m + n) /2;
	        
	        boolean odd = ((m+n)&1) == 1;//odd or even number
	        
	        //if median is in nums1, the left boundary is put nums2 left of nums1, then the index is mid - n
	        int left = Math.max(0, mid - n);
	        //if median is in nums1, the right boundary is put nums2 right of nums1, then the index is mid
	        int right = Math.min(mid, m-1);
	        
	        //we will try to search median in num1, we will scan each number, so loop continues at left == right
	        while(left <= right){
	            //try mid 
	            int i = (left + right) / 2;
	            //we need mid - i elements in nums2 before i
	            int j = mid - i - 1;//-1 is to convert from len to index
	            
	            //we suppose nums2[j] <= nums[i] <= nums[j+1]
	            
	            //if nums[i] < nums2[j], then our index in nums1 is too small
	            if(j >= 0 && nums1[i] < nums2[j]){
	                left = i + 1;
	            }else if(j + 1 < n && nums1[i] > nums2[j + 1]){
	               //if nums[i] > nums2[j+1], then our index in nums1 is too big
	               right = i - 1;
	            }else{
	                //we found median in nums1
	                
	                if(odd){
	                    return nums1[i];
	                }else{
	                    //even, we use left/right to get median, and initial left/right is 1 based 
	                    //so the median found is the second median 
	                    if(j < 0 || j >= n){
	                        //if j is out of n, so the first median is in nums1
	                        return (nums1[i-1] + nums1[i]) / 2.0;
	                    }else if(i == 0){
	                        //if second median is already the head of nums1, then first median, must in nums2
	                        //the index is simply j, as we calculated by mid - i - 1
	                        return (nums2[j] + nums1[i]) / 2.0;
	                    }else{
	                        //if we don't know where median can be, and now we have two candidates
	                        //return the one closer to nums[i], since two candiates are all smaller than nums[i]\
	                        //we will pick the larger one
	                        return (nums1[i] + Math.max(nums1[i-1], nums2[j]))/2.0;
	                    }
	                }
	            }
	        }
	        
	        //if we can't find median in nums1, then we will search nums2
	        return findMedianSortedArrays(nums2, nums1);
	    }
}
