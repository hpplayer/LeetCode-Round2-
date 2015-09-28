/*
Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate element must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant extra space.
Your runtime complexity should be less than O(n2).
*/

/**
 * Two pointer + binary search problem
 * 
 * Notice: although we only have one duplicate element, but this elements can appear more than twice, which means [2,2,2,2] is also a valid input
 * 
 * At first I was very surprising this problem can be solved with binary search, but it does has a binary search solution.
 * The tricky part is to come up with idea of binary search idea
 * 
 * Our initial search range is [1, n], i.e. each number can be a potential candidate
 * Then we calculate and get the mid number. The next step is the key part of solution: we need to rescan the array and count the number appears
 * smaller or equal mid. If we don't have duplicates in [1, mid], then we should exactly get count = mid. If count > mid, then obviously, we got 
 * duplicates in range [1, mid]. However, if count == mid, then we don't have duplicates in range [1,mid], so our next search range is [mid + 1, n]
 * Besides, like I mentioned above, the duplicates may occur more than 2 times, thus it is even possible we don't have enough number in range [1, mid],
 * i.e. the count will be < mid. For such case, we will definitely search range [mid + 1, n]
 * 
 * We initially have n candidates, each time, we will filter half of the candidates, thus this part costs O(logN)
 * The time used to scan array is O(n), so the total running time is O(NlogN)
 * 
 * @author hpPlayer
 * @date Sep 28, 2015 1:10:37 AM
 */
public class Find_the_Duplicate_Number_p287_sol1 {
    public int findDuplicate(int[] nums) {
        //our number is supposed to have range from 1 to n
        int start = 0, end = nums.length - 1;
        //while we still have two numbers left
        while(start < end){
            int mid = start + (end - start)/2;
            int count = 0;//count how many numbers < mid appear
            for(int num : nums){
                if(num <= mid) count ++;
            }
            
            //num <= mid is supposed to appear mid times, like mid: 2, we should have 1,2 
            //if count > mid, then we definitely need search numbers <= 2
            if(count > mid){
                end = mid;
            }else if(count == mid){
                //if count == mid means numbers <=2 has correct appearances, so we search numbers > 2
                start = mid+1;
            }else if(count < mid){
                //what does count < mid means? Our duplicate may appear more than twice,
                // thus we even don't have enough number <=2, definitely, we search numbers > 2
                start = mid + 1;
            }
        }
        
        return start;
    }
}
