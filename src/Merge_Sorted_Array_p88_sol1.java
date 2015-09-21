/**
 * Two pointer problem.
 * To avoid shift arrays, we can just merge backward.
 * Attach the largest number in m + n - 1, then move pointer accordingly.
 * The difficulty part is corner case.
 * 1) Since we always insert larger value backward without shift, if at some point, our pointer in num2[] < 0, it means we have inserted all values
 * into the nums1, and we can simply stop here, since the remaining part in num1 should already in sorted order.
 * 2) Of course, our pointer in nums1[] may < 0, it means we only need to insert all numbers in num2[] into num1[], then we are done.
 * 
 * So be careful with the boundary check and those corner case!
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 10:42:44 PM
 */
public class Merge_Sorted_Array_p88_sol1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int offset = nums1.length - (m + n);
        int a = m - 1;
        int b = n - 1;
        for(int i = nums1.length - 1 - offset; i >=0 && b >= 0; i--){
            if(a >= 0 && nums1[a] >= nums2[b]){
                nums1[i] = nums1[a];
                a --;
            }else{
                nums1[i] = nums2[b];
                b --;
            }
        }
    }
}
