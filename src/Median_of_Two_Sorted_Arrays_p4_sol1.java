/*
Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

/**
 * Divide-and-conquer
 * 
 * This problem is hard, the tricky part include 1) come up with idea of divide-and-conquer 2) deal with boundary cases
 * 
 * To generate a solution with run time of O(log(m+n)) we have to discard half of search range. How can we do that? Assume our 
 * search median is k, then we know there be exactly k - 1 elements before it. In our case, it also means there will be exactly
 * k-1 elements smaller than it. So the basic idea is to discard half of search range by finding k-1 elements smaller than it.
 * To achieve this goal, we will search the k/2 element in nums1 and nums2. If we found k/2 element in nums1 < k/2 element in nums2,
 * then we are sure k/2 elements in nums1 will be included before median. For the other k/2 elements, they may in the later part of
 * nums1 or maybe just the first k/2 elements in nums2, or maybe just the combination. So each time, we can discard k/2 elements. We
 * can recursively do this, until we reach the boundary case which means we found the numbers after k/2 as well. Those boundary case
 * may be our k is reduced to 1, or we have reached the end of one array, in either case, we can directly return kth value. 
 * 
 * k = (m + n) /2, we will search at most logK times, so this algorithm runs in O(log(m+n)) time
 * 
 * Remark:
 * Our array is fixed, but we will shrink our search range in the array, so we need two extra pointers to indicate the new start 
 * of search range, which are start1 and start2 respectively
 * 
 * Sol1 uses divide-and-conquer approach, each time we found half elements, thus shrink our search range by half
 * Sol2 uses binary-search approach, each time we will use mid to update the search range, it also shrink the search range by 
 * half in each loop
 * So both solutions run in O(log(N+M)) time
 * @author hpPlayer
 * @date Sep 28, 2015 4:21:10 PM
 */
public class Median_of_Two_Sorted_Arrays_p4_sol1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if(len % 2 == 1){
            //odd, one median, which should be the ceil() of half sum
            return findKthSortedArrays(nums1, nums2, 0, 0, len/2 + 1);
        }else{
            //even, two medians, take average, to keep consistence, we also use double type of 2
            return (findKthSortedArrays(nums1, nums2, 0, 0, len/2 + 1) + findKthSortedArrays(nums1, nums2, 0, 0, len/2)) / 2.0;
        }
    }
    
    public double findKthSortedArrays(int[] nums1, int[] nums2, int start1, int start2, int k){
        //start is the start index of new subarray, k is the element we need to search
        
        //all nums in nums1 are before median, now we still need k elements to reach median
        //so we just start search median in nums2
        if(start1 >= nums1.length) return nums2[start2 + k - 1];
        
        //same as above
        if(start2 >= nums2.length) return nums1[start1 + k - 1];
        
        if(k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        //mid1 is the element in the index of start1 + k/2 -1 , we will use this value to compare with mid2 from
        //nums2, if mid1 is smaller, then it is guaranteed all elements before mid1 in nums1 will be included 
        //before median, then we next search range in nums1 will start from index start1 + k/2
        //Notice: it is also possible that we don't have k/2 elements in nums1, for such case, we will have 
        //to include all k/2 elements in nums2 before median, so next search range in nums2 will from start2 + k/2
        int mid1 = start1 + k/2 - 1 >= nums1.length? Integer.MAX_VALUE : nums1[start1 + k/2 - 1];
        int mid2 = start2 + k/2 - 1 >= nums2.length? Integer.MAX_VALUE : nums2[start2 + k/2 - 1];
        
        if(mid1 < mid2){
            //if we will include all elements (k/2 elemetns) before mid1 in nums1
            //since k/2 may take the floor(), we have to use k - k/2 to find the accurate count of elements left
            return findKthSortedArrays(nums1, nums2, start1 + k/2, start2, k - k/2);
        }else{
           return findKthSortedArrays(nums1, nums2, start1, start2 + k/2, k - k/2); 
        }
    }
}
