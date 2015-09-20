/**
 * Quick sort plus divide-and-conquer.
 * 
 * To avoid sort of each number in the input array, we can just use quick sort to put k-1 numbers before pivotal. We don't need
 * to sort those k-1 numbers, we just want all of them larger than pivotal. After each partition around pivotal, we will check
 * how many numbers are before pivotal, then adjust our partitioning range accordingly, to include more and remove some numbers.
 * 
 * To be convenient, we always pick the leftmost node in current range as the pivotal. Then we check the pivotal's new index
 * after partitioning. If we got too many large number ( > k), then we will look only the left part of pivotal, we use a new
 * pivotal to update array, vice versa
 * 
 * This problem can also be solved by Max-Heap, which is trivial, so I don't list here
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 10:48:58 PM
 */

public class Kth_Largest_Element_in_an_Array_p215_sol1 {
	public static void main(String[] args){
		int[] nums = {3,2,3,1,2,4,5,5,6};
		int k = 9;
		System.out.println(new Kth_Largest_Element_in_an_Array_p215_sol1().findKthLargest(nums, k));
	}
	
    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length -1;
        while(true){
            int pos = quickSort(nums, start, end);
            if(pos == k -1){
            	//System.out.println(Arrays.toString(nums));
                return nums[pos];
            }else if(pos > k -1){
                end = pos -1;//too many large numbers, search front, pos is not kth largest number, so we skip it
            }else{
                start = pos + 1; //too less large numbers, search back, pos is not kth largest number, so we skip it
            }
        }
    }
    
    public int quickSort(int[] nums, int start, int end){
        int pivotal = nums[start];//pivotal
        int index_pivotal = start;//remember index of pivotal
        start += 1;//skip pivotal
        
    	//System.out.println(Arrays.toString(nums));
    	//System.out.println("Start: " + start + " end: " + end + " pivotal: " + index_pivotal);
    	
        while(start <= end){//we let two pointer pass each other
            //if found a pair that can be swapped 

            if(nums[start] < pivotal && nums[end] > pivotal){
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start ++;
                end --;
            }else if(nums[start] >= pivotal){
               start ++;
            }else if(nums[end] <= pivotal){
                end --;
            }
        }
        
        //end points to the last number that is >= pivotal, we need swap it with pivotal, to put pivotal to correct place
        int temp = nums[end];
        nums[end] = pivotal;
        nums[index_pivotal] = temp;
        
        return end;
    }
}
