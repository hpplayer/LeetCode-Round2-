/**
 * Divide-and-conquer problem
 * 
 * The tricky part is dealing with 0s in mid and use trim() to handle tailing " "s
 * 
 * The algorithm used to solve the problem is not hard, but we have to be careful with corner cases.
 * We split the input number into several small integers, which at most has 3 digits. Then we read number from those small integers
 * To deal with 0s in mid, we always skip small integer which does not has non-zero digits. 
 * To append " ", we always append it after small integers, we also split 3 digits integers into smaller integers to read them correctly
 * if the smaller integer is 0, then we won't append " ", otherwise we would have two consecutive " "s. In case we have tailing " "s, we can
 * just use trim() to remove all of them.
 * 
 * For the unit, we use an index "i" to track how many splits we have done before. We will start add unit from thousand after we have done 
 * at least one split before. 
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 3:10:56 PM
 */
public class Integer_to_English_Words_p273_sol1 {
	public static void main(String[] args){
		System.out.println(new Integer_to_English_Words_p273_sol1().numberToWords(1000));
	}
	
    public String numberToWords(int num) {
        //0 is special case, we deal with it seperately
        if(num == 0) return "Zero";
        
        String[] largerThan1000 = {"", "Thousand ", "Million ", "Billion ", "Trillion "};
        
        int i = 0;//the index of segment that we are looking at
        String result = "";
        
        while(num > 0){
            //if we have digits in this segment
            if(num%1000 != 0){
                //returned string should have " " after it, so don't add extra " " 
                //at least our loop goes more than 1 loop, should we start append unit in largerThan1000
                result = helper(num%1000) + largerThan1000[i] + result;
            }
            
            num /= 1000;
            i++;
        }
        
        return result.trim();
    }
    
    public String helper(int num){
        String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] lessThan100 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        
        if(num == 0){
            //since we don't have digits in input, we won't add " ", otherwise we would have two consecutive " "s
            return "";
        }else if(num < 20){
            //we have digits in input, so append " " after it
            return lessThan20[num] + " ";
        }else if(num < 100){
            //use num/10 to get digit, num%100 to deal with remainders
            return lessThan100[num/10] + " " + helper(num%10); 
        }else{// > 100 and < 1000
            return lessThan20[num/100] + " Hundred " + helper(num%100);
        }
    }
}
