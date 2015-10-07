import java.util.*;

/**
 * HashSet problem
 * 
 * Since our calculation will finally fall into a loop. We just need to use a HashSet to store all calculated value, then at a later point,
 * we must get a number that already in HashSet, if that number is 1, then the input is happy number, otherwise it is not happy number
 * But the problem here is that we may have a very large input number, so the HashSet will contain a lot of unnecessary non-repeat number.
 * Except for that, this solution is good. 
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 2:07:35 AM
 */
public class Happy_Number_p202_sol2 {
    public boolean isHappy(int n) {
        HashSet<Integer> hs = new HashSet<Integer>();
        
        while(!hs.contains(n)){
            hs.add(n);
            n = getNumber(n);
        }
        
        return n == 1;
    }
    
    public int getNumber(int n){
        int newNum = 0;
        while(n > 0){
        	//be careful about order of operator, to be safe rounded by ()
            newNum += (n%10) * (n%10);
            n /= 10;
        }
        
        return newNum;
    }
}
