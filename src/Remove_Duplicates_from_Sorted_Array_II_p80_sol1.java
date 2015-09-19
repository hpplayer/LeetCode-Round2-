/**
 * A two pointer problem
 * The problem asks we insert number at most two times in new array, thus we can compare current number with the last second number in
 * new array. If they are same, it means current number has already appeared two times in new array, so we can just skip current number.
 * Two pointer, one pointer points to the last second number in new array, one pointer points to current number in input array
 * @author hpPlayer
 * @date Sep 18, 2015 2:27:25 PM
 */
public class Remove_Duplicates_from_Sorted_Array_II_p80_sol1 {
	public static void main(String[] args){
		int nums[] = {1,1};
		System.out.println(new Remove_Duplicates_from_Sorted_Array_II_p80_sol1().removeDuplicates(nums));
	}
	
    public int removeDuplicates(int[] nums) {
        int index = 0;//where to insert new number
        for(int i = 0; i < nums.length; i++){
            //as long as current number is not same with the last second number in new array, insert it
            if(index < 2 || nums[i] != nums[index-2]) nums[index++] = nums[i];
        }
        return index;
    }
}
