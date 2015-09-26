/**
 * Math problem
 * 
 * The original sol2 of p233 is very tricky and not intuitive. So I rewrite the code for easy-understanding
 * But sol2 is p233 is very short, if time is allowed, we can recheck sol2
 * 
 * This problem is still very problem-specific, and the approach is not general.
 * 
 * This solution attacks the input number one digit by one digit.
 * We will count how many 1s in current digit by considering the the part before it, digit itself, and the part after it.
 * xxxx   1   xxxx
 * prev digit after
 * If current digit > 1, then we can treat it as previous part, i.e. we have a complete streak start with 1 in this digit
 * If current digit = 1, then we will count how many numbers start with 1 in in this digit.
 * If current digit < 1, then we don't need to consider the part after it, we just return 1s in this digit from previous part 
 * 
 * Remark:
 * 1) To avoid overflow, we always use long type, except the result, which is defined return type
 * 2) Out count always start from 0, which means if we count numbers start with 1, then we should + 1 to include base case lie 10, 100, 1000..
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 3:18:03 PM
 */
public class Number_of_Digit_One_p233_sol2 {
	public static void main(String[] args){
		System.out.println(new Number_of_Digit_One_p233_sol2().countDigitOne(10));
	}
	
    public int countDigitOne(int n) {
        int result = 0;
        //use long to avoid overflow
        for(long base = 1; base <= n; base *= 10){
            //how many numbers have this base
            long nums = n/base;
            
            //we split the nums into two parts, whole base and incomplete base
            if(nums%10 > 1){
                //if current Digit >2, then we will have nums/10 + 1 numbers have whole base + 0 incomplete base
                nums = (nums/10 + 1) * base;
            }else if(nums%10 == 1){
                //if current Digit = 1, then we will have nums/10 numbers have whole base + n%base incomplete base
                nums = nums/10 * base + n%base + 1; //+1 including 10
            }else{
                //if current Digit = 1, then we will have nums/10 numbers have whole base + 0 incomplete base
                nums = nums/10 * base;
            }
            
            result += nums;
        }
        
        return result;
    }
}
