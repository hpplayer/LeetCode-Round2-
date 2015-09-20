/**
 * Math problem.
 * 
 * For A-Z with 1 - 26, it is 26 hex.
 * Each time, we get one digit from tail, then let num right shift by 1 digit.
 * The tricky part is dealing with conversion. We will not convert 26 to 2 digits, but 27 will immediately be converted to AA.
 * Plus, when we convert number to alphabet, we are converting 1-based index to 0-based index. So we will deduct 1 from each digit
 * Like I said, we want 26 still be 1 digit, while 27 be 2 digit, so when we right-shift our number, 26 will become 0, while 27
 * still become 1. To achieve this, we let number - 1, then divided by 26, and this calculation will give us the correct calculation
 * order.
 * 
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 12:17:01 AM
 */
public class Excel_Sheet_Column_Title_p168_sol1 {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0){
            sb.insert(0, (char) ((n-1)%26 + 'A'));
            n = (n-1)/26;
        }
        
        return sb.toString();
    }
}
