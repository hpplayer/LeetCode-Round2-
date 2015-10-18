/**
 * String + two pointer solution
 * 
 * This solution is almost same with sol1, but here we use built-in API instead
 * if we found there is a mismatch in loop, we will use either replace or insert to correct this mismatch. So we are supposed to have exact 
 * remaining part in two strings. Here we simply use string.equals() to compare the remaining part
 * Therefore, if we could reach the end of loop, it indicates there is no mismatch case so far. So to ensure we have exactly one mismatch,
 * we will check if the length is differed by 1, if it is then return true otherwise return false
 * 
 * 
 * @author hpPlayer
 * @date Oct 18, 2015 12:38:04 AM
 */
public class One_Edit_Distance_p161_sol2 {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        if(m > n) return isOneEditDistance(t, s);
        if(n - m >= 2) return false;
        
        for(int i = 0, j = 0; i<m; i++, j++){
            //check if remaining strings are same after one change
            if(s.charAt(i) != t.charAt(j)){
                if(m == n){
                    //same length, then move i and j simultaneously
                    return s.substring(i+1).equals(t.substring(j+1));
                }else{
                    //different length, keep i and move j as we insert a char before index i
                    return s.substring(i).equals(t.substring(j+1));
                }
                
            }
        }
        
        //we have taken care of one mismatch case above
        //we are here only because s is same with t or prefix of t
        //if s is same with t, return false, if s is prefix of t then return true
        return n - m == 1;
    }
}
