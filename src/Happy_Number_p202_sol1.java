/**
 * Two pointer approach
 * 
 * Since the problem states that we will guarantee fall into a loop, either end with 1 (happy number) or a set of numbers do not contain 1,
 * our real task is to detect the loop and find the catch point. For happy number, the catch point will be 1, for non happy number, the catch
 * pointer will a number in set of numbers. 
 * 
 * Remark:
 * 1. Here we use do{}while(); to make loop goes first before we meet, which is very smart. I rarely use such while loop, so put it here as a practice
 * The difference between do-while and while is that do-while evaluates its expression at the bottom of the loop instead of the top.
 * Therefore, the statements within the do block are always executed at least once.
 * 2. Be careful about "%",  1*2%3, will execute 1*2 first, so if we want get 2%3 first, then we need add () i.e. 1 * (2%3)
 * 
 * 
 * Sol1 provides a solution with two pointers
 * Sol2 provides a solution with HashSet
 * 
 * Both of them want to catch a number in the loop, but Sol2 may occupy more space so sol1 is more recommended
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 1:57:08 AM
 */
public class Happy_Number_p202_sol1 {
	
    public boolean isHappy(int n) {
        //use slow and fast pointer
        int slow = n, fast = n;
        
        do{
            slow = getNumber(slow);
            fast = getNumber(getNumber(fast));
        }while(slow != fast);//be careful about ";" after while()
        
        return slow == 1;//happy number fall into loop end with 1
    }
    
    public int getNumber(int n){//get next number
        int result = 0;
        
        while(n > 0){
            result += (n%10) * (n%10);//remember add () around % sign
            n /= 10;
        }
        
        return result;
    }
}
