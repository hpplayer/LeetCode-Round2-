/**
 * Two pointer problem, but I would rather call it three pointers
 *
 * There are only three numbers, so we just need to mark the end of series of first number and the start of series of third number,
 * then we use the third pointer to scan the array, and insert number into the appropriate position.
 * 1) If the number is 0, we swapped it with the next number after end of series of first number. That swapped number must be 1
 * Otherwise we would already put it into the 0 series or 2 series, so we don't need to check the swapped value
 * 2) If the number is 1, we simply skip it and assume now it is in the 1 series. If later we got 0 or 2 in this 1 series, we will
 * swap them with 1, and keep our 1 series consistent
 * 3) If the number is 2, we swapped it with the prev number before start of series of third number. That swapped number is an 
 * unknown number may be 0/1/2 since we have not visited that index yet, So we have to stay in the index to check the swapped number
 * 
 * We have three pointers, two pointer move forward(first marker and scanner) one pointer move backward. Like other two pointers
 * problem, our loop will break when forward pointer meets backward pointer. In our case, i move faster than InsertZero, so our 
 * loop will exit when i == InsertTwo, i.e. after we deal with the last number in mid, we will exit loop and report sorted array.
 * 
 * This problem could also be solved by counting algorithm. First pass count the total number of each number, second pass reassign
 * the numbers, but it is trivial so I will not list it here.
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 2:55:15 PM
 */
public class Sort_Colors_p75_sol1 {
    public void sortColors(int[] nums) {
        int InsertZero = 0;//index to insert 0
        int InsertTwo = nums.length - 1;//index to insert 2
        
        for(int i = 0; i <= InsertTwo; i++){//need check each num, so our loop will cover last num where i == InsertTwo
            if(nums[i] == 0){
                swap(nums, i, InsertZero);
                InsertZero++;//got one zero, move this pointer one step forward
            }else if(nums[i] == 2){
                swap(nums, i,  InsertTwo);
                InsertTwo --;//got one two, move this pointer one step backward
                i--;//check the swapped value since it is from unvisited index 
            }
        }
    }
    
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
