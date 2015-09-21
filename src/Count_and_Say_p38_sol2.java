/**
 * 
 * Iterative solution of sol2.
 * The idea is exactly same, we generate string for each ith sequence one by one, then it will be used as input string for next iteration
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 11:34:23 PM
 */

public class Count_and_Say_p38_sol2 {
    public String countAndSay(int n) {
        
        String s = "1";
        for(int i = 1; i < n; i++){
            StringBuilder sb = new StringBuilder();
            char prev = s.charAt(0);
            int count = 1;
            for(int j = 1; j < s.length(); j++){
                if(s.charAt(j) == prev){
                    count ++;
                }else{
                    sb.append(count);
                    sb.append(prev);
                    count = 1;
                }
                prev = s.charAt(j);
            }
            
            //insert last pair
            sb.append(count);
            sb.append(prev);
            
            s = sb.toString();
        }
        
        return s;
    }
}
