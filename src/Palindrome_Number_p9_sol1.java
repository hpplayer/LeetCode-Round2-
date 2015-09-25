/**
 * Math problem.
 * 
 * The tricky part is dealing with corner cases(negatives, overflow, special input like 100021
 * 
 * We firstly get the length of input int. The tricky is we use x/div >= 10 to check the condition, so we can avoid overflow problem
 * meanwhile our loop will stop when we get the exact length of div
 * Then we try to get the first and last digit of current input. It can be done by /div and %10.
 * Then we remove the first and last digit
 * The next step is the most tricky part: how about div? Since we remove two digits, we can simply update div by div/100. By doing that, 
 * we can check the special case like 100021, where the second comparison is a specal case. If we use previous way to get the new div, then
 * we would skip all heading 0s, which would be in correct. But by just use div/100, we can still correctly found the left digit 0
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 8:58:37 PM
 */
public class Palindrome_Number_p9_sol1 {
	public static void main(String[] args){
		System.out.println(new Palindrome_Number_p9_sol1().isPalindrome(-2147447412));
	}
    public boolean isPalindrome(int x) {
        //we treat all negative as non palindrome
        if(x < 0) return false;
        
        int div = 1;
        
        //use x/div to avoid overflow smart!
        //remember >= 10 to get each digit
        while(x/div >= 10){
            div *= 10;
        }
        
        while(x != 0){
            int left = x / div;
            int right = x%10;
            if(left != right) return false;
            
            //it is very important to change div by 100 per time
            //I used to use a recursion, and get div for each recursive input, but if we got number like 
            //1000021, then we will skip all 0s in mid, and return incorrect result
            x = (x%div)/10;
            
            //after we remove 2 digits, our x is 100 times less
            div /= 100;
        }
        
        return true;
    }
}
